<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
<form method="POST" action="/app/newinfo">
	이름: <input type="text" name=username><br>
	로긴아이디: <input type="text" name=userid><br>
	비밀번호: <input type="password" name=password1><br>
	비밀번호 확인: <input type="password2" name=password2><br>
	모바일: <input type="text" name=mobile><br>
	<input type=submit value="확인" location.href='newinfo' style="background-color: green;">
</form>
<button onclick="location.href='home'" style="background-color: red">취소</button>
<button onclick="location.href='Login'" style="background-color: blue;">로긴하기</button>
</body>
</html>