<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="whole-page">
	<div class="left-page"></div>
	<div class="page">
		<div class="up-page"></div>
		<div class="setting-container">
			<div class="setting__content">

				<div class="content-item__01">
					<div class="item__img">
						<img src="#" onerror="this.src='https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png'" />
					</div>
					<div class="item__username">
						<div class="user_name">${principal.user.name}님</div>
					</div>
				</div>

				<%--업데이트 form--%>
				<form id="profileUpdate" onsubmit="update(${principal.user.id},event)">
					<input type="hidden"  id="id"  value="${principal.user.id}">
					<div class="content-item__02">
						<div class="item__title">이름</div>
						<div class="item__input">
							<input type="text" name="name" placeholder="이름"
								   value="${principal.user.name}" required="required"/>
						</div>
					</div>
					<div class="content-item__03">
						<div class="item__title">아이디</div>
						<div class="item__input">
							<input type="text" name="username" placeholder="아이디"
								   value="${principal.user.username}" readonly="readonly" />
						</div>
					</div>
					<div class="content-item__04">
						<div class="item__title">비밀번호</div>
						<div class="item__input">
							<input type="password" name="password" placeholder="비밀번호"  required="required"/>
						</div>
					</div>

					<div class="content-item__06">
						<div class="item__title">자기소개</div>
						<div class="item__input">
							<textarea name="bio" id="" rows="3">${principal.user.bio}</textarea>
						</div>
					</div>

					<div class="content-item__08">
						<div class="item__title">이메일</div>
						<div class="item__input">
							<input type="text" name="email" placeholder="이메일"
								   value="${principal.user.email}" readonly="readonly" />
						</div>
					</div>
					<div class="content-item__09">
						<div class="item__title">연락처</div>
						<div class="item__input">
							<input type="text" name="phone" placeholder="전화번호"
								   value="${principal.user.phone}" />
						</div>
					</div>


					<!--제출버튼-->
					<div class="content-item__11">
						<div class="item__title"></div>
						<div class="item__input">
							<button>등록</button>
						</div>
					</div>
					<!--제출버튼end-->

				</form>
			</div>
		</div>
	</div>
	<div class="right-page"></div>
</div>
<div class="page-bottom"></div>


<script src="/js/update.js"></script>
