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

            <thead>
            <tr>
                <td>선택</td>
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
                    <td><input type="checkbox" name = "chk" checked="on"></td>
                    <td><img style="width:50px;"class="img" src="${c.product.url}"></td>
                    <td>${c.product.name}</td>



                    <td><i style="font-size:20px; cursor:pointer; "class="bi bi-arrow-down-circle" id="down_{{forloop.counter}}"></i></td>
                    <td id="count_{{forloop.counter}}">${c.product_count}개</td>
                    <td><i style="font-size:20px; cursor:pointer; "class="bi bi-arrow-up-circle" id="up_{{forloop.counter}}"></i></td>

                    <td>
                        <span id='total_price_{{forloop.counter}}'class="price">${c.total_price}원</span>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="btns">
            <button class="select_all_btn"  id="select_all" onclick="select_all();" >전체 선택</button>
            <button class="select_delete_btn" id="select_delete" onclick="select_delete();">선택 삭제</button>

            <span id="price_total">총 금액</span>
            <span id='summary'>${price}원</span>
        </div>


        <div class="cart__mainbtns">
            <button class="cart__orderbtn left"><a href="{% url 'home' %}">쇼핑 계속하기</a></button>
            <button class="cart__orderbtn right">주문하기</button>
        </div>
    </section>

</body>
</html>