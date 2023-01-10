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
	<input type="hidden" id="flag" value="0">
	<button onclick="iamport();">결제하기</button>


	<form>
		<div class="division">
			<div>
				<h1 class="order-txt">주문/결제</h1>
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
							<input type="text" id="name" placeholder="" required>
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
				

				<div class="delivery-product-info-box" >
					<p class="delivery-product-info-box-title">배송 상품</p>

					<div class="delivery-product-info-box-inner">

						<a class="delivery-product-img" href=""><img class="delivery-product-img" src="" alt=""></a>

						<div class="delivery-product-info-division">
							<div class="product-brand">브랜드명</div>
							<div class="product-name">상품명</div>
							<div class="product-price">가격</div>
							<div class="">총</div>
							<div class="">개</div>
							<div class="">개수*가격</div>
						</div>
					</div>
				</div>
			</div>
			
			<div>
				<div class="">
					<div>
						<p>결제금액</p>
						<p>주문 상품 수</p>
						<p>개</p>
						<p>총 상품 가격</p>
						<p>배송비</p>
						<p>전상품 무료 배송</p>
						<p>최종 결제 금액</p>
						<p>원</p>
					</div>

					<p>결제수단</p>

					<div>
						<label>
						<input type="radio" name="contact" value="email" />
						<span>무통장 입금</span>
						</label>
					
						<label>
						<input type="radio" name="contact" value="phone" />
						<span>카카오페이</span>
						</label>
					
					</div>

					<input type="submit" value="주문하기">
				</div>
			</div>
		</div>
	</form>
</body>

<!--autoload=false 파라미터를 이용하여 자동으로 로딩되는 것을 막습니다.-->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js?autoload=false"></script>

<script src="/js/payment.js"></script>
</html>