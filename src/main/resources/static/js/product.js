//상품 등록
function uploadProduct(e){
    e.preventDefault();
    var form = $('#product-form')[0];
    var formData = new FormData(form);

    $.ajax({
        url: "/api/upload-product",
        method: "POST",
        data: formData,
        processData: false,
        contentType: false,
        success: function(rsp) {
            alert("상품 등록이 완료되었습니다.");
            location.href = "/admin/products";
        },
        error: function(xhr) {
            var errorResponse = JSON.parse(xhr.responseText);
            var errorMessage = errorResponse.errorMessage;
            alert(errorMessage);
        }
    });
}