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
            카테고리 등록
        </h1>
    </div>
</div>

<div class="container">
    <form name="newCategory" action="/admin/addCategory" class="form-horizontal" method="post">
        <c:forEach var="c" items="${categoryList}">
            ${c.name}
        </c:forEach>
        <div>
            <input class="category-input cart__btn" type="text" name="categoryName">
            <a href=""><button class="cart__btn right" onclick = "">등록하기</button></a>
        </div>
    </form>
</div>
</body>
</html>