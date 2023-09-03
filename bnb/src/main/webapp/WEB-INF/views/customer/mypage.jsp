<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="ko">

<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet"
        href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    <link rel="stylesheet"
        href="https://fonts.googleapis.com/css2?family=Material+Symbols+Sharp:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Sharp:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />

    <link rel="stylesheet" href="/css/main.css">
    <link rel="stylesheet" href="/css/slide.css">
    <link rel="stylesheet" href="/css/customer/mypage.css">
    <link rel="stylesheet" href="/css/customer/main.css">
  
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <title>Document</title>

</head>

<body>
    
    <jsp:include page="../../tiles/header.jsp"></jsp:include>


    <div class="container-mypage">

        <div class="menu_simple">
            <ul>
                <li><a href="/mypage" id="currpage">마이페이지 홈</a></li>
                <hr>
                <li><a href="/mypage/orderlist">나의 주문내역</a></li>
                <li><a href="/mypage/purchaselist">구매내역</a></li>
                <li><a href="/mypage/refundexchangelist">교환/반품내역</a></li>
                <li><a href="/mypage/rentallist">대여내역</a></li>
                <li><a href="/mypage/rentalreservationlist">대여예약내역</a></li>
                <hr>
                <li><a href="/mypage/favoritestores">즐겨찾기</a></li>
                <li><a href="/mypage/favoritebooks">찜한도서</a></li>
            </ul>
        </div>

        <div class="board-area" id="trans">

            <div class="container-1">

                <div class="container-2">
    
                    <div class="container-3 card-main cards">
                        <p>책과 작은서점을 사랑하는 <span>수히</span>님..</p>
                        <p>오늘도 행복한 하루되시고... ㅁㄴㅇㄻ</p>
                        <p>여기 이미지 들어가야함</p>
                    </div>

                    <div class="container-3 card-button cards">
                        <button onclick="location.href='/mypage/info'">회원정보</button>
                    </div>

                </div>
    
                <div class="container-2">
    
                    <div class="container-3 card-top cards">

                        <h2>진행 중인 교환/반품 : <a href="/mypage/refundexchangelist">2</a>건</h2>
                        <table>
                            <tr>
                                <th>구분</th>
                                <th>신청일자</th>
                                <th>신청품목</th>
                                <th>서점</th>
                                <th>처리상태</th>
                            </tr>
                            <tr>
                                <td>반품</td>
                                <td>2023-08-28</td>
                                <td>누가 내 머리에 똥 쌌어</td>
                                <td>동네서점</td>
                                <td>승인대기</td>
                            </tr>
                            <tr>
                                <td>교환</td>
                                <td>2023-08-25</td>
                                <td>누가 내 머리에 똥 쌌어2</td>
                                <td>동네서점2</td>
                                <td>승인</td>
                            </tr>
                        </table>

                    </div>
                    
                    <div class="container-3 card-top cards">

                        <h2>현재 대여 중 : <a href="/mypage/rentallist">3</a>건</h2>
                        <table>
                            <tr>
                                <th>제목</th>
                                <th>저자</th>
                                <th>서점</th>
                                <th>반납기한</th>
                                <th>대여상태</th>
                            </tr>
                            <tr>
                                <td>누가 내 머리에 똥 쌌어</td>
                                <td>이효진</td>
                                <td>달빛책방</td>
                                <td>2023-09-02</td>
                                <td>대여중</td>
                            </tr>
                            <tr>
                                <td>누가 내 머리에 똥 쌌어</td>
                                <td>이효진</td>
                                <td>달빛책방</td>
                                <td>2023-08-30</td>
                                <td>대여중</td>
                            </tr>
                            <tr>
                                <td>누가 내 머리에 똥 쌌어</td>
                                <td>이효진</td>
                                <td>달빛책방</td>
                                <td>2023-08-27</td>
                                <td>연체</td>
                            </tr>
                        </table>

                    </div>

                </div>
    
            </div>
    
    
            <div class="container-1">
    
                <div class="container-3 card-bottom cards">
                    <h2>최근 구매내역</h2><button onclick="location.href='/mypage/purchaselist'">▶더보기</button>
                    <table>
                        <tr>
                            <th>주문번호</th>
                            <th>주문일자</th>
                            <th>품목</th>
                            <th>배송현황</th>
                        </tr>
                        <tr>
                            <td>20331</td>
                            <td>2023-08-17</td>
                            <td>누가 내 머리에 똥 쌌어</td>
                            <td>배송중</td>
                        </tr>
                        <tr>
                            <td>10201</td>
                            <td>2023-08-13</td>
                            <td>엉덩이탐정2 외 1건</td>
                            <td>수령완료</td>
                        </tr>
                        <tr>
                            <td>20111</td>
                            <td>2023-08-11</td>
                            <td>오펜하이머</td>
                            <td>배송완료</td>
                        </tr>
                    </table>
                </div>
    
                <div class="container-3 card-bottom cards">
                    <h2>최근 대여내역</h2><button onclick="location.href='/mypage/rentallist'">▶더보기</button>
                    <table>
                        <tr>
                            <th>주문번호</th>
                            <th>대여일자</th>
                            <th>품목</th>
                            <th>배송현황</th>
                        </tr>
                        <tr>
                            <td>20331</td>
                            <td>2023-08-17</td>
                            <td>누가 내 머리에 똥 쌌어</td>
                            <td>배송중</td>
                        </tr>
                        <tr>
                            <td>10201</td>
                            <td>2023-08-13</td>
                            <td>엉덩이탐정2 외 1건</td>
                            <td>수령완료</td>
                        </tr>
                        <tr>
                            <td>20111</td>
                            <td>2023-08-11</td>
                            <td>오펜하이머</td>
                            <td>배송완료</td>
                        </tr>
                    </table>
                </div>

            </div>

        </div>

        <div class="button-area">

            <!-- <div class="buttons"></div>
            <div class="buttons"></div> -->

        </div>

    </div>


    <jsp:include page="../../tiles/footer.jsp"></jsp:include>

</body>

</html>

