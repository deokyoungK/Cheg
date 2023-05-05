<!DOCTYPE html>
<html lang="en">
<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/login.css">
</head>

<body>
    <%@ include file="../layout/header.jsp"%>
    <c:if test="${not empty error}">
        <script>
            alert("${exception}");
        </script>
    </c:if>
    <div class="wrap">
        <div class="login__form__container">
            <div class="login__form">
                <div class="login-title">
                    <div class="login-text">지금 가입하면 누구나
                        <br>
                        <strong>5000원 포인트 지급!</strong>
                    </div>
                    <div class="login-register">신규 회원이신가요?<span><a href="/auth/signup" class="registe-account-txt"> 새 계정 만들기</a></span></div>
                </div>

                <!--로그인폼(아이디,비밀번호) -->
                <form class="login__input"  action="/auth/login" method="POST">
                    <input type="text" name="username" placeholder="아이디" required="required" />
                    <input type="password" name="password" placeholder="비밀번호" required="required"/>
                    <button>로그인</button>
                </form>


                <!--소셜로그인 -->
                <div class="login__social">
                    <div class="login__facebook">
                        <button class="facebook-btn" onclick = "javascript:location.href='/oauth2/authorization/facebook'">
                            <span>Facebook으로 시작하기</span>
                        </button>
                    </div>

                    <div class="login__google">
                        <button class="google-btn" onclick = "javascript:location.href='/oauth2/authorization/google'">
                            <span>Google로 시작하기</span>
                        </button>
                    </div>

                    <div class="login__naver">
                        <button class="naver-btn" onclick = "javascript:location.href='/oauth2/authorization/naver'">
                            <span>naver로 시작하기</span>
                        </button>
                    </div>

                    <div class="login__kakao">
                        <button class="kakao-btn" onclick = "javascript:location.href='/oauth2/authorization/kakao'">
                            <span>kakao로 시작하기</span>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>
</html>