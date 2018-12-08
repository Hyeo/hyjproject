package com.hyj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hyj.services.HyjService;
import com.hyj.utill.HyjCode;
import com.hyj.vo.HyjVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class EnumController {

	@Autowired
	HyjService HyjService;
	
	/*
	 *  enum함수에 있는 값 불러오기
	 */
	@RequestMapping("enumtest")
	public String enumtest(Model model) {
		String code=HyjCode.apple.getCode();
		int upcode=Integer.parseInt(HyjCode.apple.getUpperCode());
		List<HyjVo> hyjVo = HyjService.select();
		hyjVo.get(0).setName(code);
		hyjVo.get(0).setNum(upcode);
		model.addAttribute("hyjVo",hyjVo);
		return "home";
	}
}
