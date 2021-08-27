<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>예약관리</title>
</head>
<body>
<%
	String loginid=(String)session.getAttribute("loginid");
	out.println(loginid);
	if(!loginid.equals("aud")) {
		response.sendRedirect("http://localhost:8080/app/Login");
	}
%>
    <style>
    .bodytext_area {
    position: absolute;
    margin-left: 5px; 
    top: 50px;
	}

    .bodyte_area{
        position: absolute;
        margin-left: 580px;
        top:50px;
    }
    
    .body_area {
        position: absolute;
        margin-left: 965px;
        top: 0px;
    }
    .header_cont {
    position:absolute;
    margin: -55px 20px 30px
    }
    </style>
                    </a>
                </p>
                <div class="header_cont">
                    <ul class="util clear">
                    	<button onclick="location.href='booking'">예약관리</button>
                    	<button onclick="location.href='room'" style="color: blue;">객실관리</button>
                    	<button onclick="location.href='home'" style="color: blue;">로그아웃</button>
                    </ul>	
                    <nav>
                </div>
            </div>
        </header>

            <div class="body_area box_inner">
                <h2 class="tit_page">예약된 객실</h2>
                        <table border="1">
                            <th>방 이름</th>
                            <th>숙박 기간</th>
                            <th>숙박 인원</th>
                            <th>모바일 번호</th>
                            <tr><!-- 첫번째 줄 -->
                                <td><button type="submit" class="Room_name1">광덕산</button></td>
                                <td>YYYY:MM:DD</td>
                                <td>4명</td>
                                <td>010-1111-2222</td>
                            </tr><!-- // 첫번째 줄 -->
                            <tr><!-- 두번째 줄 -->
                                <td><button type="submit" class="Room_name2">설악산</button></td>
                                <td>YYYY:MM:DD</td>
                                <td>5명</td>
                                <td>010-1111-2222</td>
                            </tr><!-- // 두번째 줄 -->
                            <tr><!-- 세번째 줄 -->
                                <td><button type="submit" class="Room_name3">남산</button></td>
                                <td>YYYY:MM:DD</td>
                                <td>6명</td>
                                <td>010-1111-2222</td>
                            </tr><!-- // 세번째 줄 -->
                            <tr><!-- 네번째 줄 -->
                                <td><button type="submit" class="Room_name4">월출산</button></td>
                                <td>YYYY:MM:DD</td>
                                <td>3명</td>
                                <td>010-1111-2222</td>
                            </tr><!-- // 네번째 줄 -->
                            </table>
                        </ul>
                </div>
        
            <div class="bodyte_area box_inner">
                <a>객실 이름</a>&nbsp;<a><input type="text" name="Name" size="20"></a><br>
                <a>숙박 기간</a></a><input type='date' name='userBirthday' value='YYYY:MM:DD'/><a>~</a><input type='date' name='userBirthday' value='YYYY:MM:DD'/><a>박</a><br>
                <a>숙박 인원</a>&nbsp;<a><input type="text" name="Name" size="20">(명)</a><br>
                <a>1박 비용</a>&nbsp;<a><input type="text" name="Name" size="20">(원)</a><br>
                <a>총 숙박비</a>&nbsp;<a><input type="text" name="Name" size="20">(원)</a><br>
                <a>예약자 모바일</a><br><a><input type="text" name="Name" size="20"></a>
                <p class="btn_line">
                    <button type="submit" style="background-color: greenyellow;" href="#" class="btn_baseColor1">등록</button>
                    <button type="reset" style="background-color: rgb(236, 131, 11);" href="#" class="btn_baseColor2">삭제</button>
                    <button type="reset" style="background-color: red;" href="#" class="btn_baseColor3">Clear</button>
            </div>
            <div class="bodytext_area box_inner">
                <!-- 검색폼영역 -->
                <form id="search_form" name="search_form" action="board_list.html" class="minisrch_form">
                    <fieldset>
                        <legend>객실이름 검색</legend>
                        <input type='date' name='userBirthday' value='YYYY:MM:DD'/><a>~</a><input type='date' name='userBirthday' value='YYYY:MM:DD'/>
                        <input name="search_keyword" type="text" class="tbox" title="객실 이름을 입력해주세요" placeholder="객실 이름을 입력해주세요">
                        <button class="btn_srch">찾기</button>
                    </fieldset>
                </form>
                <!-- //검색폼영역 -->
                
                <!-- 호텔 관리 영역 -->
                <table class="bbsListTbl" summary="방이름, 방 크기, 사용 가능인원, 1박 가격을 제공하는 표">
                    <div class="box_inner">
                        <h2 class="tit_page">예약 가능</h2>
                        <table border="1">
                            <th>방 이름</th>
                            <th>방 크기</th>
                            <th>사용 가능 인원</th>
                            <th>1박 가격</th>
                            <tr><!-- 첫번째 줄 -->
                                <td><button type="submit" class="btn_Room1">백두산</button></td>
                                <td>Suite Room</td>
                                <td>4명</td>
                                <td>120,000</td>
                            </tr><!-- // 첫번째 줄 -->
                            <tr><!-- 두번째 줄 -->
                                <td><button type="submit" class="btn_Room2">한라산</button></td>
                                <td>Family Room</td>
                                <td>6명</td>
                                <td>100,000</td>
                            </tr><!-- // 두번째 줄 -->
                            <tr><!-- 세번째 줄 -->
                                <td><button type="submit" class="btn_Room3">태조산</button></td>
                                <td>Double Room</td>
                                <td>4명</td>
                                <td>70,000</td>
                            </tr><!-- // 세번째 줄 -->
                            <tr><!-- 네번째 줄 -->
                                <td><button type="submit" class="btn_Room4">흑성산</button></td>
                                <td>Single Room</td>
                                <td>2명</td>
                                <td>50,000</td>
                            </tr><!-- // 네번째 줄 -->
                            </table>
                        </ul>
                    </div>
                </p>
                <!-- //호텔 관리 영역 -->
                </div>
            </div>
        </div>
    </body>
<script>

</script>
</html>