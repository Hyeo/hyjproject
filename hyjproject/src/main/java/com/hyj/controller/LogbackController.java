package com.hyj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LogbackController {
	
	//logbok을 이용한 처리
	@RequestMapping("logtest")
	public String logtest() {
		log.trace("trace");
		log.debug("debug");
		log.info("info");
		log.warn("warn");
		log.error("error");
		return "home";
	}
}
