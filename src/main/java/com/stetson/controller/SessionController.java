package com.stetson.controller;

import com.stetson.controller.interfaces.ITemporaryConstants;
import com.stetson.exceptions.HashException;
import com.stetson.models.DbRowMap;
import com.stetson.models.DbVal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.stetson.controller.interfaces.ISessionController.SESS_NAME;
import static com.stetson.controller.interfaces.ITemporaryConstants.TEST_PWD;

//TODO: Convert to HTTP INTERCEPTOR so it is a middleware not a normal controllee
//https://www.baeldung.com/spring-mvc-custom-handler-interceptor
//basic tutorial: https://www.baeldung.com/spring-mvc-handlerinterceptor

@Controller
public class SessionController {
    @RequestMapping(value = {"/","/index"}, method = RequestMethod.GET)
    public String index(ModelMap model) {
        //"message" is an ttribute in "hello.jsp", "hello.jsp" is created by yourself
        model.addAttribute("userName", System.getProperty("user.name").toUpperCase());
        //transform "hello" to "hello.jsp"
        return "index";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute(SESS_NAME);
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam("accessToken") String clearPwd,
                        HttpServletRequest req, HttpServletResponse resp, ModelMap modelMap) {

        for (byte[] val : PasswordHandler.generateNewHash(TEST_PWD)) {
            System.out.println("PWD: "+new String(val));
        }

        if (clearPwd.equals(TEST_PWD)) {
            req.getSession().setAttribute(SESS_NAME, ITemporaryConstants.SESS_TOKEN);
            System.out.println("SessionController:login: Session set.");
            try {
                resp.sendRedirect("/");
            } catch (IOException e) {
                System.err.println("SessionController:login: Could not redirect to index. Just nested view inside login page.");
                e.printStackTrace();
            }
            return index(modelMap);
        } else {
            modelMap.put("error", "Accesstoken not correct. Please contact administrator.");
            return "login";
        }
    }


    private static class PasswordHandler {
        private static final String sqlTableName = "accesstokens";
        private static final String defaultPrimaryKeyValue = "tarotToken"; //as only one row neccessary
        private static final SecureRandom random = new SecureRandom();

        private static byte[] generateSalt() {
            byte[] salt = new byte[16];
            random.nextBytes(salt);
            return salt;
        }

        private static byte[] generateHash(String clearPwd, byte[] salt) {
            try {
                KeySpec spec = new PBEKeySpec(clearPwd.toCharArray(), salt, 65536, 128);
                SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
                return factory.generateSecret(spec).getEncoded();
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                e.printStackTrace();
                throw new HashException("Algorithm or spec key invalid!");
            }
        }

        /** @return List<byte[]>: Size of 2, first index has the hashed token, 2nd index the salt */
        protected static List<byte[]> generateNewHash(String clearPwd) {
            List<byte[]> newPwd = new ArrayList<>();
            byte[] salt = generateSalt();
            newPwd.add(generateHash(clearPwd,salt));
            newPwd.add(salt);
            return newPwd;
        }

        protected static boolean isTokenValid(String clearToken) {
            try {
                ArrayList<DbVal> accessTokenRow = new DbRowMap(sqlTableName).get(defaultPrimaryKeyValue); //first row
                byte[] salt = accessTokenRow.get(2).toString().getBytes(); //get salt
                byte[] tokenToValidateHashed = generateHash(clearToken,salt);
                byte[] validTokenHashedFromDb = accessTokenRow.get(1).toString().getBytes();
                return Arrays.equals(validTokenHashedFromDb,tokenToValidateHashed);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new HashException("Could not determine if token is valid.");
            }
        }
    }
}
