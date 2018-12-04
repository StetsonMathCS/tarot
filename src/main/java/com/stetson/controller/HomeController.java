package com.stetson.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.io.IOException;


import javax.servlet.http.HttpSession;

import static com.stetson.controller.interfaces.ISessionController.SESS_NAME;

@Controller
public class HomeController {
    @RequestMapping(value = "generator", method = RequestMethod.GET)
    public String showGenerator() {
        return "generator";
    }

    @RequestMapping(value = "generator", method = RequestMethod.POST)
    public String execute() {
        Runtime rt = Runtime.getRuntime();
        try {
            rt.exec("cd /opt/ibm/ILOG/CPLEX_Studio128/opl/bin/x86-64_linux");
        } catch(IOException ex) {
            ex.toString();
        }
        try {
            rt.exec("LD_LIBRARY_PATH=./ ./oplrun tarot/Tarot.mod tarot/Tarot.dat");
        } catch(IOException ex) {
            ex.toString();
        }
        return "results";
    }
}

