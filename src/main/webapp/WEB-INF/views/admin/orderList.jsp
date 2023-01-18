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
            <li class="admin-menu">관리자 메뉴</li>
            <li><a href="/">홈</a></li>
            <li><a class="adminlist-page" href="/admin">회원리스트</a></li>
            <li><a class="productlist-page" href="/admin/productList">상품리스트</a></li>
            <li><a class="orderlist-page" href="/admin/orderList">주문리스트</a></li>

        </ul>
    <section class="client">
        <table class="client_list">
            <h1 class="client-txt">주문리스트</h1>
            <thead>
            <tr>
                <td>주문번호</td>
                <td>주문날짜</td>
                <td>배송상태</td>
                <td>이미지</td>
                <td>브랜드</td>
                <td>상품명</td>
                <td>고객아이디</td>
            </tr>
            </thead>

            <tbody>
                <tr class="">
                    <td>107625354897149547598</td>
                    <td>2023.10.06</td>
                    <td>배송완료</td>
                    <td><img style="width:50px;"class="img" src=""></td>
                    <td>Barbour</td>
                    <td>[커플룩/모자] 바버 강아지 옷 스웨터 강아지 바버 니트</td>
                    <td>ezi Yoon</td>
                </tr>
            </tbody>

            <tbody>
                <tr class="">
                    <td>107625354897149547598</td>
                    <td>2023.10.06</td>
                    <td>배송완료</td>
                    <td><img style="width:50px;"class="img" src=""></td>
                    <td>Barbour</td>
                    <td>[커플룩/모자] 바버 강아지 옷 스웨터 강아지 바버 니트</td>
                    <td>ezi Yoon</td>
                </tr>
            </tbody>

            <tbody>
                <tr class="">
                    <td>107625354897149547598</td>
                    <td>2023.10.06</td>
                    <td>배송완료</td>
                    <td><img style="width:50px;"class="img" src=""></td>
                    <td>Barbour</td>
                    <td>[커플룩/모자] 바버 강아지 옷 스웨터 강아지 바버 니트</td>
                    <td>ezi Yoon</td>
                </tr>
            </tbody>

             

        </table>
    </section>


    </div>
</body>
</html>