function delete_product(productId){
    var flag = confirm("삭제하시겠습니까?");
    if(flag){
        $.ajax({
            type: "post",
            url: `/api/${productId}/delete`,
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(res => {
            location.reload();
        }).fail(error => {
            alert("삭제 실패");
        });
    }
}
function delete_user(userId){
    var flag = confirm("삭제하시겠습니까?");
    if(flag){
        $.ajax({
            type: "post",
            url: `/api/user/${userId}/delete`,
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(res => {
            location.reload();
        }).fail(error => {
            alert("회원 탈퇴 실패");
        });
    }
}