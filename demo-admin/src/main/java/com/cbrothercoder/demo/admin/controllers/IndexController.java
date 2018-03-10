package com.cbrothercoder.demo.admin.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController implements EnvironmentAware {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
    private Environment         environment = null;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @RequestMapping(value = { "/" }, method = { RequestMethod.GET })
    public String index() {
        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "json", method = RequestMethod.POST)
    public void reBody(@RequestParam(value = "param") String param, HttpServletRequest request, HttpServletResponse response) {

    }
}
