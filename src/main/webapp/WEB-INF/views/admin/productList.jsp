<!doctype html>
<html lang="en">
<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/css/productlist.css">

</head>
<body>
    <%@ include file="../layout/header.jsp"%>

    <div class="amdin-wrap">
    
        <ul>
            <li class="admin-menu">관리자 메뉴</li>
            <li><a class="adminlist-page" href="/admin">회원리스트</a></li>
            <li><a class="productlist-page" href="/admin/productList">상품리스트</a></li>
            <li><a class="orderlist-page" href="/admin/orderList">주문리스트</a></li>

        </ul>

    <section class="client">
        <table class="client_list">
            <h1 class="client-txt">상품리스트</h1>
            <thead>
            <tr>
                <td>카테고리</td>
                <td>브랜드</td>
                <td>상품명</td>
                <td>가격</td>
            </tr>
            </thead>
            <c:forEach var="p" items="${productList}">
            <tbody>
                <tr class="">
                    <td>${p.category.name}</td>
                    <td>${p.brand}</td>
                    <td>${p.name}</td>
                    <td>${p.price}원</td>
                </tr>
            </tbody>
            </c:forEach>
        </table>
        <a href="/admin/addProduct"><button class="cart__btn right" > 상품등록 </button></a>
    </section>

   

    </div>
</body>
</html>