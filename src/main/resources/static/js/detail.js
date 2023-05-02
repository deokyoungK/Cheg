function onCart() {

    var principalId = $("#principalId").val();
    var productId = $("#productId").val();
    var productCount = document.getElementById('amount_input').value;

    if (principalId == "") {
        alert("로그인 후 이용가능합니다.");
        location.href = "http://localhost:8080/auth/login"
    } else {
        if (productCount < 1) {
            alert("1개 이상 선택해야 합니다.");
        } else {
            var data = {
                productId: productId,
                productCount: productCount
            };

            $.ajax({
                type: "post",
                url: "/api/cart/add",
                data: JSON.stringify(data),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (data) {
                    alert("상품을 장바구니에 담았습니다.");
                    location.href = "http://localhost:8080/cart/" + principalId;
                },
                error: function (xhr) {
                    var errorResponse = JSON.parse(xhr.responseText);
                    var errorCode = errorResponse.errorCode;
                    var errorMessage = errorResponse.errorMessage;
                    alert(errorMessage);
                }
            });
        }
    }
}

//상세에서 결제페이지로 이동
function detailToPayment(productId){
    var principalId = $("#principalId").val();
    var amount = document.getElementById('amount_input').value;

    if(amount<1){
        alert("1개 이상 선택해야 합니다.");
        return;
    }
    if(principalId == 0){
        alert("로그인 후 이용 가능합니다.");
        location.href="http://localhost:8080/auth/login"
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
