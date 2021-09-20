<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/app/resources/css/home.css">
<meta charset="UTF-8">
<title>회원가입</title>
</head>

<body class="hbody" style="margin: 280px 800px"> 
<form method="post" action="/app/signin" id="frmSignin">
<table border="0" style="width: 300px; height: 400px; background-color: LightYellow; border-radius: 30px;">
<td style="position: absolute; top:295px;left: 895px; font-size: 24px;">회원가입</td>
	<td>
호텔 이름:<input type=text name=txtname style="font-size: 23px;"><br>
아이디:<input type=text name="login" style="font-size: 23px;"><br>
비밀번호:<input type=password name=passcode style="font-size: 23px;"><br>
비밀번호 확인:<input type=password name=passcode2 style="font-size: 23px;"><br><br>
<input type=submit value="회원가입" style="color: blue; padding: 8px; border-radius: 10px;">
<a href='/app/' style="font-size: 20px; color: #957745	; text-decoration:none">취소</a>
</td>
</table>
</form>
</body>
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script>
$(document)
.on('submit','#frmSignin',function(){
  if($('input[name=txtname]').val()==''){
	  alert('호텔 이름을 입력하시오');
	  return false;
  }
  if($('input[name=login]').val()==''){
	  alert('아이디를 입력하시오');
	  return false;
  } 
  if($('input[name=passcode]').val()==''){
	  alert('비밀번호를 입력하시오');
	  return false;
  }
  if($('input[name=passcode]').val()!=$('input[name=passcode2]').val()){
	  alert('비밀번호와 비밀번호 확인이 일치하지 않습니다');
	  return false;
  }
  alert('회원가입 성공!');
  return true;
});

</script>
</html>