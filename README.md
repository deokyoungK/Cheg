## 프로젝트 개요
- **프로젝트 이름** : CHEG 
- **프로젝트 소개** : 상품 등록 및 구매가 가능한 쇼핑몰 서비스

- **개발 인원** : 2명
    - FE - yoonezi
    - BE - deokyoungK

- **개발 기간** : 2023.01.20~ 2023.02.20

- **개발 환경 및 언어**
     <div>
         <img src="https://img.shields.io/badge/springboot-6DB33F?style=plastic&logo=springboot&logoColor=white">
         <img src="https://img.shields.io/badge/Apache Maven-C71A36?style=plastic&logo=Apache Maven&logoColor=white">
         <img src="https://img.shields.io/badge/Hibernate-59666C?style=plastic&logo=Hibernate&logoColor=white">
         <img src="https://img.shields.io/badge/MySQL-4479A1?style=plastic&logo=MYSQL&logoColor=white"/>
         <img src="https://img.shields.io/badge/GitHub-181717?style=plastic&logo=Spring Security&logoColor=white">
         <img src="https://img.shields.io/badge/IntelliJ IDEA-000000?style=plastic&logo=IntelliJ IDEA&logoColor=white"/>
     </div>
     <div>
         <img src="https://img.shields.io/badge/java-007396?style=plastic&logo=java&logoColor=white">
         <img src="https://img.shields.io/badge/html5-E34F26?style=plastic&logo=html5&logoColor=white">
         <img src="https://img.shields.io/badge/css-1572B6?style=plastic&logo=css3&logoColor=white">
         <img src="https://img.shields.io/badge/javascript-F7DF1E?style=plastic&logo=javascript&logoColor=black">
         <img src="https://img.shields.io/badge/jquery-0769AD?style=plastic&logo=jquery&logoColor=white">
     </div>
 
- **테스트**
    - JUnit4
## 기능 명세

### 1.로그인

- 아이디, 비밀번호 입력
- 소셜로그인(페이스북, 네이버, 카카오, 구글) 지원
- Spring security, OAuth2
<img src="https://user-images.githubusercontent.com/74487747/223664189-df565dbf-0ecc-486e-bba1-16c03be57919.png" width="700">

### 회원가입

- 아이디, 비밀번호, 이름, 연락처, 이메일 입력
    - 아이디 : Unique, 필수 입력
    - 전화번호 : -없이 10~11자
    - 이메일 :  이메일 형식
- 비밀번호 암호화 후 DB에 저장
<img src="https://user-images.githubusercontent.com/74487747/223665229-d592c8f9-7850-4c4d-b304-c5b9d5d01b84.png" width="700">

### 메인 페이지

- 회원, 비회원 이용 가능
- 등록된 순 상품 4열 배치(grid)
- 카테고리별 상품 필터링 제공
- 검색기능 제공
<img src="https://user-images.githubusercontent.com/74487747/223668952-b557458e-60e7-4a8b-9002-240b8bfa9af7.png" width="700">

### 장바구니 페이지

- 회원만 이용 가능
- 장바구니 수량 증가/감소
- 장바구니 담기/삭제
<img src="https://user-images.githubusercontent.com/74487747/223669198-fa0c0476-6b5b-4424-b652-80ff88631a73.png" width="700">

### 구매 페이지

- 비회원 : 즉시 구매 가능
- 회원 : 즉시 구매, 장바구니 구매 가능
- 배송 정보 입력 후 결제
    - 이름 : 필수 입력
    - 연락처 : -없이 10~11자
    - 주소 : 우편번호, 도로명 주소, 상세 주소 필수 입력
- 결제
    - IMPORT API 활용
    - 결제 금액에 대한 검증 후 결제 완료
<img src="https://user-images.githubusercontent.com/74487747/223669523-cb7380d3-d586-4efa-b250-ecd760a44d92.png" width="700">

### 마이 페이지

- 회원만 이용 가능
- 구매 내역 확인
- 회원 정보 변경
    - 이메일, 아이디, 이름, 전화번호, 주소 변경 가능
    - 이메일 : 이메일 형식
    - 전화번호 : -없이 10~11자
    - 이름 : 필수 입력
<img src="https://user-images.githubusercontent.com/74487747/223955206-8db97bbb-a087-48a6-9d9b-13f2209e5549.png" width="700">

### 관리자 페이지

- Admin으로 로그인 시 관리자 페이지 입장 가능
- 회원, 상품, 주문 목록 READ/DELETE 가능
- 카테고리 등록 가능
- 상품 등록 가능
    - 카테고리, 브랜드, 상품명, 상세 내용, 가격, 이미지 등록
    - 상품명, 가격 : 필수 입력
    - 이미지 : 이미지 파일 업로드, 상품 등록 후 /upload에 저장
<img src="https://user-images.githubusercontent.com/74487747/223673174-3a01d364-c5a4-41be-aaf4-7c9db2adbfd6.png" width="700">

## ERD
![화면 캡처 2023-03-07 023452](https://user-images.githubusercontent.com/74487747/223187254-e34ce0a0-8932-4419-8352-78d1e1a7a70f.png)
