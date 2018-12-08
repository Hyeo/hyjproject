<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h1>Login Success!!!</h1>
    <h2>${user.username} 님 환영합니다. </h2>
    <p></p>
    <h3><a href="${pageContext.request.contextPath}/home">home</a></h3>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <h3><a href="javascript:alert('관리자군요.')";>관리자만 보임</a></h3>
    </sec:authorize>
        <sec:authorize access="hasRole('ROLE_USER')">
        <h3><a href="javascript:alert('일반유저입니다.')";>일반유저만 보임</a></h3>
    </sec:authorize>
    <h3><a href="${pageContext.request.contextPath}/logout">logout</a></h3>
</body>
</html>