<!doctype html>
<html lang="en">
<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/css/admin.css">

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
            <h1 class="client-txt">회원리스트</h1>
            <thead>
            <tr>
                <td>아이디(탈퇴여부)</td>
                <td>이름</td>
                <td>이메일</td>
                <td>주소</td>
                <td>전화</td>
                <td>가입일</td>
            </tr>
            </thead>
            <c:forEach var="u" items="${userList}">
            <tbody>
                <tr class="">
                    <td>${u.username}</td>
                    <td>${u.name}</td>
                    <td>${u.email}</td>
                    <td>${u.address}</td>
                    <td>${u.phone}</td>
                    <td>${u.createDate}</td>
                </tr>
            </tbody>
            </c:forEach>
        </table>


    </section>


    </div>
</body>
</html>