<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://cdn.ckeditor.com/4.8.0/full-all/ckeditor.js"></script>
<title>Insert title here</title>
</head>
<body>
<form action="smartupload" method="post" id="insertBoardFrm" enctype="multipart/form-data">
	<textarea name="editor" id="editor1" cols="50" rows="10" class="form-control" placeholder="내용을 입력하세요"></textarea>
	<input type="submit" id="insertBoard" value="등록" />
</form>
	                          
                                  
<script>
//CKEditor 표시
CKEDITOR.replace( 'editor1', {
    language: 'ko',
    toolbar: [
        ['Source','-','Save','NewPage','Preview','-','Templates'],
         ['Cut','Copy','Paste','PasteText','PasteFromWord','-','Print', 'SpellChecker', 'Scayt'],
         ['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],
         ['Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton', 'HiddenField'],
         '/',
         ['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
         ['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'],
         ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
         ['Link','Unlink','Anchor'],
         ['Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],
         '/',
         ['Styles','Format','Font','FontSize'],
         ['TextColor','BGColor'],
         ['Maximize', 'ShowBlocks','-','About']
    ],

    filebrowserUploadUrl: "${pageContext.request.contextPath}/file/upload/CKEditor",

} );


</script>
</body>
</html>