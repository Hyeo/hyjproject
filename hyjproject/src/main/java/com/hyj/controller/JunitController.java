package com.hyj.controller;

import lombok.extern.slf4j.Slf4j;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.stereotype.Controller;

import com.hyj.utill.Calculator;

@Slf4j
@Controller
public class JunitController {

	/*
	 * Junit Before 클래스 가장먼저 1번만 실행이 됨
	 * 무조건 static void로 선언할 것
	 */
	@BeforeClass // 각 테스트마다 인스턴스가 매번 다시 생성되어 독립적인 테스트 가능
	public static void beforeClass() {
		log.info("@beforeClass");		
	}
	
	/*
	 * Junit Before는 test가 실행되기전 각 1번씩 실행 test가 많을수록 많이 실행
	 * 무조건 void로 선언할 것
	 */
	@Before // 각 테스트마다 인스턴스가 매번 다시 생성되어 독립적인 테스트 가능
	public void befores() {
		log.info("@before");		
	}

	/*
	 * Junit test random 으로 실행
	 * 무조건 void로 선언할 것
	 * 순서 설정 가능하니 인터넷 검색
	 */
	@Test
	public void test1() {
		log.info("@test1");
		Calculator cal=new Calculator();
		assertEquals(7, cal.add(3, 4));
	}

	/*
	 * Junit test random 으로 실행
	 * 무조건 void로 선언할 것
	 * 순서 설정 가능하니 인터넷 검색
	 */
	@Test
	public void test2() {
		log.info("@test2");
	}
	
	/*
	 * Junit test random 으로 실행
	 * 무조건 void로 선언할 것
	 * 순서 설정 가능하니 인터넷 검색
	 */
	@Test
	public void test3() {
		log.info("@test3");
	}

	/*
	 * Junit After는 test가 실행한 후 각 1번씩 실행 test가 많을수록 많이 실행
	 * 무조건 void로 선언할 것
	 */
	@org.junit.After // 후처리 작업
	public void after() {
		log.info("@after");
	}
	
	/*
	 * Junit After는 test가 실행한 후 각 1번씩 실행 test가 많을수록 많이 실행
	 * 무조건 void로 선언할 것
	 */
	@AfterClass // 후처리 작업
	public static void afterClass() {
		log.info("@afterClass");
	}
}
