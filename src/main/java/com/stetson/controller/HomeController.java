package com.stetson.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

import static com.stetson.controller.interfaces.ISessionController.SESS_NAME;

@Controller
public class HomeController {
    @RequestMapping(value = "generator", method = RequestMethod.GET)
    public String showGenerator() {
        return "generator";
    }
}
