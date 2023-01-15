// (1) 회원정보 수정
function update(userId,e) {

    e.preventDefault(); //폼태그 액션 막기
    let data = $("#profile_info").serialize();  //key=value

    $.ajax({
        type: "PUT",
        url: `/api/update/${userId}`,
        data: data,
        contentType: "application/x-www-form-urlencoded; charset=utf8",
        dataType: "json"
    }).done(res=>{ //HttpStatus 200번대
        alert("회원정보가 변경되었습니다.");
        location.href=`/mypage/${userId}`;

    }).fail(error=>{ //HttpStatus 200번대가 아닐때
        alert(JSON.stringify(error.responseJSON.data));


    });

}