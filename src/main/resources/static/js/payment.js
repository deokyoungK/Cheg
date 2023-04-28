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
                    url: "/verifyIamport/" + res.imp_uid,
                    success: function (data) {
                        if (res.paid_amount == data.response.amount) {
                            alert("결제검증완료");

                            //비회원은 id를 0으로
                            if (principalId == "") {
                                principalId = 0;
                            }

                            var req_data = {
                                user_id: principalId, //비회원.회원 구분
                                address: address,
                                product_id: productId,
                                amount: amount,
                                flag: flag //상세,장바구니 구분
                            };

                            //비즈니스로직
                            $.ajax({
                                type: "POST",
                                url: `/api/order`,
                                data: JSON.stringify(req_data),
                                contentType: "application/json; charset=utf-8",
                                dataType: "json",
                                success: function (rsp) {
                                    alert("결제되었습니다.");
                                    //비회원은 홈으로
                                    if (principalId == 0) {
                                        location.href = `/`;
                                    } else {
                                        location.href = `/mypage/${principalId}`;
                                    }
                                },
                                error: function (xhr) {
                                    alert("서비스로직까지는 실패");
                                }
                            });

                        } else {
                            alert("결제 검증 실패");
                        }
                    },
                    error: function () {
                    }
                });
            });
        },
        error: function (xhr) {
            var errorResponse = JSON.parse(xhr.responseText);
            var errorMessage = errorResponse.errorMessage;
            alert(errorMessage);
            return;
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