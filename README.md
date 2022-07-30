# Member_Board_REST-API [![Build Status](https://app.travis-ci.com/min9288/Member_Board_REST-API.svg?branch=main)](https://app.travis-ci.com/min9288/Member_Board_REST-API)
> 회원과 게시판 기능이 있는 애플리케이션 서버입니다.

</br>

## 서비스 개요
> REST API 기반의 게시판 기능 구현 서버입니다.

</br>

## 서비스 주요기능
- 회원
  - 회원정보는 아이디, 이메일, 비밀번호, 닉네임으로 구성됩니다.
  - 아이디는 UUID로 구성되며, 인덱스를 대신하며 고유성을 유지합니다.
  - 회원가입은 중복가입을 방지하기 위해 이메일 인증을 통해서 가입할 수 있습니다.
  - 로그인시 토큰이 발급되며, 일부 API를 제외하고는 토큰이 사용해서 API에 접근할 수 있습니다.
  - 회원 UUID를 가지고 내 정보를 조회할 수 있습니다.
    - 로그인한 유저 정보를 가져와서 현재 조회하려는 유저와 확인하려는 유저와 동일한지 검증 후 조회를 할 수 있습니다.

</br>

- 게시판
  - 로그인한 회원만 게시판에 접근할 수 있습니다.
    - 토큰을 통해 API에 접근하기 때문에 로그인한 회원만 게시판에 접근 할 수 있습니다.
  - 로그인한 회원은 게시판에 글을 작성할 수 있습니다.
  - 작성한 글을 본인만 볼 수 있게 "잠금설정" 할 수 있습니다.
    - 게시글 작성 및 수정시 PUBLIC_BOARD / PRIVATE_BOARD로 해당 게시글을 잠금 설정 할 수 있습니다.
    - 게시글 전체조회시 PRIVATE_BOARD를 제외한 PUBLIC_BOARD만 노출됩니다.
    - 내가 작성한 전체 게시글을 확인 할 때는 잠금유무 상관없이 게시글들을 확인 할 수 있습니다.
    - 게시글 상세 조회시에는, 게시글이 PRIVATE_BOARD라면 본인만 확인이 가능합니다.
    - 게시글 상세 조회 후 해당 게시글의 조회수가 1 증가됩니다.
  - 작성한 글을 수정할 수 있습니다.
    - 로그인한 유저 정보를 가져와서 현재 수정하려는 유저와 게시글 작성자와 동일한지 검증 후 수정이 처리 됩니다.
  - 작성한 글을 삭제할 수 있습니다.
    - 로그인한 유저 정보를 가져와서 현재 삭제하려는 유저와 게시글 작성자와 동일한지 검증 후 삭제가 처리 됩니다.


</br>

## 기술 스택
- Spring Boot (API Server)
- Spring Security (Security)
- JPA & QueryDSL (ORM)
- MySQL (8.0 / Naver Cloud DB) (RDB)
- AWS EC2 (Infra)
- AWS S3 (Build Store)
- Travis & Codedeploy (CI/CD)
- Nginx (Reverse Proxy Server)
- Postman (Documentation)
- DBeaver (Database tool)
- IntelliJ (Development tool)

</br>

## 가용 서버
- 3.39.20.133
  - 실사용은 위 서버로 진행하시면 됩니다.

</br>

## 기능 및 사용방법
> JWT를 적용하여 일부 API를 제외하고는 토큰을 통해 접근 할 수 있습니다.

> 토큰 없이 접근가능한 API는 다음과 같습니다.
> - "/sing/**" - 가입 / 로그인 / 토큰 재발행 / 이메일 인증
> - "/exception/**" - 예외처리
> - "/profile" - 작동되고 있는 서버 프로필 확인 
> - "/actuator/**" - 애플리케이션 상태 확인
> - "/test" - 테스트

</br>

### 회원가입
  
