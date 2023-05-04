//결제
function iamport(e){
    //form action 막기
    e.preventDefault();

    var flag = $("#flag").val();
    var principalId = $("#principalId").val();
    var name = $("#name").val();
    var phone = $("#phone").val();
    var email = $("#email").val();
    var postcode = $("#postcode").val();
    var address = $("#address").val() + " " + $("#detailAddress").val();

    var productName;
    var productId = $("#productId").val();
    var detailName = $("#productName").val();
    var cartName = $("#cartName").val();
    var amount = $("#amount").val();
    var price = $("#total-price").text();

    //결제시에 상품이름 보여질때 상세, 장바구니 구분
    if(flag==0){
        productName = detailName;
    }else{
        productName = cartName;
    }
    var val_data = {
        name: name,
        phone: phone,
        postcode: postcode
    }

    //유효성 검증(배송정보-이름,연락처,우편번호에 대해)
    $.ajax({
        type: "POST",
        url: `/api/delivery/validation`,
        data: JSON.stringify(val_data),
        contentType: "application/json",
        dataType: "json",
        success: function () {
            alert("배송정보 유효성검사 성공");

            //가맹점 식별코드
            IMP.init("imp20807674");
            IMP.request_pay({
                pg : 'kcp',
                pay_method : 'card',
                merchant_uid : 'merchant_' + new Date().getTime(),
                name : productName,
                amount : price+"d",
                buyer_email : email,
                buyer_name : name,
                buyer_tel : phone,
                buyer_addr : address,
                buyer_postcode : postcode
            }, function(res) {

                // 결제검증
                $.ajax({
                    type: "POST",
                    url: "/api/verifyIamport/" + res.imp_uid,
                    success: function (data) {
                        if (res.paid_amount == data.response.amount) {
                            alert("결제검증완료");
                            var req_data = {
                                address: address,
                                productId: productId,
                                amount: amount,
                                flag: flag //상세,장바구니 구분
                            };
                            //비즈니스 로직
                            orderProcess(req_data, principalId, res.imp_uid);

                        } else {
                            alert("결제 검증 실패");
                            //환불 요청
                            cancelPayment(res.imp_uid);
                        }
                    },
                    error: function () {
                        //환불 요청
                        cancelPayment(res.imp_uid);
                    }
                });
            });
        },
        error: function (x) {
            var errorResponse = JSON.parse(x.responseText);
            var errorMessage = errorResponse.errorMessage;
            alert(errorMessage);
            return;
        }
    });
}

//주문 비즈니스 로직
function orderProcess(req_data, principalId, imp_uid){
    $.ajax({
        type: "POST",
        url: `/api/order`,
        data: JSON.stringify(req_data),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (rsp) {
            alert("결제되었습니다.");
            location.href = `/mypage/${principalId}`;

        },
        error: function (xhr) {
            var errorResponse = JSON.parse(xhr.responseText);
            var errorMessage = errorResponse.errorMessage;
            alert(errorMessage);

            // 결제 취소 요청
            cancelPayment(imp_uid);
        }
    });
}


//결제 취소 요청
function cancelPayment(imp_uid){
    $.ajax({
        type: "POST",
        url: `/api/cancelPayment`,
        data: JSON.stringify({impUid: imp_uid}),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (rsp) {
            alert("결제가 취소되었습니다.");
        },
        error: function (xhr) {
            var errorResponse = JSON.parse(xhr.responseText);
            var errorMessage = errorResponse.errorMessage;
            alert(errorMessage);
        }
    });
}




/* 우편번호 찾기 */
function execDaumPostcode() {
    daum.postcode.load(function(){
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
                $("#postcode").val(data.zonecode);
                $("#address").val(data.roadAddress);
            }
        }).open();
    });
}