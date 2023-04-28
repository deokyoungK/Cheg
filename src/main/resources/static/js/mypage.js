// (1) 회원정보 수정
function update(userId,e) {

    e.preventDefault(); //폼태그 액션 막기
    let data = $("#profile_info").serialize();  //key=value

    $.ajax({
        type: "POST",
        url: `/api/update/${userId}`,
        data: data,
        contentType: "application/x-www-form-urlencoded; charset=utf8",
        dataType: "json",
        success: function(data) {
            alert("회원정보가 변경되었습니다.");
            location.href = `/mypage/${userId}`
            // handle success response
        },
        error: function(xhr) {

            var errorResponse = JSON.parse(xhr.responseText);
            var errorCode = errorResponse.errorCode;
            var errorMessage = errorResponse.errorMessage;
            alert(errorMessage);
            // handle error response
            // errorResponse 객체의 프로퍼티 중 필요한 정보를 이용하여 적절한 에러 메시지를 표시합니다.
        }
    });

}