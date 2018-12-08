package com.hyj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TilesController {

	@RequestMapping("tile")
	public String tile(){
		
		return "tile/tile";
	}
}
