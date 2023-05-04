//장바구니 추가
function onCart() {
    var principalId = $("#principalId").val();
    var productId = $("#productId").val();
    var productCount = $("#amount_input").val();
    var stockQuantity = $("#stock_content").text();
    if (principalId == "") {
        alert("로그인 후 이용가능합니다.");
        location.href = "/auth/login"
        return;
    }
    if (productCount < 1) {
        alert("1개 이상 선택해야 합니다.");
        return;
    }
    if(parseInt(stockQuantity) < parseInt(productCount)){
        alert("남은 수량을 초과했습니다.");
        return;
    }

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
            location.href = "/cart/" + principalId;
        },
        error: function (xhr) {
            var errorResponse = JSON.parse(xhr.responseText);
            var errorCode = errorResponse.errorCode;
            var errorMessage = errorResponse.errorMessage;
            alert(errorMessage);
        }
    });
}

//상세에서 결제페이지로 이동
function detailToPayment(productId){
    var principalId = $("#principalId").val();
    var productCount = $("#amount_input").val();
    var stockQuantity = $("#stock_content").text();

    if(principalId == ""){
        alert("로그인 후 이용 가능합니다.");
        location.href="/auth/login"
        return;
    }

    if(productCount<1){
        alert("1개 이상 선택해야 합니다.");
        return;
    }

    if(parseInt(stockQuantity) < parseInt(productCount)){
        alert("남은 수량을 초과했습니다.");
        return;
    }

    $.ajax({
        type: "post",
        url: `/api/payment/${productId}/${productCount}`,
        contentType: "application/json; charset=utf-8",   //보낼 데이터의 형식
        dataType: "json" //응답받을 데이터의 형식
    }).done(res => {
        location.href="/detailPayment/"+res.data[0]+"/"+res.data[1];
    }).fail(error => {
        alert("실패");
    });



}
