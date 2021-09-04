<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
<from method=POST action="/app/signin" id="frmSignin">
	이름: <input type="text" name=realname><br>
	로긴아이디: <input type="text" name=userid><br>
	비밀번호: <input type="password" name=passcode1><br>
	비밀번호 확인: <input type="password" name=passcode2><br>
	<input type=submit value="등록">
	<a href='/app/'>홈페이지 이동</a>
	</from>
</body>
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script>
$(document)
.on('submit','#frmSignin',function(){
	console.log("debug_btn")
	if($('input[name=realname]').val()=="") {
		alert("이름을 입력하시오.");
		return false;
	}
	if($('input[name=userid]').val()=="") {
		alert("로긴아이디를 입력하시오.");
		return false;
	}
	if($('input[name=passcode1]').val()=='') {
		alert('비밀번호를 입력하시오.');
		return false;
	}
	if($('input[name=passcode1]').val()!=$('input[name=passcode2]').val()) {
		alert('비밀번호가 일치하지 않습니다.');
		return false;
	}
	return false;
});
</script>
</html>