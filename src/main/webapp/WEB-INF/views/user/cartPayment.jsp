<!doctype html>
<html lang="en">
<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/css/payment.css">
    <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
    <!-- 아래 제이쿼리는 1.0이상이면 원하는 버전을 사용하셔도 무방합니다. -->
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
</head>
<body>
    <%@ include file="../layout/header.jsp"%>
    <c:forEach var="c" items="${cartList}">
        <div class="cart__list__detail">
            <img style="width:50px;"class="img" src="${c.product.url}">
            ${c.product.name}
            ${c.product_count}개

            <span id='total_price_${c.id}' class="price">${c.total_price}원</span>

        </div>
    </c:forEach>
    <button onclick="iamport();">결제하기</button>

</body>
<script src="/js/payment.js"></script>
</html>