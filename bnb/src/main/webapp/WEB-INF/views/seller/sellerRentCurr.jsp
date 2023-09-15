<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

    <link rel="stylesheet"
        href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    <link rel="stylesheet"
        href="https://fonts.googleapis.com/css2?family=Material+Symbols+Sharp:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;800&display=swap" rel="stylesheet">
    <link rel="stylesheet"
        href="https://fonts.googleapis.com/css2?family=Material+Symbols+Sharp:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />

    <link rel="stylesheet" href="/css/seller.css">
    <link rel="stylesheet" href="/css/main.css">
    <link rel="stylesheet" href="/css/slide.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
    <script src="/js/slide.js"></script>
</head>

<body>
    <%@include file="/WEB-INF/tiles/header.jsp" %>
    <section>
        <div class="wrap">
            <div class="side-menu">
                <div class="bsname">
                    <h1><a href="/seller/main">서점 이름</a></h1>
                </div>
                <div class="menu-title">
                    MENU
                </div>
                <div class="menu">
                    <div class="menu-group">
                        <div class="bigmenu"><span>서점 정보</span></div>
                        <div class="submenu"><a href="/seller/infoupdate">서점 정보 관리</a></div>
                        <div class="submenu"><a href="/seller/csmember">고객 정보 보기</a></div>
                    </div>
                    <div class="menu-group">
                        <div class="bigmenu"><span>도서 관리</span></div>
                        <div class="submenu"><a href="/seller/book/list">등록된 도서 리스트</a></div>
                        <div class="submenu"><a href="/seller/book/add">도서 추가 등록</a></div>
                    </div>
                    <div class="menu-group">
                        <div class="bigmenu"><span>대여 관리</span></div>
                        <div class="submenu"><a href="/seller/rent/reserve">예약 신청 내역</a></div>
                        <div class="submenu"><a href="/seller/rent/curr">대여 내역</a></div>
                        <div class="submenu"><a href="/seller/rent/return">반납 내역</a></div>
                    </div>
                    <div class="menu-group">
                        <div class="bigmenu"><span>판매 관리</span></div>
                        <div class="submenu"><a href="/seller/sell/history">판매 내역</a></div>
                        <div class="submenu"><a href="/seller/sell/cancel">주문 취소 관리</a></div>
                        <div class="submenu"><a href="/seller/return/manage">반품/교환 관리</a></div>
                    </div>
                    <div class="menu-group">
                        <div class="bigmenu"><span>정산</span></div>
                        <div class="submenu"><a href="/seller/calculate">정산 내역</a></div>
                    </div>
                </div>
            </div>
            <div class="contain-3">
                <div class="box-3">
                    <h1>대여 현황</h1>
                    <button id="status_save" onclick="updateSelectedStatus()">저장</button><br><br>
                    <table class="seller-list">
                        <thead>
                            <tr>
                                <th>주문번호</th>
                                <th>아이디</th>
                                <th>제목</th>
                                <th>대여일자</th>
                                <th>반납예정일</th>
                                <th>수령방법</th>
                                <th colspan="2">주문상태</th>
                                <th colspan="2">대여상태</th>
                                <th>연체일</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${RentCurrentList}" var="rentcurrent" varStatus="status">
                                <tr id="row_${status.index}">
                                    <td>${rentcurrent.o_id}</td>
                                    <td>${rentcurrent.o_c_id}</td>
                                    <td>${rentcurrent.b_title}</td>
                                    <td>${rentcurrent.o_dateStr}</td>
                                    <td>${rentcurrent.returnexpect_daysStr}</td>
                                    <td>${rentcurrent.o_delivery_sort}</td>
                                    <td id="del_status_text_${status.index}">${rentcurrent.delivery_status}</td>
                                    <td>
                                        <select class="del_status" name="del_status_${status.index}">
                                            <c:forEach items="${DeliveryStatusList}" var="DelStatus">
                                                <option value="${DelStatus.delivery_status}" <c:if
                                                    test="${rentcurrent.delivery_status == DelStatus.delivery_status}">
                                                    selected</c:if>
                                                    >
                                                    ${DelStatus.delivery_status}
                                                </option>
                                            </c:forEach>
                                        </select>

                                    </td>
                                    <td id="rent_status_text_${status.index}">${rentcurrent.rental_status}</td>
                                    <td>
                                        <button id="Rent_Return" onclick="Rent_Return()">반납 완료</button>
                                    </td>
                                    <td>${rentcurrent.overdue_days}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </section>
    <%@include file="/WEB-INF/tiles/footer.jsp" %>
</body>
<script>
    function updateSelectedStatus() {
    var data = [];
    var rows = document.querySelectorAll('.seller-list tbody tr');

    rows.forEach(function (row, index) {
        var o_id = row.querySelector('td:first-child').textContent;
        var b_title = row.querySelector('td:nth-child(3)').textContent;
        var select = row.querySelector('.del_status');
        var delivery_status = select.options[select.selectedIndex].value;

        data.push({
            o_id: o_id,
            b_title: b_title,
            delivery_status: delivery_status,
        });
    });

    console.log(data);

    $.ajax({
        url: '/seller/rent/curr/save',
        method: 'POST',
        data: JSON.stringify(data),
        contentType: 'application/json',
        success: function (response) {
            console.log('서버 응답:', response);
            alert('상태가 저장되었습니다!');

            var rows = document.querySelectorAll('.seller-list tbody tr');
            rows.forEach(function (row, index) {
                var del_status_text = $('#del_status_text_' + index);
                del_status_text.text(response[index].delivery_status);
            });
        },
        error: function (error) {
            console.error('오류 발생:', error);
            alert('상태 저장 실패');
        }
    });
}

</script>

</html>