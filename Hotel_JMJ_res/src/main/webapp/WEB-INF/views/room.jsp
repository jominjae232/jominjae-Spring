<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" href="/app/resources/css/home.css">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>객실관리</title>
</head>
<body class="r_body">

<button onclick="location.href='booking'" style="color: blue; padding: 7px; border-radius: 20px;">예약관리</button>
<button onclick="location.href='room'" style="padding: 7px; border-radius: 20px;">객실관리</button>
<button onclick="location.href='logout'" style="color: blue; padding: 7px; border-radius: 20px;">로그아웃</button>

<!-- <table border="1" style="margin: 180px 580px; color: black; background-color: gray; height: 500px; width: 700px;">
<tr>
 -->
	<table border="0" style="width: 400px; height:55px; position: absolute; top: 265px; left: 470px; border-radius: 10px; background-color: MistyRose;">
	<td align=center>객실 목록</td>
	</table>
	
	
		<select size=19 style="width: 400px; position: absolute; top: 320px; left: 470px; font-size: 16px;" id="selRoom">
	
		<!--  
				<c:forEach items="${list}" var="room">
					<option value='${room.roomcode}'>${room.roomname},${room.typename},${room.howmany},${room.howmuch}</option>
				</c:forEach>
		-->
		</select>
	
	
		<table border="0" style="background-color: BlanchedAlmond; position: absolute; top: 240px; left: 1060px; width: 450px; height: 530px; border-radius: 10px;">
		<tr>
			<td align=right>객실명</td>
			<td><input type=text id=txtName style="width: 240px; height: 28px; border-radius: 9px;"><input type=hidden id=roomcode></td>
		</tr>
		<tr>
			<td align="right">객실종류</td>
				<td>
					<select size="1" style="width: 240px; height: 28px; border-radius: 9px;" id=selType>
						<c:forEach items="${list_type}" var="type">
							<option value="${type.typecode}">${type.name}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td align=right>최대숙박인원</td><td><input type=number id=txtNum style="width: 240px; height: 28px; border-radius: 9px;"></td>
			</tr>
			<tr>
				<td align=right>1박 가격</td><td><input type=number id=txtPrice  style="width: 240px; height: 28px; border-radius: 9px;"></td>
			</tr>
			<tr>
				<td colspan=2 align=center>
					<input type=button value='등록' id=btnAdd style="padding: 11px; color: blue; border-radius: 10px;">
					<input type=button value='삭제' id=btnDelete style="padding: 11px; color: red; border-radius: 10px;">
					<input type=button value='취소' id=btnEmpty style="padding: 11px; border-radius: 10px;">
				</td>
			</tr>
			</table>
</body>
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script>
/*JSON
	객실목록을 출력하는 코드입니다.
 */
$(document)
.ready(function(){
	$.post("http://localhost:8080/app/getRoomList",{},function(result){
		console.log(result);
		$.each(result,function(ndx,value){ // == for(i=0;i<result.length;i++){}
			str='<option value="'+value['roomcode']+'">'+value['roomname']+','+
				value['typename']+','+value['howmany']+','+value['howmuch']+'</option>';
			$('#selRoom').append(str);
		});
	},'json');
})
/* 객실 목록을 출력하는 코드입니다. */
 
.on('click','#selRoom option',function(){
	let str_room=$(this).text();
	let ar=str_room.split(',');
	$('#txtName').val($.trim(ar[0]));
	$('#selType option:contains("'+$.trim(ar[1])+'")').prop('selected',true); //attr / prop | 'selected' / 'true' 
	$('#txtNum').val($.trim(ar[2]));
	$('#txtPrice').val($.trim(ar[3]));
	let code=$(this).val();
	$('#roomcode').val(code);
	return false;
})

.on('click','#btnEmpty',function(){
	$('#txtName,#txtNum,#txtPrice,#roomcode,#selType').val('');
	return false;
})

.on('click','#btnDelete',function(){
	$.post('http://localhost:8080/app/deleteRoom',{roomcode:$('#roomcode').val()},
			function(result){
		console.log(result);
		if(result=="ok") {
			$('#btnEmpty').trigger('click'); //입력란 비우기
			$('#selRoom option:selected').remove(); //room리스트에서 제거
		}
	},'text');
	return false;
})

/* 객실관리 목록 추가 & 업데이트 코드 */
.on('click','#btnAdd',function(){
	let roomname=$('#txtName').val();
	let roomtype=$('#selType').val();
	let howmany=$('#txtNum').val();
	let howmuch=$('#txtPrice').val();
	// validation(유효성 검사)
	if(roomname=='' || roomtype=='' || howmany=='' || howmuch=='') {
		alert('누락된 값이 있습니다.');
		return false;
	}
	let roomcode=$('#roomcode').val();
	if(roomcode=='') { //insert
		$.post('http://localhost:8080/app/addRoom',
				{roomname:roomname,roomtype:roomtype,howmany:howmany,howmuch:howmuch},
				function(result){
					if(result=='ok') {
						location.reload();
					}
				},'text');
	} else { //update
		$.post('http://localhost:8080/app/updateRoom',
				{roomcode:roomcode,roomname:roomname,
				roomtype:roomtype,howmany:howmany,
				howmuch:howmuch},
				function(result){
					if(result=='ok') {
						location.reload();
					}
				},'text');
			}
	})
	/* //객실 관리 목록 추가 & 업데이트 코드 */
</script>
</html>