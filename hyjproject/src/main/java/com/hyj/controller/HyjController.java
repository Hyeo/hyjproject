package com.hyj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hyj.services.HyjService;
import com.hyj.vo.HyjVo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HyjController {

	@Autowired
	HyjService HyjService;

	//전체리스트 가져오기
	@RequestMapping("select")
	public String view(Model model) {
		
		List<HyjVo> hyjVo = HyjService.select();
		model.addAttribute("hyjVo", hyjVo);
		return "home";
	}

	
}
