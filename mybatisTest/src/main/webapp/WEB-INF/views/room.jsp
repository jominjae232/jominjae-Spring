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
    <style>
    .bodytext_area {
    position: absolute;
    left: 500px;
    top: 50px;
	}
    </style>
    
                    </a>
                </p>
                <div class="header_cont">
                    <ul class="util clear">
                    	<button onclick="location.href='booking'" style="color: blue;">예약관리</button>
                    	<button onclick="location.href='room'">객실관리</button>
                    	<button onclick="location.href='logout'" style="color: blue;">로그아웃</button>
                    </ul>	
                    <nav>
                </div>
            </div>
        </header>
        <form>
        <div id="container">
            <div class="location_area customer">
                <div class="box_inner">
                    <h2 class="tit_page">객실목록</h2>
                    <table border="1">
                    	<select size=10 style="width: 250px;">
							<c:forEach items="${list}" var="room">
								<option>${room.roomcode},${room.name},${room.type},${room.howmany},${room.howmuch}</option>
							</c:forEach>
						</select>
                        </table>
                    </ul>
                </div>
            </div>
			</form>
			
            <div class="bodytext_area box_inner">
                <!-- 검색폼영역 -->
                <form id="search_form" name="search_form" action="board_list.html" class="minisrch_form">
                    <fieldset>
                        <legend>객실이름 검색</legend>
                        <input name="search_keyword" type="text" class="tbox" title="객실 이름을 입력해주세요" placeholder="객실 이름을 입력해주세요">
                        <button class="btn_srch">검색</button>
                    </fieldset>
                </form>
                <!-- //검색폼영역 -->
                <h3>객실분류</h3>
                <!-- 호텔 관리 영역 -->
                </form>
                <table class="bbsListTbl">
                    
                    <select size=10 style="width: 250px;">
						<c:forEach items="${list_type}" var="type">
							<option>${type.typecode},${type.name}</option>
						</c:forEach>
				</select>
				<tr>
					<td>
						<a>타입</a>&nbsp;
						<select size="5" style="width: 120px;" id="selType">
						</select>
					</td>
				</tr>
				<tr>
					<td>
						<a>숙박 가능 인원</a>&nbsp;<input type="number" name="Man" size="20">(명)<br>
					</td>
				</tr>
				<tr>
					<td>
						<a>1박 요금</a>&nbsp;<input type="number" name="Won" size="20">(원)
					</td>
				</tr>
                </table>
                <p class="btn_line">
                    <button type="submit" style="background-color: greenyellow;" href="#" class="btn_baseColor1">등록</button>
                    <button type="reset" style="background-color: rgb(236, 131, 11);" href="#" class="btn_baseColor2">삭제</button>
                    <button type="reset" style="background-color: red;" href="#" class="btn_baseColor3">Clear</button>
                </p>
                </form>
                <!-- //호텔 관리 영역 -->
           </div>
    </body>
</html>