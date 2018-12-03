package com.hyj.controller;



import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hyj.services.HyjService;
import com.hyj.utill.HyjCode;
import com.hyj.vo.HyjVo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HyjController {

	@Autowired
	HyjService HyjService;
		
	@RequestMapping("tile")
	public String tile(){
		
		return "tile/tile";
	}
	
	//@value test 시범
	@Value("#{test.VALUE1}") 
	String gTestDb1; //프로퍼티값을 읽어드림

	
	//전체리스트 가져오기
	@RequestMapping("select")
	public String view(Model model) {
		
		List<HyjVo> hyjVo = HyjService.select();
		model.addAttribute("hyjVo", hyjVo);
		return "home";
	}
	
	
	//loobok을 이용한 처리
	@RequestMapping("logtest")
	public String logtest() {
		log.trace("trace");
		log.debug("debug");
		log.info("info");
		log.warn("warn");
		log.error("error");
		return "home";
	}
	
	@RequestMapping("valuetest")
	public String valuetest() {
		log.info(gTestDb1);
		return	"home";
	}
	
	
	@RequestMapping("assertjtest")
	public void assertjtest() {
		
	}
	
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
	@org.junit.Before // 각 테스트마다 인스턴스가 매번 다시 생성되어 독립적인 테스트 가능
	public void before() {
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
		log.info("test2");
	}
	
	/*
	 * Junit test random 으로 실행
	 * 무조건 void로 선언할 것
	 * 순서 설정 가능하니 인터넷 검색
	 */
	@Test
	public void test3() {
		log.info("test3");
	}

	/*
	 * Junit After는 test가 실행한 후 각 1번씩 실행 test가 많을수록 많이 실행
	 * 무조건 void로 선언할 것
	 */
	@org.junit.After // 후처리 작업
	public void after() {
		log.info("after");
	}
	
	/*
	 * Junit After는 test가 실행한 후 각 1번씩 실행 test가 많을수록 많이 실행
	 * 무조건 void로 선언할 것
	 */
	@AfterClass // 후처리 작업
	public static void afterClass() {
		log.info("@afterClass");
	}
	
	/*
	 * 404 error 발생시 이 페이지로 이동
	 */
	@RequestMapping("error/404")
	public String error(Model model) {
		log.info("404error발생");
		model.addAttribute("msg","접근이 금지되었습니다.");
		return "error/errorPage";
	}

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
	
	@RequestMapping("smartupload")
	public String smartupload(String editor,Model model){
		model.addAttribute("editor", editor);
		return "smartupload";
	}
	
	@RequestMapping("ckeditor")
	public String ckeditor(){
		
		return "ckeditor";
	}	
	
	@PostMapping(value="/file/upload/CKEditor")
    @ResponseBody
    public String uploadFileCKEditor(MultipartHttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile upload) throws Exception {
		

		StringBuffer sb = new StringBuffer();
		String url="";
		OutputStream out = null;
        PrintWriter printWriter = null;
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
 
        try{
 
            String fileName = upload.getOriginalFilename();
            String saveName = sb.append(new SimpleDateFormat("yyyyMMddHHmmss")
                    .format(System.currentTimeMillis()))
                    .append(UUID.randomUUID().toString())
                    .append(fileName.substring(fileName.lastIndexOf("."))).toString();
            
            byte[] bytes = upload.getBytes();
            String uploadPath = "/upload/" + saveName;//저장경로
 
            out = new FileOutputStream(new File(uploadPath));
            out.write(bytes);
            
            String callback = request.getParameter("CKEditorFuncNum");
 
            //printWriter = response.getWriter();
            String fileUrl = "http://localhost:8090/hyjproject/resources/photoUpload/" + saveName;//url경로
            url="<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction("
                    + callback
                    + ",'"
                    + fileUrl
                    + "','SUCCES'"
                    + ")</script>";
            
        }catch(IOException e){ 
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (printWriter != null) {
                    printWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
 
        return url;
    }
	
}
