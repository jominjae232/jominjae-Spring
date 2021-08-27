<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    margin-left: 500px;
    top: 50px;
	}
    </style>
                    </a>
                </p>
                <div class="header_cont">
                    <ul class="util clear">
                    	<button onclick="location.href='booking'" style="color: blue;">예약관리</button>
                    	<button onclick="location.href='room'">객실관리</button>
                    	<button onclick="location.href='home'" style="color: blue;">로그아웃</button>
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
                        <th>방 이름</th>
                        <th>방 크기</th>
                        <th>사용 가능 인원</th>
                        <tr><!-- 첫번째 줄 -->
                            <td><button type="submit" class="Room_name1" name=Room1>백두산</button></td>
                            <td>Suite Room</td>
                            <td>4명</td>
                        </tr><!-- // 첫번째 줄 -->
                        <tr><!-- 두번째 줄 -->
                            <td><button type="submit" class="Room_name2" name=Room2>한라산</button></td>
                            <td>Family Room</td>
                            <td>6명</td>
                        </tr><!-- // 두번째 줄 -->
                        <tr><!-- 세번째 줄 -->
                            <td><button type="submit" class="Room_name3" name=Room3>태조산</button></td>
                            <td>Double Room</td>
                            <td>4명</td>
                        </tr><!-- // 세번째 줄 -->
                        <tr><!-- 네번째 줄 -->
                            <td><button type="submit" class="Room_name3" name=Room4>흑성산</button></td>
                            <td>Single Room</td>
                            <td>2명</td>
                        </tr><!-- // 네번째 줄 -->
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
                
                <!-- 호텔 관리 영역 -->
                </form>
                <table class="bbsListTbl" summary="번호,제목,조회수,작성일 등을 제공하는 표">
                    <caption class="hdd">객실 분류</caption>
                    <thead>
                        <tr>
                            <th> 객실 크기 번호 </th>
                            <th> 객실 크기 </th>
                    </thead>
                    <tbody>
                        <tr><!-- 첫번째 줄 -->
                            <td><button type="submit" class="btn_Num1">1</button></td>
                            <td>Suite Room</td>
                        </tr><!-- // 첫번째 줄 -->
                        <tr><!-- 두번째 줄 -->
                            <td><button type="submit" class="btn_Num2">2</td>
                            <td>Family Room</td>
                        </tr><!-- // 두번째 줄 -->
                        <tr><!-- 세번째 줄 -->
                            <td><button type="submit" class="btn_Num3">3</td>
                            <td>Double Room</td>
                        </tr><!-- // 세번째 줄 -->
                        <tr><!-- 네번째 줄 -->
                            <td><button type="submit" class="btn_Num4">4</td>
                            <td>Single Room</td>
                        </tr><!-- //네번째 줄 -->
                        <tr><!-- 다섯번째 줄 -->
                            <td><button type="submit" class="btn_Num5">5</td>
                            <td>Domitory</td>
                        </tr><!-- // 다섯번째 줄 -->
                    </tbody>
                </table>
                
                
                <a>숙박 가능 인원</a>&nbsp;<a><input type="text" name="Name" size="20">(명)</a><br>
                <a>1박 요금</a>&nbsp;<a><input type="text" name="Name" size="20">(원)</a>
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