<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal"/>
</sec:authorize>


<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>MySNS</title>

	<!-- 제이쿼리 -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

	<%--sockjs--%>
	<script type="text/javascript"
			src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.5/sockjs.min.js"></script>

	<!-- Style -->
	<link rel="stylesheet" href="/css/friend.css">
	<link rel="stylesheet" href="/css/createchatroom.css">
	<link rel="stylesheet" href="/css/chatroom.css">
	<link rel="stylesheet" href="/css/chat.css">
	<link rel="stylesheet" href="/css/header.css">
	<link rel="stylesheet" href="/css/style.css">
	<link rel="stylesheet" href="/css/story.css">
	<link rel="stylesheet" href="/css/profile.css">
	<link rel="stylesheet" href="/css/upload.css">
	<link rel="stylesheet" href="/css/update.css">
	<link rel="shortcut icon" href="/images/insta.svg">
	
	<!-- Fontawesome -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
	<!-- Fonts -->
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Gowun+Dodum&display=swap" rel="stylesheet">

</head>

<body>
	
	<!-- principalId 담아두는곳 -->
	<input type="hidden" id="principalId" value="${principal.user.id}">
	<input type="hidden" id="principalName" value="${principal.user.name}">



	<header class="header">
		<div class="con">
			<a href="/" class="project-name">
				</i> MySNS
			</a>
			<nav class="navi">
				<ul class="navi-list">
					<li class="navi-item"><a href="/search/">
						검색
						</a></li>
					<li class="navi-item"><a href="/chatroom/">
						오픈채팅
					</a></li>
					<li class="navi-item"><a href="/user/${principal.user.id }">
						마이페이지
					</a></li>
				</ul>
			</nav>
			<nav class="sub">
				<ul class="sub-list">
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
							<li class="sub-welcome">
								${principal.user.name}님, 환영합니다!
							</li>
							<li class="sub-logout">
								<button class="logout-btn" onclick="location.href='/logout'">로그아웃</button>
							</li>
						</c:otherwise>
					</c:choose>

				</ul>

			</nav>
		</div>
	</header>
