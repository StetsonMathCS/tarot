package com.stetson.interceptors;

import com.stetson.controller.interfaces.ITemporaryConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.stetson.controller.interfaces.ISessionController.SESS_NAME;

/**
 * Simple session middleware
 */
public class SessionInterceptor implements HandlerInterceptor {
    private static final long MAX_INACTIVE_SESSION_TIME = 999999 * 1000;

    @Autowired
    private HttpSession session;

    private static boolean isUserLogged(HttpServletRequest request) {
        if (request == null) {
            System.out.println("ERR_REQ");
        } else if (request.getSession() == null) {
            System.out.println("ERR_SESS");
        } else {
            Object sessionId = request.getSession().getAttribute(SESS_NAME);
            if (sessionId != null) {
                return sessionId.equals(ITemporaryConstants.SESS_TOKEN); //TODO: evaluate value.
            } else {
                System.out.println("ERR_SESS_NOT_SET");
            }
        }
        return false;
    }

    private static void logout(HttpServletResponse response) throws Exception {
        System.out.println("User tried to logout.");
        response.sendRedirect("/logout");
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        long startTime = System.currentTimeMillis();
        httpServletRequest.setAttribute("executionTime", startTime);
        boolean isLoginPage = httpServletRequest.getRequestURI().equals("/login");

        if (SessionInterceptor.isUserLogged(httpServletRequest)) {
            session = httpServletRequest.getSession();
            System.out.println("Time since last request in this session: " + (System.currentTimeMillis() - httpServletRequest.getSession().getLastAccessedTime()) + " ms");
            if (System.currentTimeMillis() - session.getLastAccessedTime() > MAX_INACTIVE_SESSION_TIME) {
                System.out.println("Logging out, due to inactive session");
                //SecurityContextHolder.clearContext();
                SessionInterceptor.logout(httpServletResponse);
            }

            System.out.println("SessionInterceptor:preHandle: User is browsing page -> " + httpServletRequest.getRequestURI());
            if (isLoginPage) {
                //automatic redirect to index page as user already logged in
                httpServletResponse.sendRedirect("/index");
            }
        } else {
            //redirect to login page (no need to
            if (!isLoginPage) {
                httpServletResponse.sendRedirect("/login"); //is ignored by interceptor
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object
            o, ModelAndView modelAndView) throws Exception {
        System.out.println("Post handle method - check execution time of handling");
        long startTime = (Long) httpServletRequest.getAttribute("executionTime");
        System.out.println("Execution time for the request was: " + (System.currentTimeMillis() - startTime) + "ms");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse
            httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("After completion called.");
    }
}
