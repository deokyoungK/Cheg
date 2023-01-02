<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<!--프로필 섹션-->
<div class="whole-page">
	<div class="left-page"></div>
	<div class="middle-page">
		<div class="up-page">
			<c:choose>
				<c:when test="${dto.pageOwnerState}">
					＊나의 프로필 정보
				</c:when>
				<c:otherwise>
					＊프로필 정보
				</c:otherwise>
			</c:choose>
		</div>
		<div class="profile">
			<!--유저정보 컨테이너-->
			<div class="profileContainer">
				<!--유저이미지-->
				<div class="profile-left">
					<div class="profile-img-wrap"
						onclick="popup('.modal-image')">

						<form id="userProfileImageForm">
							<input type="file" name="profileImageFile" style="display: none;"
								id="userProfileImageInput" />
						</form>

						<img class="profile-image" src="/upload/${dto.user.profileImageUrl}"
							id="userProfileImage" onerror="this.src='https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png'" />
					</div>
				</div>
				<!--유저이미지end-->

				<!--유저정보 및 사진등록 구독하기-->
				<div class="profile-right">
					<div class="name-group">
						<div class="user_name">${dto.user.name }</div>

						<c:choose>
							<c:when test="${dto.pageOwnerState}">
								<button class="cta" onclick="location.href='/image/upload'">사진등록</button>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${dto.subscribeState}">
										<button class="cta blue" onclick="toggleSubscribe(${dto.user.id},this)">구독취소</button>
									</c:when>
									<c:otherwise>
										<button class="cta" onclick="toggleSubscribe(${dto.user.id},this)">구독하기</button>
									</c:otherwise>
								</c:choose>

							</c:otherwise>
						</c:choose>

						<c:choose>
							<c:when test="${dto.pageOwnerState}">
								<button class="modi" onclick="popup('.modal-info')">
									<i class="fas fa-cog"></i>
								</button>
							</c:when>
							<c:otherwise>

							</c:otherwise>
						</c:choose>

					</div>

					<div class="subscribe">
						<ul>
							<li><a href=""> 게시물<span>${dto.imageCount}</span>
							</a></li>
							<li><a href="javascript:subscribeInfoModalOpen(${dto.user.id});">팔로우<span>${dto.subscribeCount}</span>
							</a></li>
						</ul>
					</div>
					<div class="state">
						<div class="bio">${dto.user.bio}</div>
					</div>
				</div>
				<!--유저정보 및 사진등록 구독하기 End-->
			</div>
		</div>
		<br>
		<hr>
		<div class="tab-content-title">
			＊업로드한 사진
		</div>
		<br>
		<div id="tab-content">
			<div class="profileContainer">
				<!--그냥 감싸는 div (지우면이미지커짐)-->
				<div id="tab-1-content" class="tab-content-item show">
					<div class="tab-1-content-inner">

						<c:forEach var="image" items="${dto.user.images }">
							<div class="img-box">
								<img src="/upload/${image.postImageUrl}" />
								<c:choose>
									<c:when test="${dto.pageOwnerState}">
										<div id="img-delete-div"><button type="button" class="img-delete-btn" onclick="location.href='/delete/${image.id}'">삭제</button></div>
									</c:when>
									<c:otherwise>
									</c:otherwise>
								</c:choose>
							</div>
						</c:forEach>

					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="right-page">
	</div>
</div>
<div class="page-bottom"></div>





<!--로그아웃, 회원정보변경 모달-->
<div class="modal-info" onclick="modalInfo()">
	<div class="modal">
		<button onclick="location.href='/user/1/update'">회원정보 변경</button>
		<button onclick="location.href='/logout'">로그아웃</button>
		<button onclick="closePopup('.modal-info')">취소</button>
	</div>
</div>
<!--로그아웃, 회원정보변경 모달 end-->

<!--프로필사진 바꾸기 모달-->
<div class="modal-image" onclick="modalImage()">
	<div class="modal">
		<p>프로필 사진 바꾸기</p>
		<button onclick="profileImageUpload(${dto.user.id},${principal.user.id })">사진 업로드</button>
		<button onclick="closePopup('.modal-image')">취소</button>
	</div>
</div>
<!--프로필사진 바꾸기 모달 end-->

<!-- 구독정보 모달-->
<div class="modal-subscribe">
	<div class="subscribe">
		<div class="subscribe-header">
			<span>구독정보</span>
			<button onclick="modalClose()">
				<i class="fas fa-times"></i>
			</button>
		</div>
		<div class="subscribe-list" id="subscribeModalList">

		</div>
	</div>
</div>
<!-- 구독정보 모달 end-->

<script src="/js/profile.js"></script>
