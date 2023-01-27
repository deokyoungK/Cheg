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
            <li class="admin-menu admin">관리자 메뉴</li>
            <li><a class="adminlist-page admin" href="/admin">회원리스트</a></li>
            <li><a class="productlist-page admin" href="/admin/productList">상품리스트</a></li>
            <li><a class="orderlist-page admin" href="/admin/orderList">주문리스트</a></li>

        </ul>

    <section class="client">
        <table class="client_list">

            <!-- <div class="product-btn">
                <h1 class="client-txt">상품리스트</h1>
                <div class="admin-search">
                    <form id="form" action="/search" method="GET" class="admin-box">
                        <input class="admin-search_input" type="text">
                        <button class="admin-search_btn">검색</button>
                    </form>
                </div>

                <div class="product-btn">
                    <a href="/admin/addProduct"><button class="cart__btn right" > 상품등록 </button></a>
                    <a href="/admin/addCategory"><button class="cart__btn right" > 카테고리등록 </button></a>
                </div>
            </div> -->

            <div class="client-wrap">
                
                    <h1 class="client-txt">상품리스트</h1>
                    <div class="admin-search">
                        <form id="form" action="/admin/search/product" method="GET" class="admin-box">
                            <input name="keyword" class="admin-search_input" type="text" placeholder="상품명 or 브랜드 입력">
                            <button type="submit" class="admin-search_btn">검색</button>
                        </form>
                    
    
                    
                        <a href="/admin/addProduct"><button class="cart__btn right" > 상품등록 </button></a>
                        <a href="/admin/addCategory"><button class="cart__btn right" > 카테고리등록 </button></a>
                    
                </div>
            </div>



            <thead>
            <tr>
                <td>카테고리</td>
                <td>브랜드</td>
                <td>사진</td>
                <td>상품명</td>
                <td>상품상세</td>
                <td>가격</td>
                <td>삭제</td>
            </tr>
            </thead>
            <c:forEach var="p" items="${productList}">
            <tbody>
                <tr class="">
                    <td>${p.category.name}</td>
                    <td>${p.brand}</td>
                    <td><img src="/upload/${p.url}" alt="" style="width:50px;height:50px;"/></td>
                    <td>${p.name}</td>
                    <td>${p.description}</td>
                    <td>${p.price}원</td>
                    <td><i style="font-size:20px; cursor:pointer;" class="fa-solid fa-square-minus" onclick="delete_product(${p.id});"></i></td>
                </tr>
            </tbody>
            </c:forEach>
        </table>
    </section>

   

    </div>
</body>
<script src="/js/admin.js"></script>
</html>