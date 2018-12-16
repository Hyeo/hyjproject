package com.hyj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hyj.utill.LoginUser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UserController {
   
	/*
	 * login URL 접근시 일로 접근을 하게 된다.
	 */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        log.info("=========================================");
        log.info("=========================================");
        log.info("===================login=================");
        log.info("=========================================");
        log.info("=========================================");
    	return new ModelAndView("login");
    }

    /*
     * 로그인 성공시 response 리다이렉트를 통해 일로 온다.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(LoginUser loginUser) {
        log.info("=========================================");
        log.info("=========================================");
        log.info("===================index=================");
        log.info("=========================================");
        log.info("=========================================");
    	ModelAndView model = new ModelAndView("index");
        model.addObject("user", loginUser);
        return model;
    }
    
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home() {
        log.info("=========================================");
        log.info("=========================================");
        log.info("===================home=================");
        log.info("=========================================");
        log.info("=========================================");
    	return new ModelAndView("home");
    }
}
