function iamport(){
    //가맹점 식별코드
    IMP.init('imp20807674');
    IMP.request_pay({
        pg : 'kcp',
        pay_method : 'card',
        merchant_uid : 'merchant_' + new Date().getTime(),
        name : '상품1' , //결제창에서 보여질 이름
        amount : 2, //실제 결제되는 가격
        buyer_email : 'kang48450@gmail.com',
        buyer_name : '강덕영',
        buyer_tel : '010-5022-2951',
        buyer_addr : '서울 서초구 서초동',
        buyer_postcode : '123-456'
    }, function(res) {
        console.log(res);
        // 결제검증
        $.ajax({
            type : "POST",
            url : "/verifyIamport/" + res.imp_uid
        }).done(function(data) {

            console.log(data);

            // 위의 rsp.paid_amount 와 data.response.amount를 비교한후 로직 실행 (import 서버검증)
            if(res.paid_amount == data.response.amount){
                alert("결제 및 결제검증완료");
            } else {
                alert("결제 실패");
            }
        });
    });
}