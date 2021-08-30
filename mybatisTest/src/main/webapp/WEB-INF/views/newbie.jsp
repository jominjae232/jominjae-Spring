<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
<form method="POST" action="/app/home">
	이름: <input type="text" name=username><br>
	로긴아이디: <input type="text" name=userid><br>
	비밀번호: <input type="password" name=passcode><br>
	비밀번호 확인: <input type="password" name=passcode2><br>
	모바일: <input type="text" name=mobile><br>
	<input type=submit value="등록" location.href='home'>
	<button onclick="location.href='home'">취소</button>
</form>
</body>
</html>