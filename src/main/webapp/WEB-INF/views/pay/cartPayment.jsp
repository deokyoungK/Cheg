<!doctype html>
<html lang="en">
<head>
	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<meta charset="UTF-8">
	<meta name="viewport"
		  content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<link rel="stylesheet" href="/css/payment.css">
	<%--아임포트 라이브러리--%>
	<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
	<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
</head>
<body>
<%@ include file="../layout/header.jsp"%>
<input type="hidden" id="flag" value="1">
<input type="hidden" id="cartName" value="${responseMap['name']}">

<form action="" onsubmit="iamport(event)">

	<div class="division">
		<div class="wrap-left">
			<h1 class="order-txt">주문/결제</h1>
			<div>
				<div class="orderer-info-box" >
					<p class="orderer-info-box-title">주문자 정보</p>

					<div class="order-info-box-inner">

						<div class="orderer-info-division">
							<div class="orderer-info-txt">이름</div>
							<input type="text" id="uname" value="${principal.user.name}">
						</div>

						<div class="orderer-info-division">
							<div class="orderer-info-txt">연락처</div>
							<input type="text" id="uphone" value="${principal.user.phone}">
						</div>

						<div class="orderer-info-division">
							<div class="orderer-info-txt">이메일</div>
							<input type="email" id="email" value="${principal.user.email}" >
						</div>
					</div>
				</div>

				<div class="delivery-info-box">
					<p class="delivery-info-box-title">배송 정보</p>

					<div class="delivery-info-box-inner">

						<div class="delivery-info-division">
							<div class="delivery-info-txt">이름</div>
							<input type="text" id="name">
						</div>

						<div class="delivery-info-division">
							<div class="delivery-info-txt">연락처</div>
							<input type="text" id="phone" placeholder="여백없이 입력해주세요.">
						</div>


						<div class="delivery-info-division">
							<div class="delivery-info-txt">주소</div>

							<div class="post-box">
								<div class="post-btn-box">
									<input type="text" class="" name="postcode" id="postcode" placeholder="우편번호" readonly>
									<button type="button" class="post-find-btn" onclick="execDaumPostcode()">우편번호 찾기</button>
								</div>
								<input
										type="text"
										name="address"
										id="address"
										placeholder="도로명 주소"
										readonly
								/>
								<input
										type="text"
										name="detailAddress"
										id="detailAddress"
										placeholder="상세 주소"

								/>
							</div>

						</div>
						<!-- <div class="delivery-ask-txt">배송 요청 사항</div>
						<button type="button" class="delivery-ask-btn" onclick="">직접 수령할게요.</button> -->

					</div>
				</div>


				<div class="delivery-product-box" >
					<p class="delivery-product-title">배송 상품</p>

					<c:forEach var="c" items="${responseMap['list']}">
					<div class="delivery-product-box-inner">
						<div class="delivery-product-top">
							<a class="delivery-product-img" href=""><img class="delivery-product-img" src="/upload/${c.productUrl}" alt=""></a>
							<div class="delivery-product-info">
								<div class="delivery-product-brand">${c.productBrand}</div>
								<div class="delivery-product-name">${c.productName}</div>
								<div class="delivery-product-name">${c.productCount}개</div>
								<div class="delivery-product-price">${c.cartTotalPrice}원</div>
							</div>
						</div>
					</div>
					</c:forEach>

					<div class="delivery-product-down">
							<span class="order-count">
								총
								<span class="text-green">${responseMap['amount']}</span>
								개
							</span>

							<span class="order-total-price">
									${responseMap['cartListTotalPrice']}원
							</span>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="wrap-right">
			<div class="payment-box">
				<div class="payment-box-bottom">
					<div class="payment-box-title">결제금액</div>

					<div class="payment-division">
						<div class="payment-left">주문 상품 수</div>
						<div class="payment-right">${responseMap['amount']}개</div>
					</div>
					<div class="payment-division">
						<div class="payment-left">총 상품 가격</div>
						<div class="payment-right" id = "total-price">${responseMap['cartListTotalPrice']}원</div>
					</div>
					<div class="payment-division">
						<div class="payment-left">배송비</div>
						<div class="payment-right">전상품 무료 배송</div>
					</div>
					<div class="payment-division">
						<div class="payment-left">남은 포인트</div>
						<div class="payment-right" id="now-point">${principal.user.point.amount}원</div>
					</div>
					<div class="payment-division">
						<div class="payment-left">포인트 사용</div>
						<div class="payment-right">
							<input type="number" class="point-input" id="point" min="0" max="${principal.user.point.amount}" value="0">원
							<button id="use-all-points">전체 사용</button>
						</div>
					</div>
					<div class="payment-division">
						<span class="max-point-msg" style="color: #ff0000; font-size: 10px;">※최대 포인트 ${responseMap['cartListTotalPrice']/2}원까지 사용 가능한 상품입니다.</span>
					</div>
				</div>

				<div class="payment-division">
					<div class="total-payment-left">최종 결제 금액</div>
					<div class="total-payment-right" id="final-price">${responseMap['cartListTotalPrice']}원</div>
				</div>
				<input class="payment-btn" type="submit" value="결제하기">

			</div>
		</div>
	</div>
</form>
</body>

<!--autoload=false 파라미터를 이용하여 자동으로 로딩되는 것을 막습니다.-->
<script type="text/javascript" src="/js/payment.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js?autoload=false"></script>

</html>