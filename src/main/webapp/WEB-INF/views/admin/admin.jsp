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
            <li><a href="/">홈</a></li>
            <li><a href="#">회원리스트</a></li>
            <li><a href="#">상품리스트</a></li>
            <li><a href="#">주문리스트</a></li>

        </ul>

    <section class="client">
        <table class="client_list">
            <h1 class="client-txt">회원리스트</h1>
            <thead>
            <tr>
                <td>아이디(탈퇴여부)</td>
                <td>이름</td>
                <td>이메일</td>
                <td>우편번호</td>
                <td>주소</td>
                <td>전화</td>
                <td>가입일</td>
            </tr>
            </thead>

            <tbody>
                <tr class="">
                    <td>google_107625354897149547598(가입)</td>
                    <td>윤예지</td>
                    <td>jeji3391@naver.com</td>
                    <td>18030</td>
                    <td>화성시 봉담읍 동화길 82 104-1901</td>
                    <td>01055021006</td>
                    <td>2023.10.06</td>
                </tr>
            </tbody>
            <tbody>
                <tr class="">
                    <td>google_107625354897149547598</td>
                    <td>윤예지</td>
                    <td>jeji3391@naver.com</td>
                    <td>18030</td>
                    <td>화성시 봉담읍 동화길 82 104-1901</td>
                    <td>01055021006</td>
                    <td>2023.10.06</td>
                </tr>
            </tbody>
            <tbody>
                <tr class="">
                    <td>google_107625354897149547598</td>
                    <td>윤예지</td>
                    <td>jeji3391@naver.com</td>
                    <td>18030</td>
                    <td>화성시 봉담읍 동화길 82 104-1901</td>
                    <td>01055021006</td>
                    <td>2023.10.06</td>
                </tr>
            </tbody>

        </table>
    </section>


    </div>
</body>
</html>