<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	이름: <input type="text" name=username id=txtName><br>
	로긴아이디: <input type="text" name=userid id=txtId><br>
	비밀번호: <input type="password" name=passcode id=passcode><br>
	비밀번호 확인: <input type="password" name=passcode2><br>
	<input type=button value="등록" id=btnAdd>
	<button onclick="location.href='home'">취소</button>
</body>
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script>
$(document)
.on('click','#btnAdd',function(){
   console.log("debug btn")
   let txtName=$('#txtName').val();
   let txtId=$('#txtId').val();
   let passcode=$('#passcode').val();
   let passcode2=$('#passcode2').val();
   if(txtName=='' || txtId=='' || passcode=='' || passcode != passcode2) {
      alert('누락된 값이 있거나 비밀번호 확인이(가) 알맞지 않습니다');
      return false;
   }
   $.post('http://localhost:8080/app/signin',
         {txtName:txtName,txtId:txtId,passcode:passcode},
         function(result){
            if(result=='ok') {
               location.reload();
            }
         },'text');
})
</script>
</html>