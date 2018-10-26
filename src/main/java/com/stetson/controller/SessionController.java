package com.stetson.controller;

import com.stetson.controller.interfaces.ITemporaryConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.stetson.controller.interfaces.ISessionController.SESS_NAME;

//TODO: Convert to HTTP INTERCEPTOR so it is a middleware not a normal controllee
//https://www.baeldung.com/spring-mvc-custom-handler-interceptor
//basic tutorial: https://www.baeldung.com/spring-mvc-handlerinterceptor

@Controller
public class SessionController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
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

        if (clearPwd.equals(ITemporaryConstants.TEST_PWD)) {
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
}
