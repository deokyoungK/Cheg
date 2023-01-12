<!doctype html>
<html lang="en">
<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/css/mypage.css">
</head>
<body>
    <%@ include file="../layout/header.jsp"%>
    <div class="mypage">

        <!-- 유저 기본 정보 -->
        <div class="user_membership">
            <div class="user_detail">
                <strong class="user_detail_name">${principal.user.name}</strong>
                <div class="user_detail_email">${principal.user.email}</div>
            </div>

            <div class="membership_detail">
                <div class="membership_detail_content">
                    <strong class="info"> 일반 회원 </strong>
                    <p class="info-sub"> 회원 등급 </p></a>
                </div>

                <div class="membership_detail_content">
                    <strong class="info"> 0P </strong>
                    <p class="info-sub"> 포인트 </p></a>
                </div>
            </div>

        </div>

        <!-- 구매 내역 -->
        <div class="purchase_list">
            <div class="purchase_list_title">
                <h3 class="title_txt"> 구매 내역 </h3>
            </div>

            <div class="purchase_list_display_item" style="background-color: rgb(255, 255, 255);">
                <div class="purchase_list_product">

                    <p class="list_item_name" style="margin-right: 30px;">201808272020</p>

                    <div class="list_item_img_wrap">
                        <img alt="image" src="" class="list_item_img" style="background-color: rgb(255, 255, 255);">
                    </div>

                    <div class="list_item_title_wrap">
                        <p class="list_item_brand">[EVENT] Nike x Ambush</p>
                        <p class="list_item_name">Dunk High Black</p>
                    </div>
                </div>

                <div class="list_item_status">
                    <div class="width-wrap">
                        <p class="list_item_column" style="color: rgb(34, 34, 34); margin-right: 60px;">개수</p>
                    </div>
                    
                    <div class="width-wrap">
                        <p class="list_item_column" style="color: rgb(34, 34, 34); margin-right: 60px;">가격</p>
                    </div>

                    <div class="width-wrap">
                        <p class="list_item_column" style="color: #BB2649;">배송중</p>
                    </div>

                </div>
            </div>
        </div>

        <!-- 회원 정보 변경 -->
        <form class="profile_info">
            <div class="purchase_list_title">
                <h3 class="title_txt"> 회원 정보 수정 </h3>
            </div>
            <div class="profile_box">

                <div class="profile_group">
                    <h4 class="group_title">로그인 정보</h4>

                    <div class="unit">
                        <h5 class="title">이메일 주소</h5>
                        <div class="input_item">
                            <input type="email" autocomplete="off" class="input_txt text_fill" placeholder="${principal.user.email}">
                        </div>
                    </div>
                
                    <div class="unit">
                        <h5 class="title">비밀번호</h5>
                        <div class="input_item">
                            <input type="password" placeholder="영문, 숫자, 특수문자 조합 8-16자" autocomplete="off" class="input_txt text_fill">
                        </div>
                    </div>
                </div>
                        
                <div class="profile_group" >
                    <h4 class="group_title">개인 정보</h4>
                    <div class="unit">
                        <h5 class="title">이름</h5>
                        <div class="input_item">
                            <input type="text" placeholder="${principal.user.name}" autocomplete="off" class="input_txt">
                        </div>
                    </div>
                        
                    <div class="unit">
                        <h5 class="title">휴대폰 번호</h5>
                        <!-- <p class="desc">010-5***-*006</p> -->
                        <input type="text" placeholder="${principal.user.phone}" autocomplete="off" class="input_txt">
                    </div>
                </div>
            </div>

            <div class="modify-box">
                <input class="modify-btn" type="submit" onclick="" value="변경">
            </div>
        </form>
    </div>
</body>
</html>
