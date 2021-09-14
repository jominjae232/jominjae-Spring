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
    <title>예약관리</title>
</head>
<body class="b_body">

<button onclick="location.href='booking'" >예약관리</button>
<button onclick="location.href='room'" style="color: blue;">객실관리</button>
<button onclick="location.href='logout'" style="color: blue;">로그아웃</button>

<table border="1" style="margin: 190px 95px; color: black; background-color: gray;">
<tr>
	

	
	<td align=center>예약가능객실<br><br><br>예약일자
	<br><input type="date" id="checkin1">~<br><input type=date id=checkout1><br>
	<input type=button id=btnFind value="조회">
	</td>
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
		<table style="height: 70px;">
		<tr>
			<td align=right>객실명</td>
			<td><input type=text id=txtName><input type=hidden id=bookcode></td>
		</tr>
		<tr>
			<td align="right">객실종류</td>
				<td>
					<select size="1" style="width: 120px" id=selType>
					<option value="1">suite Room</option>
					<option value="2">Family Room</option>
					<option value="3">Double Room</option>
					<option value="4">Single Room</option>
					<!--
						<c:forEach items="${list_type}" var="roomtype">
							<option value="${roomtype.typecode}">${roomtype.name}</option>
						</c:forEach>
						  -->
					</select>
				</td>
			</tr>
			<tr>
				<td align=right>최대인원</td><td><input type=number id=txtNum min=1></td>
			</tr>
			<tr>
				<td align=right>예약인원</td><td><input type=number id=human min=1></td>
			</tr>
			<tr>
				<td align=right>예약기간</td><td><input type=date id=checkin>~<tr>
				<td align=right></td><td><input type=date id=checkout></td>
			</tr></td>
			</tr>
			<tr>
				<td align=right>총액</td><td><input type=number id=txtPrice min=1></td>
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
		
		<select size=20 style="width: 300px; overflow-x: scroll;" id="selBookingList">
		
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
	// 디버그용 ready
})
	
	// 찾기버튼 클릭 액션하는 부분 (예약 가능 객실)
	.on('click','#btnFind',function(){ 
		$.post("http://localhost:8080/app/getBookingList",
				{checkin:$('#checkin1').val(),checkout:$('#checkout1').val()},
				function(result){
				console.log(result);
				$('#selRoom').empty();
				$.each(result,function(ndx,value){ // == for(i=0;i<result.length;i++){}
					str='<option value="'+value['roomcode']+'">'+value['roomname']+','+
						value['typename']+','+value['howmany']+','+value['howmuch']+'</option>';
					$('#selRoom').append(str);
					
				});
			},'json');
})
//찾기버튼 클릭 액션하는 부분 (예약 가능 객실)

// 찾기버튼 클릭 액션하는 부분(예약된 객실)
.on('click','#btnFind',function(){
$.post("http://localhost:8080/app/getBookList",
		{checkin:$('#checkin1').val(),checkout:$('#checkout1').val()},
		
function(result){
	console.log(result);
	$('#selBookingList').empty();
	$.each(result,function(ndx,value){ // == for(i=0;i<result.length;i++){}
		str='<option value="'+value['bookcode']+'">'+value['roomname']+","+value['type']+','+
		value['human']+","+value['txtNum']+','+value['checkin']+','+value['checkout']+","+value['total']+','+value['name']+','+value['mobile']+'</option>';
		$('#selBookingList').append(str);
	});
},'json');
return false;
})
// 찾기버튼 클릭 액션하는 부분(예약된 객실)

.on('click','#selRoom option',function(){
	$('#roomcode').val($(this).val());
	let str_room=$(this).text();
	console.log(str_room);
	let ar=str_room.split(',');
	$('#txtName').val($.trim(ar[0]));
	console.log('['+ar[1]+']');
	$('#selType option:contains("'+$.trim(ar[1])+'")').prop('selected',true); //attr / prop | 'selected' / 'true' 
	$('#txtNum').val($.trim(ar[2]));
	$('#txtPrice').val($.trim(ar[3]));
	$('#checkin').val($('#checkin1').val());
	$('#checkout').val($('#checkout1').val());
	
	$('#checkin,#checkout').trigger('change');
	return false;
})


