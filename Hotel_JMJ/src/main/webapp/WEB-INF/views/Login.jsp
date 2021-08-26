<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
<form method="get" action="/app/viewinfo">
	로긴아이디: <input type="text" name=userid><br>
	비밀번호: <input type="password" name=password1><br>
	<button onclick="location.href='viewinfo'" style="background-color: green;">확인</button>
</form>
</body>
</html>