package com.hyj.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.hyj.services.UserDetailService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	
	/*
	 * @EnableGlobalMethodSecurity(securedEnabled = true) 란?
	 * @Configuration인스턴스 에서든 주석을 사용하여 주석 기반 보안을 활성화 할 수 있습니다 
	 * @Secured주석을 가능하게한다
	 * 
	 * prePostEnabled = true 이것 또한 마찬가지 
	 * 
	 * 둘의 차이점은
	 * secured 	    =>  OR조건만 가능
	 * preauthorize =>  AND조건,OR조건 가능
	 */
	
	
	/*
	 * javax.sql로 import
	 */
	@Autowired
    DataSource dataSource;

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Autowired
    private UserDetailService userDetailService;
	
	//security를 적용하지 않는 곳을 지정한다.
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web.ignoring().antMatchers("/resources/**");
	    web.ignoring().antMatchers("/photoUpload/**/**");
	    web.ignoring().antMatchers("/smartupload");
	    web.ignoring().antMatchers("/ckeditor");
	    web.ignoring().antMatchers("/file");
	    
	    
	    
	    
        log.info("=========================================");
        log.info("=========================================");
        log.info("===================ignore=================");
        log.info("=========================================");
        log.info("=========================================");
	}

	/*
	 * http의 모든 인증 절차를 받는곳으로 로그인 페이지를 별도를 설정할수 있다.
	 * 로그인 처리를 담당하는 경로와 실패를 처리하는 경로까지 정의되고 있고 로그인 성공시 처리되는 클래스 loginSuccessHanler로 설정
	 * 로그아웃을 클릭하면 쿠키까지 제거하는 기능도 들어가져 있다. username, password는 login페이지에서 넘어오는 파라미터를 말한다.
	 * 그리고 마지막 로그아웃을 하면 보이는 경로는 초기경로 등으로 표시했다. 여기서는 csrf는 사용안함으로 처리했다.
	 */
	@Override
	protected void configure(HttpSecurity http) throws  Exception {
        log.info("=========================================");
        log.info("=========================================");
        log.info("=========================================");
        log.info("============도입 configure===============");
        log.info("=========================================");
        log.info("=========================================");

    		http.authorizeRequests().antMatchers("/home","/login").permitAll().anyRequest().authenticated().and().formLogin().loginPage("/login")
	        .loginProcessingUrl("/login").failureUrl("/login?error=true").successHandler(loginSuccessHandler)
	        .usernameParameter("username").passwordParameter("password").permitAll().and().logout().deleteCookies("remove")
	        .invalidateHttpSession(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	        .logoutSuccessUrl("/").and().csrf().disable( );        
        	log.info("=========================================");
	        log.info("=========================================");
	        log.info("===================configure=================");
	        log.info("=========================================");
	        log.info("=========================================");
	}
	/*
	 * homd은 아무나 들어가는게 가능
	 * authorizeRequests => 인증된 사용자만 접근할수있습니다.
	 * anyRequest() => 어떤 요청이든지
	 * .authenticated() => 인증이 필요
	 * formLogin() =>  -> 폼을 통한 로그인을 이용한다는 의미이며,
	 * loginPage("/login")를 통해 로그인 페이지는 /login 경로로 제공하며 /login의 POST 요청이 바로 로그인 처리 과정이라는 것
	 * 
	 * failureUrl("/login?error=true") => 이것은 로그인시 실패했을 경웅
	 * successHandler => 로그인 성공시 
	 * usernameParameter("username") =>username 값을 추출
	 * passwordParameter("password") => password 라는 값을 추출
	 * permitAll => 모든 사용자들
	 * 
	 * logout().deleteCookies("remove")
	 * .logoutSuccessUrl("/")
	 * 
	 * .csrf().disable(); => csrf는 사용하지 않는다.
	 * 
	 * 
	 * 
	 * 
	 * .antMatchers ( "/ resources / **" , "/ signup" , "/ about" ) .permitAll () 
	 * 모든 사용자가 액세스 할 수있는 여러 개의 URL 패턴을 지정했습니다. 특히 URL이 "/ resources /", "/ signup"과 같거나 "/ about"인 경우 모든 사용자가 요청에 액세스 할 수 있습니다.
	 * 
	 * 
	 * .antMatchers ( "/ admin / **" ) .hasRole ( "ADMIN" )   
	 * 	"/ admin /"으로 시작하는 URL은 "ROLE_ADMIN"역할을 가진 사용자로 제한됩니다. 우리는이 hasRole메소드 를 호출하기 때문에 "ROLE_"접두어를 지정할 필요가 없다는 것을 알 수 있습니다.
	 * 
	 * 
	 *  .antMatchers ( "/ db / **" ) .access ( "hasRole ( 'ADMIN') 및 hasRole ( 'DBA')" )
	 * 	"/ db /"로 시작하는 URL은 사용자가 "ROLE_ADMIN"과 "ROLE_DBA"를 모두 가지고 있어야합니다. 당신은 우리가 사용하고 있기 때문에 것을 알 hasRole표현을 우리는 "ROLE_"접두사를 지정할 필요가 없습니다.
	 * 
	 * 
	 *  .anyRequest (). authenticated () 
	 * 아직 일치하지 않은 URL은 사용자 만 인증해야합니다.
	 * 
	 * 
	 * 
	 * .logoutUrl ( "/ my / logout" )
	 * 로그 아웃을 발생시키는 URL입니다 (기본값은 /logout). CSRF 보호가 활성화 된 경우 (기본값) 요청은 POST 여야합니다. 자세한 내용은 JavaDoc을 참조하십시오 
	 * 
	 * .logoutSuccessUrl ( "/ my / index" )  
	 * 로그 아웃 후 리디렉션 할 URL입니다. 기본값은 /login?logout입니다.
	 * 
	 * .logoutSuccessHandler (logoutSuccessHandler) 
	 * 	사용자 정의를 지정합시다 LogoutSuccessHandler. 이것이 지정되면 logoutSuccessUrl()무시됩니다. 
	 * 
	 * .invalidateHttpSession (true)
	 * 	HttpSession로그 아웃 할 때 무효화할지 여부를 지정하십시오 . 이것은 기본적으로 true 입니다.
	 * 
	 * .addLogoutHandler (logoutHandler) 
	 * a LogoutHandler.를 추가합니다 . 기본적으로 SecurityContextLogoutHandler마지막 LogoutHandler으로 추가됩니다 .
	 * 
	 * .deleteCookies (cookieNamesToClear)  
	 * 	로그 아웃 성공시 삭제할 쿠키의 이름을 지정할 수 있습니다. 이것은 CookieClearingLogoutHandler명시 적으로 추가하는 지름길입니다 .
	 * 
	 * 
	 * 
	 */
	
	
	/*
	 * import시 crypto.password로 처리할 것 
	 */
	 @Bean
    public PasswordEncoder passwordEncoder() {
	        log.info("=========================================");
	        log.info("=========================================");
	        log.info("===================passwordEncoder=================");
	        log.info("=========================================");
	        log.info("=========================================");
        return new BCryptPasswordEncoder();
    }

	/*
	 * 우리는 데이터베이스에 저장된 회원으로 인증을 처리할 예정이니 configureGlobal 메소드에서 그 부분을 처리한다. 
	 * 만약 패스워드를 암호화할 예정이면 authenticationProvider.setPasswordEncoder(passwordEncoder()) 을 사용하면 된다.
	 */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        log.info("=========================================");
        log.info("=========================================");
        log.info("===================configureGlobal=================");
        log.info("=========================================");
        log.info("=========================================");
        auth.authenticationProvider(authenticationProvider());
    }

    /*
     * 데이터베이스 인증용 Provider
     * @return
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        log.info("=========================================");
        log.info("=========================================");
        log.info("===================DaoAuthenticationProvider=================");
        log.info("=========================================");
        log.info("=========================================");
    	DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailService);
       // authenticationProvider.setPasswordEncoder(passwordEncoder()); //패스워드를 암호활 경우 사용한다
        return authenticationProvider;
    }
    
    
}
