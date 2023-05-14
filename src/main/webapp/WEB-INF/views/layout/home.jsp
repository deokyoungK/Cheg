<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html lang="en">
<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>홈</title>
    <link rel="stylesheet" href="/css/home.css">
    <!-- Link Swiper's CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper/swiper-bundle.min.css">
    <!-- Swiper JS -->
    <script src="https://cdn.jsdelivr.net/npm/swiper/swiper-bundle.min.js"></script>

</head>
<body>
    <%@ include file="../layout/header.jsp"%>
    <div class="swiper mySwiper">
        <div class="swiper-wrapper">
            <a href="" class="swiper-slide"><img src="${pageContext.request.contextPath}/images/1.jpeg"  alt="NULL"></a>
            <a href="" class="swiper-slide"><img src="${pageContext.request.contextPath}/images/2.jpeg"  alt="NULL"></a>
            <a href="" class="swiper-slide"><img src="${pageContext.request.contextPath}/images/3.jpeg"  alt="NULL"></a>
            <a href="" class="swiper-slide"><img src="${pageContext.request.contextPath}/images/4.jpeg"  alt="NULL"></a>
            <a href="" class="swiper-slide"><img src="${pageContext.request.contextPath}/images/15.jpeg"  alt="NULL"></a>
            <a href="" class="swiper-slide"><img src="${pageContext.request.contextPath}/images/16.jpeg"  alt="NULL"></a>
            <a href="" class="swiper-slide"><img src="${pageContext.request.contextPath}/images/17.jpeg"  alt="NULL"></a>
            <a href="" class="swiper-slide"><img src="${pageContext.request.contextPath}/images/18.jpeg"  alt="NULL"></a>
            <a href="" class="swiper-slide"><img src="${pageContext.request.contextPath}/images/1.jpeg"  alt="NULL"></a>
            <a href="" class="swiper-slide"><img src="${pageContext.request.contextPath}/images/01.webp"  alt="NULL"></a>
            <a href="" class="swiper-slide"><img src="${pageContext.request.contextPath}/images/02.webp"  alt="NULL"></a>
            <a href="" class="swiper-slide"><img src="${pageContext.request.contextPath}/images/03.webp"  alt="NULL"></a>
            <a href="" class="swiper-slide"><img src="${pageContext.request.contextPath}/images/04.webp"  alt="NULL"></a>
            <a href="" class="swiper-slide"><img src="${pageContext.request.contextPath}/images/05.webp"  alt="NULL"></a>
            <a href="" class="swiper-slide"><img src="${pageContext.request.contextPath}/images/06.webp"  alt="NULL"></a>
            <a href="" class="swiper-slide"><img src="${pageContext.request.contextPath}/images/07.webp"  alt="NULL"></a>
            <a href="" class="swiper-slide"><img src="${pageContext.request.contextPath}/images/08.webp"  alt="NULL"></a>
            <a href="" class="swiper-slide"><img src="${pageContext.request.contextPath}/images/14.webp"  alt="NULL"></a>
        </div>
        <div class="swiper-button-next"></div>
        <div class="swiper-button-prev"></div>
        <div class="swiper-pagination"></div>
    </div>

    <div class="product-container">
        <div class="product-category2">
            <button id="category-button" class="category" onclick="showCategoryProduct(0)"><img src="${pageContext.request.contextPath}/images/harness.png">전체</button>

            <c:forEach var="c" items="${categoryListDto}" >
                <button id="category-button" class="category" onclick="showCategoryProduct(${c.id})"><img src="${pageContext.request.contextPath}/images/harness.png">${c.name}</button>
            </c:forEach>
        </div>
        
        <div id = "search-result" class="search-result">
            <div  id = "search-total-count" class="search-total-count">
                <span>상품 ${count}개 </span>
            </div>
        </div>

        <%--상품 목록--%>
        <div id="product-list" class="product-list">

        </div>
        <%--상품 목록 끝--%>

    </div>

</body>
<script type="text/javascript" src="/js/home.js"></script>
<script>
    var swiper = new Swiper(".mySwiper", {
        spaceBetween: 5,
        centeredSlides: true,
        autoplay: {
            delay: 2500,
            disableOnInteraction: false,
        },
        pagination: {
            el: ".swiper-pagination",
            dynamicBullets: true,
        },
        navigation: {
            nextEl: ".swiper-button-next",
            prevEl: ".swiper-button-prev",
        },
        allowTouchMove: false, /* 배너 드래그 조작 금지*/
    });
</script>

</html>


