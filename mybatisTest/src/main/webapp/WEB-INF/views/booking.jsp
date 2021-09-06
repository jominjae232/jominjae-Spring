<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>객실관리</title>
</head>
<body>

<button onclick="location.href='booking'" >예약관리</button>
<button onclick="location.href='room'" style="color: blue;">객실관리</button>
<button onclick="location.href='logout'" style="color: blue;">로그아웃</button>

<table border="1">
<tr>
	<td align=center>예약가능객실</td>
	<td>
		<select size=10 style="width: 250px;" id="selRoom">
		<!--  
				<c:forEach items="${list}" var="room">
					<option value='${room.roomcode}'>${room.roomname},${room.typename},${room.howmany},${room.howmuch}</option>
				</c:forEach>
		-->
		</select>
	</td>
	
	
	
	<td>
		<table>
		<tr>
			<td align=right>객실명</td>
			<td><input type=text id=txtName><input type=hidden id=bookcode></td>
		</tr>
		<tr>
			<td align="right">객실종류</td>
				<td>
					<select size="5" style="width: 120px" id=selType>
						<c:forEach items="${list_types}" var="type">
							<option value="${type.typecode}">${type.name}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td align=right>예약인원</td><td><input type=number id=human></td>
			</tr>
			<tr>
				<td align=right>최대숙박인원</td><td><input type=number id=txtNum></td>
			</tr>
			<tr>
				<td align=right>예약기간</td><td><input type=text id=checkin>~<tr>
				<td align=right></td><td><input type=text id=checkout></td>
			</tr></td>
			</tr>
			<tr>
				<td align=right>숙박비 총액</td><td><input type=number id=txtPrice></td>
			</tr>
			<tr>
				<td align=right>예약자명</td><td><input type=text id=txtNames></td>
			</tr>
			<tr>
				<td align=right>모바일</td><td><input type=text id=txtmobile></td>
			</tr>
			<tr>
				<td colspan=2 align=center>
					<input type=button value='등록' id=btnAdd>
					<input type=button value='삭제' id=btnDelete>
					<input type=button value='취소' id=btnEmpty>
					</td>
			</tr>
			</table>
			</td>
			<td align=center>예약된 객실</td>
		<td>
		<select size=10 style="width: 250px;" id="selBookingList">
		<!--
			<c:forEach items="${list}" var="booking">
				<option value='${booking.bookingcode}'>${booking.roomcode},${booking.human},${booking.checkin},${booking.checkout},${booking.name},${booking.mobile}</option>
			</c:forEach>
		-->
		</select>
	</td>
	</tr>
</table>
</body>
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script>

//JSON
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
	
	$.post("http://localhost:8080/app/getBookingList",{},function(result){
		console.log(result);
		$.each(result,function(ndx,value){ // == for(i=0;i<result.length;i++){}
			str='<option value="'+value['bookcode']+'">'+value['roomcode']+','+
				value['human']+','+value['checkin']+','+value['checkout']+','+value['txtName']+','+value['txtmobile']+'</option>';
			$('#selBookingList').append(str);
		});
	},'json');
})


.on('click','#selRoom option',function(){
	let str_room=$(this).text();
	//console.log(str);
	let ar=str_room.split(',');
	$('#txtName').val($.trim(ar[0]));
	//console.log('['+ar[1]+']');
	$('#selType option:contains("'+$.trim(ar[1])+'")').prop('selected',true); //attr / prop | 'selected' / 'true' 
	$('#txtNum').val($.trim(ar[2]));
	$('#txtPrice').val($.trim(ar[3]));
	let code=$(this).val();
	$('#roomcode').val(code);
	return false;
})

/*
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
*/
 
.on('click','#btnAdd',function(){
	let roomcode=$('#selType').val();
	let human=$('#human').val();
	let checkin=$('#checkin').val();
	let checkout=$('#checkout').val();
	let txtName=$('#txtNames').val();
	let txtmobile=$('#txtmobile').val();
	// validation(유효성 검사)
	if(roomcode=='' || human=='' || checkin=='' || checkout=='' || txtName=='' || txtmobile=='') {
		alert('누락된 값이 있습니다.');
		return false;
	}
		$.post('http://localhost:8080/app/addbooking',
				{roomcode:roomcode,human:human,checkin:checkin,checkout:checkout,txtName:txtName,txtmobile:txtmobile},
				function(result){
					if(result=='ok') {
						//location.reload();
					}
				},'text');
	
	})
</script>
</html>