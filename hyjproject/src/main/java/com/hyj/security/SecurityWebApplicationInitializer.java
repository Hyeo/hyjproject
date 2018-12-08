package com.hyj.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/*
 * 애플리케이션의 모든 URL에 대해 springSecurityFilterChain 필터를 자동으로 등록을 함
 * WebSecurityConfig 를로드하는 ContextLoaderListener를 추가함
 */
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer{

	
}
