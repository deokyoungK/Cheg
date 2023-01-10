function enterkey() {
    if (window.event.keyCode == 13) {
        alert("Enter Key 입력 감지 \n함수 실행.");
         // 엔터키가 눌렸을 때 실행하는 반응
         $("#form").submit();
    }
}