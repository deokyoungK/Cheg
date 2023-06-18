function handleSocialLogin(){
    // 소셜 로그인 버튼 클릭 시 호출되는 함수
    // 로그인 요청을 수행하고 JWT 토큰을 응답 헤더에서 추출하여 처리하는 코드

    $.ajax({
        url: '/oauth2/authorization/google',
        type: 'GET',
        success: function(data, textStatus, jqXHR) {
            const token = jqXHR.getResponseHeader('Auth');
            // JWT 토큰이 헤더에 존재하는 경우
            if (token) {
                // 토큰을 로컬 스토리지에 저장하거나 필요한 곳에서 사용
                localStorage.setItem('jwtToken', token);
                console.log('JWT 토큰:', token);

                // 로그인이 성공하면 리다이렉트 처리 또는 원하는 동작 수행
                window.location.href = '/home'; // 리다이렉트할 URL
            } else {
                console.log('토큰이 존재하지 않음');
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.error('로그인 요청 실패:', jqXHR.status);
        }
    });
}