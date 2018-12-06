<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>
      <ul>
          <li>로그인이 되어 있어야 이 페이지로 접근할 수 있습니다</li>
          <li><a href="${pageContext.request.contextPath}/">홈으로.</a></li>
      	<li><a href="${pageContext.request.contextPath}/excelDown">엑셀다운</a></li>
      </ul>

	<form method="POST" action="${pageContext.request.contextPath}/readExcel"  enctype="multipart/form-data">
		<input type="file" name="weeklyFile">
		<button type="submit">Sign in</button>
	</form>
<P>  The time on the server is ${serverTime}. </P>
<c:forEach items="${hyjVo}" var="hyjVo">
	<p>${hyjVo.name }</p>
	<p>${hyjVo.num }</p>
</c:forEach>
</body>
</html>
