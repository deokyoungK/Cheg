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
    <input type="hidden" id="productId" value="${product.id}">
    <div class="product_detail">
        <div class="product_img">
            <div><img width=300px src="${product.url}"></div>
        </div>

        <div class="product_info">
            <div class="brand">
                ${product.brand}
            </div>
            <div class="name">
                ${product.name}
            </div>

            <li>
                <div class="price">
                    <span class="price_text">판매가</span>
                    <span class="price_content">${product.price}원</span>
                </div>
            </li>
            <hr>
            <li>
                <div class="delivery">
                    <span class="delivery_text">배송정보</span>
                    <span class="delivery_content"></span> 무료/해외배송/관부가세 포함
                </div>
            </li>
            <hr>
            <li>
                <div class="amount">
                    <span class="amount_text">수량</span>
                    <span class="amount_content">
                        <input id="amount_input" type="number" placeholder="수량을 입력하세요."max="10"min="1">
                    </span>
                </div>
            </li>

        </div>
    </div>

    <div class="product_btn">
        <div class="cart">
            <button id="cart_btn" class="cart_btn" onclick="onCart();">장바구니 담기</button>
        </div>
        <div class="buy">
            <a href=""></a><button class="buy_btn" onclick = "detailToPayment(${product.id});">즉시 구매하기</button>
        </div>
    </div>



</body>
<script src="/js/detail.js"></script>
</html>
