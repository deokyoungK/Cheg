<!doctype html>
<html lang="en">
<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/addProduct.css">

</head>
<body>
    <%@ include file="../layout/header.jsp"%>
	<div class="jumbotron">
		<div class="container" style="height: 24px;">
			<h1>
				상품 등록
			</h1>
		</div>
	</div>
	
	<div class="container">
		
		<form name="newProduct" action="/admin/addProduct" class="form-horizontal" method="post"
		enctype="multipart/form-data">

            <div class="dropdown">
			    <div class="form-group row">
				    <label class="col-sm-2">카테고리</label>
				    <div class="com-sm-3">
                        <select id="select_value" name="category" onchange="addressKindChange(this)">
							<c:forEach var="c" items="${categoryListDto}">
								<option value="${c.name}">${c.name}</option>
							</c:forEach>
                        </select>
                    </div>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">브랜드</label>
				<div class="com-sm-3">
					<input type="" name="brand" class="form-control" required>
				</div>
			</div>
            
			<div class="form-group row">
				<label class="col-sm-2">상품 이름</label>
				<div class="com-sm-3">
					<input type="text" name="name" class="form-control" required>
				</div>
			</div>

            <div class="form-group row">
				<label class="col-sm-2">상세 내용</label>
				<div class="com-sm-5">
					<textarea name="description" cols="50" rows="2" class="form-control"></textarea>
				</div>
			</div>

			
			<div class="form-group row">
				<label class="col-sm-2">가격</label>
				<div class="com-sm-3">
					<input type="text" id="" name="price" class="form-control" placeholder="숫자만 입력하세요." required>
				</div>
			</div>

			<div class="form-group row">
				<label class="col-sm-2">상품 이미지</label>
				<div>
					<input type="file" name="file" class="form-control">
				</div>
			</div>
			
            <a href=""><button class="cart__btn right" onclick = "">등록하기</button></a>
			
		</form>
	</div>
</body>
</html>