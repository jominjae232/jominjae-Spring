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

<button onclick="location.href='booking'" style="color: blue;">예약관리</button>
<button onclick="location.href='room'">객실관리</button>
<button onclick="location.href='logout'" style="color: blue;">로그아웃</button>

<table border="1">
<tr>
	<td align=center>객실 목록</td><td>
		<select size=10 style="width: 250px;" id="selRoom">
				<c:forEach items="${list}" var="room">
					<option value='${room.roomcode}'>${room.roomname},${room.typename},${room.howmany},${room.howmuch}</option>
				</c:forEach>
		</select>
	</td>
	<td>
		<table>
		<tr>
			<td align=right>객실명</td>
			<td><input type=text id=txtName><input type=hidden id=roomcode></td>
		</tr>
		<tr>
			<td align="right">타입</td>
				<td>
					<select size="5" style="width: 120px" id=selType>
						<c:forEach items="${list_type}" var="type">
							<option value="${type.typecode}">${type.name}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td align=right>최대숙박인원</td><td><input type=number id=txtNum></td>
			</tr>
			<tr>
				<td align=right>1박 가격</td><td><input type=number id=txtPrice></td>
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
	</tr>
</table>
</body>
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script>
$(document)
.on('click','#selRoom option',function(){
	let str=$(this).text();
	//console.log(str);
	let ar=str.split(',');
	$('#txtName').val($.trim(ar[0]));
	//console.log('['+ar[1]+']');
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

</script>
</html>