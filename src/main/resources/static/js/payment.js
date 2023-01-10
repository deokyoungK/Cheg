//결제
function iamport(){

    var principalId = $("#principalId").val();
    var address = "서울 서초구 서초동";
    var productId = 7;
    var amount = 20;
    var flag = $("#flag").val();

    //가맹점 식별코드
    IMP.init("imp20807674");
    IMP.request_pay({
        pg : 'kcp',
        pay_method : 'card',
        merchant_uid : 'merchant_' + new Date().getTime(),
        name : '상품1' , //결제창에서 보여질 이름
        amount : 2, //실제 결제되는 가격
        buyer_email : 'kang48450@gmail.com',
        buyer_name : '강덕영',
        buyer_tel : '010-5022-2951',
        buyer_addr : address,
        buyer_postcode : '123-456'
    }, function(res) {
        console.log(res);
        // 결제검증
        $.ajax({
            type : "POST",
            url : "/verifyIamport/" + res.imp_uid
        }).done(function(data) {

            // 위의 rsp.paid_amount 와 data.response.amount를 비교한후 로직 실행 (import 서버검증)
            if(res.paid_amount == data.response.amount){
                alert("결제 및 결제검증완료");

                //비회원은 id를 0으로
                if(principalId == ""){
                    principalId = 0;
                }

                var req_data = {
                    user_id: principalId, //비회원.회원 구분
                    address: address,
                    product_id: productId,
                    amount: amount,
                    flag: flag //상세,장바구니 구분
                };

                //서비스로직
                $.ajax({
                    type : "POST",
                    url : `/api/order`,
                    data: JSON.stringify(req_data),
                    contentType: "application/json; charset=utf-8",
                    dataType: "json"
                }).done(rsp=>{
                    alert("서비스로직까지 성공");
                    console.log(rsp.data);

                }).fail(error=>{
                    alert("서비스로직까지는 실패");
                });


            } else {
                alert("결제 실패");
            }
        });
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