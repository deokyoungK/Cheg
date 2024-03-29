<!doctype html>
<html lang="en">
<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/css/orderlist.css">

</head>
<body>
    <%@ include file="../layout/header.jsp"%>

    <div class="amdin-wrap">
    
        <ul>
            <li class="admin-menu admin">관리자 메뉴</li>
            <li><a class="adminlist-page admin" href="/admin/users">회원리스트</a></li>
            <li><a class="productlist-page admin" href="/admin/products">상품리스트</a></li>
            <li><a class="orderlist-page admin" href="/admin/orders">주문리스트</a></li>

        </ul>

    <section class="client">
        <table class="client_list">
            <div class="client-wrap">
                <h1 class="client-txt">주문리스트</h1>
                <div class="admin-search">
                    <form id="form" action="/admin/search/order" method="GET" class="admin-box">
                        <input name="keyword" class="admin-search_input" type="text" placeholder="주문번호 입력">
                        <button type="submit" class="admin-search_btn">검색</button>
                    </form>
                </div>
            </div>
            <thead>
            <tr>
                <td>주문 번호</td>
                <td>회원 아이디</td>
                <td>주문 상태</td>
                <td>주문 날짜</td>
            </tr>
            </thead>

            <c:forEach var="o" items="${orderListDto}">
            <tbody>
                <tr class="">
                    <td>${o.orderNumber}</td>
                    <td>${o.username}</td>
                    <td>${o.orderStatus}</td>
                    <td>${o.createDate}</td>
                </tr>
            </tbody>
            </c:forEach>
             

        </table>
    </section>


    </div>
</body>
</html>