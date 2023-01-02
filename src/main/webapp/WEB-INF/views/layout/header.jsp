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



<!-- navbar시작 -->
<div class="bar"></div>
<nav class="navbar">
    <ul class="navbar__left">
        <li><a href="#" class="menu">Menu</a></li>
        <li><a href="#" class="search">Search</a></li>
    </ul>

    <div class="navbar__logo">
        <a href="/" class="logo">CHEG</a>
    </div>

    <ul class="navbar__right">
        <ul class="sub__account">
            <c:choose>
                <c:when test="${principal == null}">
                    <li class="sub-login"><a href="/auth/signin">
                        로그인
                    </a></li>
                    <li class="sub-signup"><a href="/auth/signup">
                        회원가입
                    </a></li>
                </c:when>
                <c:otherwise>
                    <li class="sub-welcome" style="padding: 8px 13px;">
                            ${principal.user.name}님, 환영합니다!
                    </li>
                    <li class="sub-logout">
                        <button class="logout-btn" onclick="location.href='/logout'">로그아웃</button>
                    </li>
                </c:otherwise>
            </c:choose>

        </ul>
        <li><a href="/cart" class="cart">Cart</a></li>
    </ul>
</nav>

