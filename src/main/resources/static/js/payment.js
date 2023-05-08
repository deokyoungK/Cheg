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
    var price = $("#final-price").text();
    var pointAmount = parseInt($('#point').val()) || 0;

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
            // alert("배송정보 유효성검사 성공");

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
                            // alert("결제검증완료");
                            var req_data = {
                                address: address,
                                productId: productId,
                                amount: amount,
                                pointAmount: pointAmount,
                                flag: flag //상세,장바구니 구분
                            };
                            //비즈니스 로직
                            orderProcess(req_data, res.imp_uid);

                        } else {
                            // alert("결제 검증 실패");
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
function orderProcess(req_data, imp_uid){
    $.ajax({
        type: "POST",
        url: `/api/order`,
        data: JSON.stringify(req_data),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (rsp) {
            alert("결제되었습니다.");
            location.href = "/mypage/"+rsp.data;

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

// 포인트 입력 필드의 값이 변경될 때마다 최종 결제 금액을 계산하여 표시
$('#point').on('input', function() {
    var totalPrice = parseInt($('#total-price').text().replace('원', '')); // 총 상품 가격을 초기화
    var point = parseInt($(this).val()) || 0; // 값이 없을 경우 0으로 처리
    var maxPoint = totalPrice/2; // 최대 포인트 사용 가능 범위 설정
    var myPoint = parseInt($('#now-point').text().replace('원', '')); //현재 보유한 포인트

    if (point > myPoint) { // 입력한 값이 현재 포인트보다 큰 경우
        alert('보유한 포인트를 초과하였습니다.');
        point = myPoint;
        $(this).val(point);
    } else if (point > maxPoint) { // 최대 포인트 사용 가능 범위를 초과한 경우
        alert('포인트 사용 가능 최대 범위입니다');
        point = maxPoint;
        $(this).val(point);
    }

    // 최종 결제 금액 계산
    var finalPrice = totalPrice - point;

    if (finalPrice < 0) {
        finalPrice = 0;
        $(this).val(totalPrice); // 입력값을 총 상품 가격으로 초기화
    }

    $('#final-price').text(finalPrice + '원');
});


//전체 사용 버튼 클릭시
document.getElementById('use-all-points').addEventListener('click', function(event) {
    event.preventDefault();
    // 버튼 클릭 시 실행될 코드
    var maxPoint = parseInt($('#point').attr('max'));
    $('#point').val(maxPoint);
    $('#point').trigger('input'); // 인풋 이벤트 강제 실행
});



/* 우편번호 찾기 */
function execDaumPostcode() {
    daum.postcode.load(function(){
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
                $("#postcode").val(data.zonecode);
                $("#address").val(data.roadAddress);
            }
        }).open();
    });
}