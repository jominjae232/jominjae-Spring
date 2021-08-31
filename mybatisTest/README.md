#### 선생님 room.jsp 코딩

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
		<select size=10 style="width: 250px;">
				<c:forEach items="${list}" var="room">
					<option>${room.roomcode},${room.name},${room.type},${room.howmany},${room.howmuch}</option>
				</c:forEach>
		</select>
	</td>
	<td>
		<table>
		<tr>
			<td>객실명</td><td>
				<select size=10 style="width: 250px;">
						<c:forEach items="${list1}" var="room">
							<option>${room.name}</option>
						</c:forEach>
				</select>
				</td>
		</tr>
		<tr>
			<td>타입</td>
				<td>
					<select size="5" style="width: 120px" id=txtNum>
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
</html>