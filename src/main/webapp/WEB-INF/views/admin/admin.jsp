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
            <li class="admin-menu admin">관리자 메뉴</li>
            <li><a class="adminlist-page admin" href="/admin">회원리스트</a></li>
            <li><a class="productlist-page admin" href="/admin/productList">상품리스트</a></li>
            <li><a class="orderlist-page admin" href="/admin/orderList">주문리스트</a></li>

        </ul>

    <section class="client">
        <table class="client_list">
            <div class="client-wrap">
                <h1 class="client-txt">회원리스트</h1>
                <div class="admin-search">
                    <form id="form" action="/search" method="GET" class="admin-box">
                        <input class="admin-search_input" type="text">
                        <button class="admin-search_btn">검색</button>
                    </form>
                </div>
            </div>
            <thead>
            <tr>
                <td>아이디</td>
                <td>이름</td>
                <td>이메일</td>
                <td>주소</td>
                <td>연락처</td>
                <td>가입일</td>
                <td>회원탈퇴</td>
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
                    <td><i style="font-size:20px; cursor:pointer;" class="fa-solid fa-square-minus" onclick="delete_user(${u.id});"></i></td>
                </tr>
            </tbody>
            </c:forEach>
        </table>


    </section>


    </div>
</body>
<script src="/js/admin.js"></script>
</html>