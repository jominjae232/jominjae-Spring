<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
<form method="POST" action="/app/check_user">
	로긴아이디: <input type="text" name=userid><br>
	비밀번호: <input type="password" name=passcode><br>
	<button onclick="location.href='check_user'">로그인</button>
	<input type="reset" value="취소">
</form>
<button onclick="location.href='newbie'">회원가입</button>
</body>
</html>