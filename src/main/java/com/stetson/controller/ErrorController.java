package com.stetson.controller;

import jdk.jfr.events.ThrowablesEvent;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.MessageFormat;

/**
TODO: https://www.javacodegeeks.com/2013/11/how-to-custom-error-pages-in-tomcat-with-spring-mvc.html
 */
@Controller
public class ErrorController {
    @RequestMapping("error")
    public String error(HttpServletRequest request,
                        HttpServletResponse response, Model model) {
        //retrieve useful information
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");

        String exceptionMessage = getExceptionMessage(throwable, statusCode);

        String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }

        model.addAttribute("statusCode", statusCode);
        model.addAttribute("requestUri", requestUri);
        model.addAttribute("exceptionMessage", exceptionMessage);
        model.addAttribute("fullErrorMessage",  MessageFormat.format("{0} returned for {1} with message <strong>{2}</strong>",statusCode,requestUri, exceptionMessage));
        return "customError";
    }

    private String getExceptionMessage(Throwable throwable, Integer statusCode) {
        if (throwable != null) {
            return throwable.getCause().getMessage();
        }
        HttpStatus httpStatus = HttpStatus.valueOf(statusCode);
        return httpStatus.getReasonPhrase();
    }

}
