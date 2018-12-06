package com.hyj.controller;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Calculator {
	
	
	public int add(int one,int two) {
		log.info("더하기 nums");
		return one+two;
	}
}
