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

            <c:forEach var="order" items="${orderList}">
            <div class="purchase_list_display_item" style="background-color: rgb(255, 255, 255);">
                <div class="purchase_list_product">
                    <p class="list_item_name" style="margin-right: 30px;">${order.orderNumber}</p>
                    <div class="list_item_img_wrap">
                        <img alt="image" src="/upload/${order.orderItemList[0].product.url}" class="list_item_img" style="background-color: rgb(255, 255, 255);">
                    </div>

                    <c:choose>
                        <c:when test="${order.orderProductCount == 1}">
                            <div class="list_item_title_wrap">
                                <p class="list_item_brand">${order.orderItemList[0].product.brand}</p>
                                <p class="list_item_name">${order.orderItemList[0].product.name}</p>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="list_item_title_wrap">
                                <p class="list_item_brand">${order.orderItemList[0].product.brand}</p>
                                <p class="list_item_name">${order.orderItemList[0].product.name}외 ${order.orderProductCount-1}개 상품</p>
                            </div>
                        </c:otherwise>
                    </c:choose>

                </div>

                <div class="list_item_status">
                    <c:choose>
                        <c:when test="${order.orderStatus == '주문완료'}">
                            <div class="width-wrap">
                                <p class="list_item_column" style="color: rgb(34, 34, 34); margin-right: 60px;">주문완료</p>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="width-wrap">
                                <p class="list_item_column" style="color: rgb(34, 34, 34); margin-right: 60px;">주문취소</p>
                            </div>
                        </c:otherwise>
                    </c:choose>

                    <div class="width-wrap">
                        <p class="list_item_column" style="color: rgb(34, 34, 34); margin-right: 60px;">${order.orderPrice}원</p>
                    </div>

                    <div class="width-wrap">
                        <p class="list_item_column" style="color: #BB2649;">${order.delivery.deliveryStatus}</p>
                    </div>

                </div>
            </div>
            </c:forEach>
        </div>

        <!-- 회원 정보 변경 -->
        <form id="profile_info" class="profile_info" onsubmit="update(${principal.user.id},event)">
            <div class="purchase_list_title">
                <h3 class="title_txt"> 회원 정보 수정 </h3>
            </div>
            <div class="profile_box">

                <div class="profile_group">
                    <h4 class="group_title">로그인 정보</h4>

                    <div class="unit">
                        <h5 class="title">이메일 주소</h5>
                        <div class="input_item">
                            <input type="email" name="email" class="input_txt text_fill" value="${principal.user.email}">
                        </div>
                    </div>
                
                    <div class="unit">
                        <h5 class="title">아이디</h5>
                        <div class="input_item">
                            <input type="text" class="input_txt text_fill" value="${principal.user.username}" readonly />
                        </div>
                    </div>
                </div>
                        
                <div class="profile_group" >
                    <h4 class="group_title">개인 정보</h4>
                    <div class="unit">
                        <h5 class="title">이름</h5>
                        <div class="input_item">
                            <input type="text" name="name" class="input_txt" value="${principal.user.name}" required />
                        </div>
                    </div>
                        
                    <div class="unit">
                        <h5 class="title">휴대폰 번호</h5>
                        <input type="text" name="phone" class="input_txt" value="${principal.user.phone}" placeholder="공백없이 입력해주세요.">
                    </div>

                    <div class="unit">
                        <h5 class="title">주소</h5>
                        <input type="text" name="address" class="input_txt" value="${principal.user.address}" placeholder="주소를 입력해주세요.">
                    </div>
                </div>
            </div>

            <div class="modify-box">
                <input class="modify-btn" type="submit" onclick="" value="변경">
            </div>
        </form>
    </div>
</body>
<script type="text/javascript" src="/js/mypage.js"></script>
</html>
