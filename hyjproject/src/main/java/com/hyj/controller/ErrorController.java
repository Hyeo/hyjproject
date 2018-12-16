package com.hyj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ErrorController {
	/*
	 * 404 error 발생시 이 페이지로 이동
	 */
	@RequestMapping("error/404")
	public String error(Model model) {
		log.info("404error발생");
		model.addAttribute("msg","접근이 금지되었습니다.");
		return "error/errorPage";
	}
}
