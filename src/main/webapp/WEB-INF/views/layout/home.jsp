<!doctype html>
<html lang="en">
<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
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
            <a href="https://unsplash.com/s/photos/pets" class="swiper-slide"><img src="images/1.jpg"  alt="NULL"></a>
            <a href="https://unsplash.com/s/photos/pets" class="swiper-slide"><img src="images/1.jpg"  alt="NULL"></a>
            <a href="https://unsplash.com/s/photos/pets" class="swiper-slide"><img src="images/1.jpg"  alt="NULL"></a>
            <a href="https://unsplash.com/s/photos/pets" class="swiper-slide"><img src="images/1.jpg"  alt="NULL"></a>
            <a href="https://unsplash.com/s/photos/pets" class="swiper-slide"><img src="images/1.jpg"  alt="NULL"></a>
            <a href="https://unsplash.com/s/photos/pets" class="swiper-slide"><img src="images/1.jpg"  alt="NULL"></a>
            <a href="https://unsplash.com/s/photos/pets" class="swiper-slide"><img src="images/1.jpg"  alt="NULL"></a>
            <a href="https://unsplash.com/s/photos/pets" class="swiper-slide"><img src="images/1.jpg"  alt="NULL"></a>
            <a href="https://unsplash.com/s/photos/pets" class="swiper-slide"><img src="images/1.jpg"  alt="NULL"></a>
        </div>
        <div class="swiper-button-next"></div>
        <div class="swiper-button-prev"></div>
        <div class="swiper-pagination"></div>
    </div>


    <div class="product-list">
        <c:forEach var="p" items="${productList}">
            <img src="${p.url}" alt="">
            <div>${p.brand}</div>
            <div>${p.name}</div>
            <div>${p.price}원</div>

        </c:forEach>
    </div>




</body>
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
