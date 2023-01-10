function onCart(){
    var productId = $("#productId").val();
    var principalId = $("#principalId").val();
    var amount = document.getElementById('amount_input').value;

    if(principalId == ""){
        alert("로그인 후 이용가능합니다.");
        location.href="http://localhost:8080/auth/login"
    }
    else{
        if(amount<1){
            alert("1개 이상 선택해야 합니다.");
        }else{
            var data={
                p: principalId,
                amount: amount
            };

            $.ajax({
                type: "post",
                url: `/api/cart/${productId}/${amount}`,
                data: JSON.stringify(data), //(자바스크립트 데이터를 JSON으로 변환하여 보낸다.)
                contentType: "application/json; charset=utf-8",   //보낼 데이터의 형식
                dataType: "json" //응답받을 데이터의 형식
            }).done(res => {
                alert("상품을 장바구니에 담았습니다.");
                location.href="http://localhost:8080/cart/"+principalId;
            }).fail(error => {
                alert("실패");
            });
        }
    }
}

//상세에서 결제페이지로 이동
function detailToPayment(productId){


    var amount = document.getElementById('amount_input').value;
    if(amount<1){
        alert("1개 이상 선택해야 합니다.");
        return;
    }
    $.ajax({
        type: "post",
        url: `/api/payment/${productId}/${amount}`,
        contentType: "application/json; charset=utf-8",   //보낼 데이터의 형식
        dataType: "json" //응답받을 데이터의 형식
    }).done(res => {
        location.href="http://localhost:8080/detailPayment/"+res.data[0]+"/"+res.data[1];
    }).fail(error => {
        alert("실패");
    });
}
