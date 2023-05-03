<!doctype html>
<html lang="en">
<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/css/detail.css">
</head>
<body>
    <%@ include file="../layout/header.jsp"%>
    <input type="hidden" id="productId" value="${productDto.id}">

    <div class="product_detail">
       
        <div class="product_img">
            <img src="/upload/${productDto.url}" style="width:500px;height:480px;">
        </div>

        <div class="product_info">
            <div class="brand">
                ${productDto.brand}
            </div>
            <div class="name">
                ${productDto.name}
            </div>
            <div class="description">
                ${productDto.description}
            </div>

            <li>
                <div class="price">
                    <span class="price_content">${productDto.price}원</span>
                </div>
            </li>
            <hr>
            <li>
                <div class="delivery">
                    <div class="delivery_text">배송비</div>
                    <span class="delivery_content">전상품 무료배송
                        <span class="help-text">(대형가구와 같은 대형 상품 제외)</span>
                    </span>
                </div>
                <div class="delivery">
                    <span class="delivery_text">관부과세</span>
                    <span class="delivery_content">관부과세 없음</span>
                </div>
                <div class="delivery">
                    <span class="delivery_text">배송 예정일</span>
                    <span class="delivery_content">1~2일 이내</span>
                </div>
                <div class="delivery">
                    <span class="delivery_text">반품/교환</span>
                    <span class="delivery_content">수령 후 반품/교환 가능</span>
                </div>

                <div class="delivery">
                    <span class="delivery_text">남은 수량</span>
                    <span class="delivery_content">${productDto.stockQuantity}개</span>
                </div>
            </li>


            <li>
                <div class="amount">
                    <div class="amount_text">구매 수량</div>
                    <span class="amount_content">
                        <input class="amount_input" id="amount_input" type="number" placeholder="0"max="10"min="1">
                    </span>
                </div>
            </li>

            <div class="product_btn">
                <button id="cart_btn" class="cart_btn" onclick="onCart();">장바구니</button>
                <button class="buy_btn" onclick = "detailToPayment(${productDto.id});">즉시 구매하기</button>
            </div>
        </div>
    </div>

</body>
<script src="/js/detail.js"></script>
</html>
