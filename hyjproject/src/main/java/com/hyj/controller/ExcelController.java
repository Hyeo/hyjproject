package com.hyj.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hyj.utill.ExcelDown;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ExcelController {

	//HSSF => 97~2003 XSSF => 2007버전 이상	
	@RequestMapping("excelDown")
	public @ResponseBody byte[] excelDown(HttpServletResponse response) {
		ExcelDown excelDown=new ExcelDown();
		byte[] bytes=excelDown.excelDownload();
		
	    //엑셀 이름 정함함
	    response.setHeader("Content-Disposition", "attachment; filename=Company.xlsx");
	    //크기
	    response.setContentLength(bytes.length);
	    //타입
	    response.setContentType("application/vnd.ms-excel");
	    return bytes;
	}
	
	@RequestMapping("readExcel")
	public String readExcel(@RequestParam (name = "weeklyFile") MultipartFile weeklyFile) {
		log.info("one");
		ExcelDown excelDown=new ExcelDown();
		log.info("two");
		excelDown.readExcel(weeklyFile);
		log.info("three");
		return "home";
	}
}
