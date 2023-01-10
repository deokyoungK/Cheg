<!doctype html>
<html lang="en">
<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/css/payment.css">

</head>
<body>
    <%@ include file="../layout/header.jsp"%>
	<form>
		<h1 class="order-txt">주문/결제</h1>
		<div class="division">
			
			<div class="wrap-left">
				
				<!-- <input type="reset" value="다시 작성"> -->

				<div class="orderer-info-box" >
					<p class="orderer-info-box-title">주문자 정보</p>

					<div class="order-info-box-inner">

						<div class="orderer-info-division">
							<div class="orderer-info-txt">이름</div>
							<input type="text" id="uname" placeholder="" required>
						</div>

						<div class="orderer-info-division">
							<div class="orderer-info-txt">연락처</div>
							<input placeholder="여백없이 입력"  required>
						</div>

						<div class="orderer-info-division">
							<div class="orderer-info-txt">이메일</div>
							<input placeholder="">
						</div>
					</div>
				</div>

				<div class="delivery-info-box">
					<p class="delivery-info-box-title">배송 정보</p>

					<div class="delivery-info-box-inner">

						<div class="delivery-info-division">
							<div class="delivery-info-txt">이름</div>
							<input type="text" id="uname" placeholder="" required>
						</div>

						<div class="delivery-info-division">
							<div class="delivery-info-txt">연락처</div>
							<input placeholder="여백없이 입력"  required>
						</div>

						
						<div class="delivery-info-division">
							<div class="delivery-info-txt">주소</div>
							
							<div class="post-box">
								<div class="post-btn-box">
									<input type="text" class="" name="postcode" id="postcode" placeholder="우편번호" readonly  required/>
									<button type="button" class="post-find-btn" onclick="execDaumPostcode()">우편번호 찾기</button>
								</div>
								<input
								type="text"
								class=""
								name="address"
								id="address"
								placeholder="도로명 주소"
								readonly
								/>

								<input
								type="text"
								class=""
								name="detailAddress"
								placeholder="상세 주소"
								required
								/>
							</div>

						</div>

						<!-- <div class="orderer-info-division"> -->
							<div class="delivery-ask-txt">배송 요청 사항</div>
							<button type="button" class="delivery-ask-btn" onclick="">직접 수령할게요.</button>
							<!-- <textarea cols="40" rows="3" id="comment"></textarea> -->
						<!-- </div> -->
					</div>
				</div>
				

				<div class="delivery-product-box" >
					<p class="delivery-product-title">배송 상품</p>

					<div class="delivery-product-box-inner">
						<div class="delivery-product-top">

							<a class="delivery-product-img" href=""><img class="delivery-product-img" src="images/1.jpg" alt=""></a>
							<div class="delivery-product-info">
								<div class="delivery-product-brand">브랜드명</div>
								<div class="delivery-product-name">상품명</div>
								<div class="delivery-product-price">가격</div>
							</div>
						</div>

						<div class="delivery-product-down">
							<span class="order-count">
								총 
								<span class="text-green">1</span>
								개
							</span>

							<span class="order-total-price">
								1,600,000
								원
							</span>
						</div>
					</div>
				</div>
			</div>
			
			<div class="wrap-right">
				<div class="payment-box">
					<div>
						<div class="payment-box-bottom">
							<div class="payment-box-title">결제금액</div>
							
							<div class="payment-division">
								<div class="payment-left">주문 상품 수</div>
								<div class="payment-right">3개</div>
							</div>

							<div class="payment-division">
								<div class="payment-left">총 상품 가격</div>
								<div class="payment-right">420000원</div>
							</div>

							<div class="payment-division">
								<div class="payment-left">배송비</div>
								<div class="payment-right">전상품 무료 배송</div>
							</div>
						</div>

						<div class="payment-division">
							<div class="total-payment-left">최종 결제 금액</div>
							<div class="total-payment-right">420000원</div>
						</div>
					</div>

					<!-- <p>결제수단</p> -->

					<!-- <div>
						<label>
						<input type="radio" name="contact" value="email" />
						<span>무통장 입금</span>
						</label>
					
						<label>
						<input type="radio" name="contact" value="phone" />
						<span>카카오페이</span>
						</label>
					
					</div> -->

					<input class="payment-btn" type="submit" value="결제하기">
				</div>
			</div>
		</div>
	</form>
</body>

<!--autoload=false 파라미터를 이용하여 자동으로 로딩되는 것을 막습니다.-->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js?autoload=false"></script>

<script>
/** 우편번호 찾기 */
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
</script>
</html>