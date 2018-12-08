<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/signin.css">
</head>
<!-- 로그인 화면 단  -->
<body>
<div class="container">
    <form class="form-signin" method="POST" action="${pageContext.request.contextPath}/login">
        <h2 class="form-signin-heading">Please sign in</h2>
        <c:if  test = "$ {param.error! = null}" >        
        	<p>
            	사용자 이름과 비밀번호가 잘못되었습니다.
        	</p>
    	</c:if> 
        <label for="" class="sr-only">ID</label>
        <input type="text" name="username" class="form-control" placeholder="ID" required autofocus>
        <label for=""  class="sr-only">Password</label>
        <input type="password" name="password" class="form-control" placeholder="Password" required>
        <div class="checkbox">
            <label>
                <input type="checkbox" value="remember-me"> Remember me
            </label>
        </div>
         <input type="hidden" 
		 name="${_csrf.parameterName}"
		 value="${_csrf.token}"/>
		        
        <!--input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"-->
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    </form>

</div> <!-- /container -->
</body>
</html>
