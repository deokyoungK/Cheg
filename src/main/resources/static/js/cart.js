//물건 수량 감소
function count_down(cartId){
    if($('#count_' + cartId).text() == "0개") {
        alert("최소 갯수입니다.");
    }else if($('#count_' + cartId).text() == "1개"){
        delete_cart(cartId);
    }else{

        $.ajax({
            type: "post",
            url: `/api/cart/${cartId}/down`,
            contentType: "application/json; charset=utf-8",   //보낼 데이터의 형식
            dataType: "json" //응답받을 데이터의 형식
        }).done(res => {
            //해당 cart찾기
            var index = -1;
            for(var i=0; i<res.data.length;i++){
                if(res.data[i].cartId == cartId){
                    index = i;
                }
            }

            //수량 갱신
            $('#count_' + cartId).text(res.data[index].productCount+"개");

            //가격 갱신
            $('#total_price_'+cartId).text(res.data[index].cartTotalPrice+"원");

            //장바구니 총 가격 갱신
            var sum = 0;
            for(var i=0; i<res.data.length; i++){
                sum += parseInt($('#total_price_'+res.data[i].cartId).text());
            }
            $('#summary').text(sum+"원");


        }).fail(error => {
            alert("수량 감소 실패");
        });
    }
}

//물건 수량 증가
function count_up(cartId){
    $.ajax({
        type: "post",
        url: `/api/cart/${cartId}/up`,
        contentType: "application/json; charset=utf-8",   //보낼 데이터의 형식
        dataType: "json" //응답받을 데이터의 형식
    }).done(res => {
        //해당 cart찾기
        var index = -1;
        for(var i=0; i<res.data.length;i++){
            if(res.data[i].cartId == cartId){
                index = i;
            }
        }

        //수량 갱신
        $('#count_' + cartId).text(res.data[index].productCount+"개");

        //가격 갱신
        $('#total_price_'+cartId).text(res.data[index].cartTotalPrice+"원");

        //장바구니 총 가격 갱신
        var sum = 0;
        for(var i=0; i<res.data.length; i++){
            sum += parseInt($('#total_price_'+res.data[i].cartId).text());
        }
        $('#summary').text(sum+"원");

    }).fail(error => {
        alert("수량 증가 실패");
    });
}

//물건 삭제
function delete_cart(cartId){
    var flag = confirm("삭제하시겠습니까?");
    if(flag){
        $.ajax({
            type: "post",
            url: `/api/cart/${cartId}/delete`,
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(res => {
            location.reload();
        }).fail(error => {
            alert("삭제 실패");
        });
    }
}

//수량0개인지 체크
function checkNum(e){
    var summary = $('#summary').text();
    if(summary == "0원"){
        alert("상품이 없습니다.");
        e.preventDefault();
    }
}