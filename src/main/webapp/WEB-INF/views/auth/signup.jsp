<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/signup.css">
</head>

<body>
    <%@ include file="../layout/header.jsp"%>

    <div class="wrap">
        <div class="signup__form__container">
            <div class="signup__form">
                <div class="signup-title">
                    <div class="signup-text">회원가입</div>
                    <div class="signup-register">계정이 있으신가요?<span><a href="/auth/login" class="go-login">로그인</a></span></div>
                </div>

                <!--회원가입(아이디,비밀번호,이름,전화번호,이메일-->
                <div class="signup__input">
                    <input type="text" name="username" id="username" placeholder="아이디" >
                    <div id="usernameCheck" style="display:none"><i class="fas fa-check" style="color:green"></i>중복 확인 완료</div>
                    <button type="button" id="check-username" onclick = "checkUsername()">중복 확인</button>

                    <input type="password" name="password" id="password" placeholder="비밀번호" >
                    <div id="passwordCheck" style="display:none"><i class="fas fa-check" style="color:green"></i>비밀번호 확인 완료</div>
                    <button type="button" id="check-password" onclick = "checkPassword()">확인</button>

                    <input type="text" name="name" id="name" placeholder="이름" >
                    <input type="text" name="phone" id="phone" placeholder="전화번호" >
                    <input type="email" name="email" id="email" placeholder="이메일">
                    <button type="button" id="signup" onclick="signup()">회원가입</button>
                </div>
            </div>
        </div>
    </div>

</body>
<script type="text/javascript" src="/js/signup.js"></script>
</html>