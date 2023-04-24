<!doctype html>
<html lang="en">
<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/css/cart.css">
</head>
<body>
<%@ include file="../layout/header.jsp"%>

<section class="cart">
    <table class="cart__list">
        <h1 class="cart-txt">장바구니</h1>
        <thead>
        <tr>
            <td>삭제</td>
            <td>사진</td>
            <td>상품명</td>
            <td></td>
            <td>수량</td>
            <td></td>
            <td>상품금액</td>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="c" items="${carts}">
            <tr class="cart__list__detail">
                <td><i style="font-size:20px; cursor:pointer;" class="fa-solid fa-square-minus" onclick="delete_cart(${c.id});"></i></td>
                <td><img style="width:50px;"class="img" src="/upload/${c.product.url}"></td>
                <td>${c.product.name}</td>

                <td><i style="font-size:20px; cursor:pointer;" class="fa-regular fa-circle-down" id="down_${c.id}" onclick="count_down(${c.id});"></i></td>
                <td id="count_${c.id}">${c.productCount}개</td>
                <td><i style="font-size:20px; cursor:pointer;" class= "fa-regular fa-circle-up" id="up_${c.id}" onclick="count_up(${c.id});"></i></td>

                <td>
                    <span id='total_price_${c.id}' class="price">${c.cartTotalPrice}원</span>
                </td>
            </tr>
        </c:forEach>
        </tbody>

    </table>



    <div class="cart__total">
        <div>
            <a href="/"><button class="cart__btn left">쇼핑 계속하기</button></a>
        </div>
        <div class="cart__order">
            <div>
                <span id="price_total">Total</span>
                <span id='summary'>${price}원</span>
            </div>
            <div>
                <a href="../cartPayment/${principal.user.id}/"><button class="cart__btn right" onclick = "checkNum(event);">주문하기</button></a>
            </div>
        </div>
    </div>

</section>

</body>
<script src="/js/cart.js"></script>
</html>