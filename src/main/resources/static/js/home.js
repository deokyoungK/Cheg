
var page = 0; // 현재 페이지 번호

getProductList(0); //첫 화면 상품 로딩

$(window).scroll(() => {
  // 스크롤이 끝(밑에서 50 전)까지 내려갔을 때 새로운 상품 데이터 가져오기
  if ($(window).scrollTop() >= $(document).height() - $(window).height() - 50) {
    page++;
    getProductList(page);
  }
});

//페이지별로 상품 가져오기
function getProductList(page){
  $.ajax({
    type: "GET",
    url: `/api/products/${page}`,
    contentType: "application/x-www-form-urlencoded; charset=utf8",
    dataType: "json",
    success: function (data) {
      var productList = data.data;
      var productHtml = "";

      $.each(productList, function (index, product) {
        productHtml += '<div class="product-card">';
        productHtml +=
            '<a class="product-img" href="/detail/' +
            product.id +
            '"><img class="product-img" src="/upload/' +
            product.url +
            '" alt=""></a>';
        productHtml +=
            '<div class="product-brand">' + product.brand + "</div>";
        productHtml += '<div class="product-name">' + product.name + "</div>";
        productHtml +=
            '<div class="product-price">' + product.price + "원</div>";
        productHtml += "</div>";
      });

      //응답으로 받은 상품 데이터를 이용해서 새로운 상품 리스트를 추가
      $("#product-list").append(productHtml);
    },
    error: function (xhr) {
      var errorResponse = JSON.parse(xhr.responseText);
      var errorMessage = errorResponse.errorMessage;
      alert(errorMessage);
    },
  });
}

//카테고리 별로 상품 가져오기
function showCategoryProduct(categoryId) {
  $.ajax({
    type: "GET",
    url: `/api/products/category/${categoryId}`,
    contentType: "application/x-www-form-urlencoded; charset=utf8",
    dataType: "json",
    success: function (data) {
      var productList = data.data;
      var productHtml = "";

      if (productList.length == 0) {
        //조회된 상품이 없는 경우
        productHtml += '<div class="product-card">';
        productHtml += "<div>상품이 없습니다.</div>";
        productHtml += "</div>";
      } else {
        $.each(productList, function (index, product) {
          productHtml += '<div class="product-card">';
          productHtml +=
            '<a class="product-img" href="/detail/' +
            product.id +
            '"><img class="product-img" src="/upload/' +
            product.url +
            '" alt=""></a>';
          productHtml +=
            '<div class="product-brand">' + product.brand + "</div>";
          productHtml += '<div class="product-name">' + product.name + "</div>";
          productHtml +=
            '<div class="product-price">' + product.price + "원</div>";
          productHtml += "</div>";
        });
      }

      $("#search-result")
        .text("상품 " + productList.length + "개")
        .css({
          "font-size": "13px",
          color: "rgba(34, 34, 34, 0.8)",
        });

      // 상품 리스트를 보여주는 div를 선택해서 모든 자식 엘리먼트를 제거
      $("#product-list").empty();
      // 응답으로 받은 상품 데이터를 이용해서 새로운 상품 리스트를 추가
      $("#product-list").append(productHtml);
    },
    error: function (xhr) {
      var errorResponse = JSON.parse(xhr.responseText);
      var errorMessage = errorResponse.errorMessage;
      alert(errorMessage);
    },
  });
}

