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

                <!--회원가입폼(아이디,비밀번호,이름,전화번호,이메일-->
                <form class="signup__input"  action="/auth/signup" method="POST">
                    <input type="text" name="username" placeholder="아이디" required="required"/>
                    <input type="password" name="password" placeholder="비밀번호" required="required" />
                    <input type="text" name="name" placeholder="이름" required="required"/>
                    <input type="text" name="phone" placeholder="전화번호" required="required" />
                    <input type="email" name="email" placeholder="이메일" >
                    <button>회원가입</button>
                </form>
            </div>
        </div>
    </div>

</body>

</html>