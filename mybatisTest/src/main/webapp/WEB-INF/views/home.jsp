<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
<form method="POST" action="/app/check_user" id="frmLogin" style="margin: 300px 200px">
	로긴아이디: <input type="text" name=userid><br>
	비밀번호: <input type="password" name=passcode><br>
	<input type=submit value="로그인">
	<input type="reset" value="취소">
	<a href='/app/newbie'>회원가입</a>
</form>
</body>
<style>
    body{
     background-image: url('./home.jpg');
     }
</style>
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
		alert('로긴아이디를 입력하시오.');
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