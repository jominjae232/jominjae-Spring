<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원</title>
</head>
<body>
<p>유저이름: ${username}</p>
<p>유저아이디: ${userid}</p>
<p>유저 비밀번호${password1}</p>
<p>유저 비밀번호 확인:${password2}</p>
<p>모바일: ${mobile}</p>
<button onclick="location.href='home'">돌아가기</button>
</body>
</html>