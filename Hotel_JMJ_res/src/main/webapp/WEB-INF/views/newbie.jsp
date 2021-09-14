<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/app/resources/css/home.css">
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body class="hbody" style="margin: 320px 280px"> 
<form method="post" action="/app/signin" id="frmSignin"> 
호텔 이름:<input type=text name=txtname><br>
아이디:<input type=text name="login"><br>
비밀번호:<input type=password name=passcode ><br>
비밀번호 확인:<input type=password name=passcode2 ><br>
<input type=submit value="회원가입 등록">
<a href='/app/'>로그인</a>
</form>

</body>
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script>
$(document)
.on('submit','#frmSignin',function(){
  if($('input[name=txtname]').val()==''){
	  alert('이름을 입력하시오');
	  return false;
  }
  if($('input[name=login]').val()==''){
	  alert('id를 입력하시오');
	  return false;
  } 
  if($('input[name=passcode]').val()==''){
	  alert('비밀번호를 입력하시오');
	  return false;
  }
  if($('input[name=passcode]').val()!=$('input[name=passcode2]').val()){
	  alert('비밀번호가 일치하지 않습니다');
	  return false;
  }
  alert('회원가입 성공!');
  return true;
});
</script>
</html>