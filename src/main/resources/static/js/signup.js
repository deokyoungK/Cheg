function checkUsername() {
    var username = $("#username").val();
    $.ajax({
        type: "POST",
        url: "/api/auth/checkUsername",
        data: {"username":username},
        dataType: "json",
        success: function(data){
            alert("사용 가능한 아이디입니다.");
            $("#usernameCheck").show();
        },
        error: function (xhr) {
            var errorResponse = JSON.parse(xhr.responseText);
            var errorMessage = errorResponse.errorMessage;
            alert(errorMessage);
            $("#usernameCheck").hide();
        }
    });
}

function checkPassword() {
    var password = $("#password").val();
    $.ajax({
        type: "POST",
        url: "/api/auth/checkPassword",
        data: {"password":password},
        dataType: "json",
        success: function(data){
            alert("사용 가능한 비밀번호입니다.");
            $("#passwordCheck").show();
        },
        error: function (xhr) {
            var errorResponse = JSON.parse(xhr.responseText);
            var errorMessage = errorResponse.errorMessage;
            alert(errorMessage);
        }
    });
}

function signup(e){

    var username = $("#username").val();
    var password = $("#password").val();
    var name = $("#name").val();
    var phone = $("#phone").val();
    var email = $("#email").val();

    if(!$("#usernameCheck").is(":visible")){
        alert("아이디 중복확인을 해주세요.");
        return;
    }
    if(!$("#passwordCheck").is(":visible")){
        alert("비밀번호 확인을 해주세요.");
        return;
    }

    $.ajax({
        type: "POST",
        url: "/api/auth/signup",
        data: {
            "username": username,
            "password": password,
            "name": name,
            "phone": phone,
            "email": email
        },
        dataType: "json",
        success: function(data){
            alert("회원가입이 완료됐습니다.");
            location.href = "/auth/login"
        },
        error: function (xhr) {
            var errorResponse = JSON.parse(xhr.responseText);
            var errorMessage = errorResponse.errorMessage;
            alert(errorMessage);
        }
    });
}











