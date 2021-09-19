<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" href="/app/resources/css/home.css">
</head>
<body class=hbody>
<form method="POST" action="/app/check_user" id="frmLogin" style="margin: 320px 780px" class="login">
<table border="0" style="width: 280px; height: 270px; background-color: LightYellow; border-radius: 40px;">
<td style="position: absolute; top:330px;left: 895px; font-size: 24px;">LOGIN</td>
	<td>
	아이디: <input type="text" name=userid style="font-size: 23px;"><br>
	비밀번호: <input type="password" name=passcode style="font-size: 23px;"><br><br>
	<input type=submit value="로그인" style="color: blue; padding: 8px; border-radius: 10px;">
	<input type="reset" value="취소" style="color: red; padding: 8px; border-radius: 10px;">
	<a href='/app/newbie' style="font-size: 20px; color: #957745; text-decoration:none">회원가입</a>
	</td>
</table>
</form>
</body>
<script src='https:code.jquery.com/jQuery-3.5.0.js'></script>
<script>
$(document)
.on('submit','#frmLogin',function(){
	let pstr=$.trim($('input[name=userid]').val());
	$('input[name=userid]').val(pstr);
	console.log(pstr);
	pstr=$.trim($('input[name=passcode]').val());
	$('input[name=passcode]').val(pstr);
	console.log(pstr);
	if($('input[name=userid]').val()=='') {
		alert('아이디를 입력하시오.');
		return false;
	}
	if($('input[name=passcode]').val()=='') {
		alert('비밀번호를 입력하시오.');
		return false;
	}
	return true;
})
</script>
</html>