//예약된 객실 클릭시 selBookingList에 있는 값을 왼쪽의 테이블에 입력 코드
.on('click','#selBookingList option',function(){
	$('#bookcode').val($(this).val());
	let str_book=$(this).text();
	let ar=str_book.split(',');
	$('#txtName').val($.trim(ar[0]));
	//console.log('['+ar[1]+']');
	$('#selType option:contains("'+$.trim(ar[1])+'")').prop('selected',true); //attr / prop | 'selected' / 'true' 
	$('#human').val($.trim(ar[2]));
	$('#txtNum').val($.trim(ar[3]));
	$('#checkin').val($.trim(ar[4]));
	$('#checkout').val($.trim(ar[5]));
	$('#txtPrice').val($.trim(ar[6]));
	$('#txtNames').val($.trim(ar[7]));
	$('#txtmobile').val($.trim(ar[8]));
	return false;
})


.on('click','#btnEmpty',function(){
	$('#txtName,#bookcode,#selType,#human,#txtNum,#checkin,#checkout,#txtPrice,#txtNames,#txtmobile').val('');
	return false;
})

.on('click','#btnDelete',function(){
	$.post('http://localhost:8080/app/deleteBooking',{bookcode:$('#bookcode').val()},
			function(result){
		console.log(result);
		if(result=="ok") {
			$('#btnEmpty').trigger('click'); //입력란 비우기
			$('#selBookingList option:selected').remove();
		}
	},'text');
	return false;
})
 
.on('click','#btnAdd',function(){
	let roomname=$('#txtName').val();
	let roomcode=$('#selType').val();
	let txtNum=$('#txtNum').val();
	let human=$('#human').val();
	let checkin=$('#checkin').val();
	let checkout=$('#checkout').val();
	let total=$('#txtPrice').val();
	let txtName=$('#txtNames').val();
	let txtmobile=$('#txtmobile').val(); 
	// validation(유효성 검사)
	if(roomname=='' || roomcode=='' || txtNum=='' || human=='' || checkin=='' || checkout=='' || total=='' || txtName=='' || txtmobile=='') {
		alert('누락된 값이 있습니다. 다시 입력해주세요!');
		return false;
	}
	let bookcode=$('#bookcode').val();
	if(bookcode==''){
		$.post('http://localhost:8080/app/addbooking',
				{roomname:roomname,roomcode:roomcode,txtNum:txtNum,human:human,checkin:checkin,checkout:checkout,total:total,txtName:txtName,txtmobile:txtmobile},
				function(result){
					if(result=='ok') {
						location.reload();
					}
				},'text');
			alert("추가 완료!")
			return false;
		} else {
			$.post('http://localhost:8080/app/updatebooking',
					{bookcode:bookcode,human:human,txtNames:txtName,txtmobile:txtmobile},
					function(result){
						if(result=='ok') {
							location.reload();
						}
					},'text');
				}
				alert("업데이트 완료!")
				return false;
			})
		
	.on('change','#checkin,#checkout',function(){
		//총숙박비 계산
		let checkin1=$('#checkin').val();
		let checkout1=$('#checkout').val();
		//console.log('checkin1 ['+checkin1+'] checkout1 ['+checkout1+']');
		if(checkin1=='' || checkout1=='') return false;
		checkin1=new Date(checkin1);
		checkout1=new Date(checkout1);
		if(checkin1 > checkout1) {
			alert('체크인 날짜가 체크아웃보다 나중일 수 없습니다.'); return false;
		}
		let ms=Math.abs(checkout1-checkin1);
		let days=Math.ceil(ms/(1000*60*60*24));
		let total = days*parseInt($('#txtPrice').val());
		$('#txtPrice').val(total);
		console.log(total);
		return false;
	})
</script>
</html>