package com.hyj.controller;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MailController {

	// 자바 메일 발송
	/*
	 * 가장 중요한 것은 보내는 메일 설정에서 POP3/SMTP 허용을 해줘야 간다.
	 * 이것 때문에 고생을 좀 했으니 미리 알아두길(블로그에서 찾아서 썻는데 다들 기본으로 아는지 
	 * 써있지 않았음
	 * 참고로 비번 바꿨어요~
	 */
	@RequestMapping(value = "/mailSender") 
	public String mailSender(HttpServletRequest request, ModelMap mo) throws AddressException, MessagingException { 
		
		Properties p =System.getProperties();	//정보를 담기 위한 객체 생성
		
		//SMTP 서버 정보 설정
		p.put("mail.smtp.host", "smtp.naver.com"); //naver용 host 설정
		p.put("mail.smtp.port", 465); 			// 포트번호 (번호를 알고 싶으면 보낼 메일에서 POP3/SMTP에서 확인
		p.put("mail.smtp.auth", "true"); 		// 권한 설정
		p.put("mail.smtp.ssl.enable", "true"); 	// SLL 권한 설정

		final String username="kkhj7777";		//보낼 메일 아이디
		final String password = "password@"; 	//보낼 메일 비밀번호
		
		//Session 생성
		Session session = Session.getDefaultInstance(p, new javax.mail.Authenticator() { 
			String un=username; 
			String pw=password; 
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() { 
				return new javax.mail.PasswordAuthentication(un, pw); 
			} 
		}); 
		session.setDebug(true); //for debug
		
		MimeMessage msg = new MimeMessage(session); //MimeMessage 생성하고 
		msg.setSentDate(new Date());			// 보내는 날짜 설정
		InternetAddress from = new InternetAddress();
		
		from = new InternetAddress("kkhj7777@naver.com"); //보내는 사람 주소(full email 쓰기)
		msg.setFrom(from);
		
		InternetAddress to=new InternetAddress("kkhj011@gmail.com");	//받는 사람설정
		msg.setRecipient(Message.RecipientType.TO, to);	// 앞에 것은 타입, 뒤에 것은 받는 사람 설정하는 것 넣기
		
		msg.setSubject("이것은 이메일 테스트 제목","UTF-8");	//제목 셋팅 UTF-8로 한글 깨짐 방지
		msg.setText("메일 테스트 내용부분입니다.","UTF-8");		//내용 셋팅 UTF-8로 한글 깨짐 방지
		msg.setHeader("content-Type", "text/html");		//헤더에 type을 설정하여 보냄
		
		javax.mail.Transport.send(msg);					//javax.mail.Transport.send() 이용
		return "home";
	}
}
