<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/member/findId.css">
    <title>Find ID</title>
</head>
<body>
    <div class="header"></div>
    <div class="titleArea">
        <h2>Find ID</h2>
        <ul>
            <li>아이디 찾기 페이지요.</li>
            <li>법인사업자 회 법인명과 법인번호 또는 이름과 등록번호를 입력해 주세요.</li>
        </ul>
    </div>
     
    <form id="findIdForm" name="findIdForm" action="/exec/front/Member/findId/" method="post" target="_self" enctype="multipart/form-data">
        <input id="returnUrl" name="returnUrl" value="/member/id/find_id_result.html" type="hidden">
        <div class="findId">
            <fieldset>   
                <legend>아이디 찾기</legend>
                <p>
                    <strong>회원유형</strong>
                    <select id="searchType" name="searchType" fw-label="회원유형">
                        <option value="indi" selected="selected">개인회원</option>
                        <option value="indibuis">개인 사업자회원</option>
                    </select>
                </p>
                <p class="check">
                    <input id="check_method1" name="check_method" value="2" type="radio" checked="checked">
                    <label for="check_method1">이메일</label>
                </p>
                <p class="input-group" id="input_name">
                    <strong class="input_label">이름 </strong>
                    <input id="input_field_name" name="name" class="lostInput" placeholder="이름을 입력하세요" value="" type="text">
                </p>
                <p class="input-group" id="input_email">
                    <strong class="input_label">이메일 입력</strong>
                    <input id="input_field_email" name="email" class="lostInput" placeholder="이메일을 입력하세요" value="" type="text">
                </p>
                    <!-- 결과를 표시할 부분을 추가 -->
                    <span id="idResult" style="display:none;">
                        회원님의 아이디: <span id="userId"></span>
                    </span>                 
                </p>
          
                <div class="ec-base-button gColumn" id="button_group">
                    <button type="submit" id="submit_button" class="btnSubmit sizeM" onclick="findIdAction(); return false;">확인</button>
                    <!-- 로그인 버튼을 추가 (기본 상태는 숨김) -->
                    <a href="/member/login" id="login_button" style="display:none;" class="btnSubmit sizeM">로그인페이지로</a>
                </div>        
            </fieldset>
        </div>
    </form>

    <!-- 결과를 보여줄 부분 -->
    <div id="idResult" style="display:none;">
        <strong>회원님의 아이디:</strong> <span id="userId"></span>
    </div>

    <script src="https://code.jquery.com/jquery-3.7.1.min.js"
        integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
        crossorigin="anonymous"></script>
    <script>
        function findIdAction() {
    var inputData = {
        name: $("#input_field_name").val(),
        email: $("#input_field_email").val()
    };

    // 간단한 유효성 검사
    if (!inputData.name || !inputData.email) {
        alert('이름과 이메일을 모두 입력하세요.');
        return;
    }

    $.ajax({
        type: "POST",
        url: "/member/findId",
        data: inputData,
        success: function(response) {
            // 여기서 response는 서버로부터 받은 응답입니다.
            // 일반적으로, 데이터베이스의 값과 일치하는 경우 서버에서는 아이디 값을 반환하며,
            // 일치하지 않는 경우 null 또는 비어있는 값을 반환할 것입니다.
            // 이를 기반으로 아래의 로직을 작성하였습니다.
            if (response && response !== 'not found') {
                $("#userId").text(response);
                $("#idResult").show();
                $("#submit_button").hide();
                $("#login_button").show();
            } else {
                alert('해당하는 아이디를 찾을 수 없습니다.');
            }
        },
        error: function(error) {
            alert('오류가 발생했습니다. 다시 시도해주세요.');
        }
    });
}
    </script>
</body>
</html>