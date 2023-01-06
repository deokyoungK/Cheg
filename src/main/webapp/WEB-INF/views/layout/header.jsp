<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="principal"/>
</sec:authorize>
<!-- principalId 담아두는곳 -->
<input type="hidden" id="principalId" value="${principal.user.id}">
<input type="hidden" id="principalName" value="${principal.user.name}">

<!-- 제이쿼리 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- Style -->
<link rel="stylesheet" href="/css/home.css">
<link rel="stylesheet" href="/css/header.css">
<!-- fontawesome-->
<script src="https://kit.fontawesome.com/226cf9ba84.js" crossorigin="anonymous"></script>


<!-- navbar시작 -->
<div class="bar"></div>
<nav class="navbar">

    <div class="navbar__logo">
        <a href="/" class="logo">CHEG</a>
    </div>

    <ul class="navbar__right">
        <div class="search-padding">
            <div class="search-box">
                <input class="search-input" type="text" name="search">
                <img class="search-btn" src="images/search.png">    
            </div>
        </div>

        <c:choose>
            <c:when test="${principal == null}">
                <li class="right-sub fromLeft"><a href="/auth/login" class="nav-txt">
                    Login
                </a></li>
                <li class="right-sub fromLeft"><a href="/auth/signup" class="nav-txt">
                    Join
                </a></li>
            </c:when>

            <c:otherwise>
                <li class="nav-txt sub-welcome" >
                        ${principal.user.name}님, 환영합니다!
                </li>
                <li><a href="/cart/${principal.user.id}" class="nav-txt right-sub fromLeft">Cart</a></li>
                <li class="sub-logout">
                    <button class="nav-txt logout-btn right-sub fromLeft" onclick="location.href='/logout'">logout</button>
                </li>

            </c:otherwise>
        </c:choose>



    </ul>
</nav>

