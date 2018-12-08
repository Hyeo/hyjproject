package com.hyj.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class EditorController {

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
