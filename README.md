# Member_Board_REST-API
> 회원과 게시판 / 쇼핑 기능이 있는 애플리케이션 서버입니다.
> 
> 기존의 회원-게시판 기능에서 쇼핑 기능을 추가하였습니다.
>
> 지속적으로 기존 코드에 대한 리펙토링을 진행 중 입니다.


</br>

## 서비스 개요
> REST API 기반의 회원-게시판-쇼핑 기능 구현 서버입니다.


</br>

## 서비스 주요기능

<details><summary>세부정보</summary>

- 회원
  - 수정 및 조회는 로그인한 유저정보를 가지고 진행됩니다.
  - 가입
    - 이메일, 비밀번호, 닉네임으로 가입합니다.
    - 회원가입 후 중복가입을 방지하기 위해 이메일 인증을 진행합니다.
  - 로그인
    - 로그인시 토큰이 발급되며, 일부 API를 제외하고는 토큰이 사용해서 API에 접근해야 합니다.
  - 회원 정보 수정
    - 닉네임과 보유 자산을 수정할 수 있습니다.
  - 조회
    - 내 정보를 조회합니다.

</br>

- 게시판
  - 로그인한 회원만 게시판에 접근할 수 있습니다.
    - 토큰을 통해 API에 접근하기 때문에 로그인한 회원만 게시판에 접근 할 수 있습니다.
  - 게시글의 수정 / 삭제는 작성자 본인만 할 수 있습니다.
  - 등록 및 수정
    - 로그인한 회원은 게시판에 글을 작성할 수 있습니다.
    - 작성한 글을 본인만 볼 수 있게 "잠금설정" 할 수 있습니다.
    - 게시글 작성 및 수정시 PUBLIC_BOARD / PRIVATE_BOARD로 해당 게시글을 잠금 설정합니다.
  - 조회
    - 게시글 전체조회시 PRIVATE_BOARD를 제외한 PUBLIC_BOARD만 노출됩니다.
    - 내가 작성한 전체 게시글을 확인 할 때는 잠금유무 상관없이 게시글들을 확인 할 수 있습니다.
    - 게시글 상세 조회시에는, 게시글이 PRIVATE_BOARD라면 본인만 확인이 가능합니다.
    - 게시글 상세 조회 후 해당 게시글의 조회수가 1 증가됩니다.
  - 삭제
    - 작성자가 본인 게시글을 삭제합니다.

</br>

- 쇼핑
  - 상품
    - 등록
      - 아래 정보로 판매되는 상품을 등록합니다.
      - 카테고리, 이름, 가격, 수량, 포인트적립률, 밴더사
    - 조회
      - 전체 / 카테고리별 / 벤더사별 / 상품명 / 상품ID 별로 조회합니다.
    - 수정
      - 등록 때 기입한 정보들을 수정합니다.
    - 삭제
      - 상품ID 값으로 삭제합니다.
  - 장바구니
    - 등록
      - 장바구니에 상품을 추가할 때, 장바구니가 없다면 생성됩니다 (최초 1회).
      - 상품ID 와 구매수량으로 상품을 추가합니다.
    - 수정
      - 장바구니에 있는 상품 수량을 변경합니다.
    - 조회
      - 장바구니에 있는 상품들을 조회합니다.
    - 선택 삭제
      - 장바구니에 있는 특정 상품을 삭제합니다.
    - 전체 삭제
     - 장바구니에 있는 전체 상품들을 삭제합니다.
  - 주문
    - 주문방식은 CASH / POINT가 존재합니다.
      - CASH로 주문 시, 주문금액에 따라 회원 보유 자금이 차감 및 상품 포인트적립률에 따른 포인트 적립이 이루어집니다.
      - POINT로 주문 시, 주문금액에 따라 회원 보유 포인트가 차감되고 포인트 적립은 되지 않습니다. 
    - 일반 주문
      - 상품ID와 주문방식(CASH / PONT), 주문수량을 기입하여 주문합니다.
    - 장바구니 주문
      - 주문방식을 기입하여 장바구니에 있는 상품들을 주문합니다.
    - 조회
      - 전체 주문내역들을 조회합니다.

</details>


</br>

## 프로젝트 히스토리

- 히스토리
  - 2022/07/25 ~ 31 : 회원-게시판 기능 구현
  - ~ 현재 
    - 기존 코드 리펙토링
      - URL 리소스를 수정
        - Before ex)  localhost/board/myBoard/{email}
        - After ex) localhost/boards/my-board-list/email/{email}
      - 정보 수정 코드 수정
        - before ex) board.setTitle(수정내용) / boardRepository.save(board)
        - After ex) board = board.update(requestDTO.toEntity())
    - 쇼핑 (상품 / 장바구니 / 주문) 기능 추가 구현

</br>

## 기술 스택
- `Spring Boot (API Server)`
- `Spring Security (Security)`
- `JPA & QueryDSL (ORM)`
- `MySQL (8.0 / Google Cloud SQL) (RDB)`
- `AWS EC2 (Infra)`
- `AWS S3 (Build Store)`
- `~Travis CI & AWS Codedeploy (CI/CD)~ - travis 무료 크레딧 만료로 git action으로 변경`
- `GitHub Actions & AWS Codedeploy (CI/CD)`
- `Nginx (Reverse Proxy Server)`
- `Postman (Documentation)`
- `DBeaver (Database tool)`
- `IntelliJ (Development tool)`

</br>

## 도메인 명세

* [도메인 명세 문서](https://min9288.notion.site/964d8cf0eff147e68874f75a7a2563d5)

</br>

## API 명세

<details><summary>세부정보</summary>

<br>


  * <b>Sign & Member API</b> <br>

    | Title | HTTP Method | URL | Request | Response | Auth
    |:-------:|:---:|:-------:|:-------:|:-------:|:---:|
    | 회원가입 | POST | /signs/register | { "email" : "이메일주소", "password" : "패스워드", "nickname" : "닉네임" } | SingleResult\<MemberRegisterResponseDTO\> | NO
    | 이메일 인증 | GET | /signs/confirm-email | {} | SingleResult\<String\> | NO
    | 로그인 | POST | /signs/login | {"email" : "이메일주소", "password" : "패스워드"} | SingleResult\<MemberLoginResponseDTO\> | NO
    | 토큰 재발행 | POST | /signs/reissue | {"accessToken" : "엑세스 토큰", "refreshToken" : "리프레시 토큰"} | SingleResult\<TokenResponseDTO\> | NO
    | 내 정보 보기 | GET | /members | {} | SingleResult\<MemberGetInfoResponseDTO\> | YES
    | 내 정보 수정 | PUT | /members | {"nickname" : "수정할 닉네임", "money" : 보유 현금 기입} | SingleResult\<MemberUpdateResponseDTO\> | YES
  
<br>


  * <b>Board API</b> <br>

    | Title | HTTP Method | URL | Request | Response | Auth
    |:---:|:---:|:---:|:---:|:---:|:---:|
    | 게시글 작성 | POST | /boards | { "title" : "제목", "contents" : "내용", "boardStatus" : "PRIVATE_BOARD / PUBLIC_BOARD" } | SingleResult\<BoardWriteResponseDTO\> | YES
    | 게시글 전체 조회 | GET | /boards | {} | MultipleResult\<BoardGetBoardListResponseDTO\> | YES
    | 내가 작성한 게시글 전체 조회 | GET | /my-board-list/email/{email} | {} | MultipleResult\<BoardGetBoardListResponseDTO\> | YES
    | 게시글 상세 조회 | GET | /boards/board-detail/boardUUID/{boardUUID} | {} | SingleResult\<BoardGetBoardResponseDTO\> | YES
    | 게시글 수정 | PUT | /boards/{boardUUID} |{ "title" : "수정할 제목", "contents" : "내용", "boardStatus" : "PUBLIC_BOARD / PRIVATE_BOARD" } | SingleResult\<BoardUpdateResponseDTO\> | YES
    | 게시글 삭제 | DELETE | /boards/{boardUUID} | {} | SingleResult\<BoardDeleteResponseDTO\> | YES
  
<br>


  * <b>Product API</b> <br>

    | Title | HTTP Method | URL | Request | Response | Auth
    |:---:|:---:|:---:|:---:|:---:|:---:|
    | 상품 등록 | POST | /products | { "category" : "FOOD / HOUSE_WARE / GIFT_CARD", "name" : "price" : 상품가격, "quantity" : 상품수량, "pointRate" : 상품별 포인트 적립률, "vander" : "GS25 / Homeplus / CGV" } | SingleResult\<ProductAddResponseDTO\> | YES
    | 상품 수정 | PUT | /products/{productUUID} | { "category" : "FOOD / HOUSE_WARE / GIFT_CARD", "name" : "price" : 상품가격, "quantity" : 상품수량, "pointRate" : 상품별 포인트 적립률, "vander" : "GS25 / Homeplus / CGV" } | SingleResult\<ProductUpdateResponseDTO\> | YES
    | 상품 전체 조회 | GET | /products | {} | MultipleResult\<ProductGetProductListResponseDTO\> | YES
    | 상품 카테고리별 조회 | GET | /products/category/{category} | {} | MultipleResult\<ProductGetProductListResponseDTO\> | YES
    | 상품 벤더사별 조회 | GET | /products/vander/{vander} | {} | MultipleResult<ProductGetProductListResponseDTO> | YES
    | 상품명별 조회 | GET | /products/product-name/{productName} | {} | MultipleResult<ProductGetProductListResponseDTO> | YES
    | 상품 UUID로 조회 | GET | /products/product-uuid/{productUUID} | {} | SingleResult\<ProductGetProductListResponseDTO\> | YES
    | 상품 삭제 | DELETE | /products/{productUUID} | {} | String | YES
  
<br>

  * <b>Cart API</b> <br>

    | Title | HTTP Method | URL | Request | Response | Auth
    |:---:|:---:|:---:|:---:|:---:|:---:|
    | 장바구니에 상품 추가 | POST | /carts | { "productUUID" : "상품UUID", "orderCount" : 장바구니 상품 수량  } | MultipleResult\<CartGetCartItemResponseDTO\> | YES
    | 장바구니 상품 수량 수정 | PUT | /carts/cart-item-uuid/{cartItemUUID | { "orderCount" : 수정할 상품 수량 수} | SingleResult\<CartGetCartItemResponseDTO\> | YES
    | 장바구니 조회 | GET | /carts | {} | MultipleResult\<CartGetCartItemResponseDTO\>  | YES
    | 장바구니 상품 선택 삭제 | DELETE | /carts/delete-cart-item/cart-item-uuid/{cartItemUUID} | {} | String | YES
    | 장바구니 상품 전체 삭제 | DELETE | /carts/delete-all-cart-item | {} | String | YES

  * <b>Order API</b> <br>

    | Title | HTTP Method | URL | Request | Response | Auth
    |:---:|:---:|:---:|:---:|:---:|:---:|
    | 일반 주문 | POST | /orders/push-order | { "productUUID" : "상품UUID", "orderMethod" : "CASH / POINT", "orderCount" : 구매수량 } | SingleResult\<OrderResponseDTO\> | YES
    | 장바구니 주문 | POST | /push-order-by-cart/order-method/{orderMethod} | {} | MultipleResult\<OrderResponseDTO\> | YES
    | 전체 주문 내역 | GET | /orders | {} | MultipleResult\<OrderResponseDTO\>  | YES

</details>

<br>
<br>

## 가용 서버
- 43.200.144.129
  - 실 사용은 위 서버로 진행할 수 있습니다.

</br>

## 서비스 기능 안내
> JWT를 적용하여 일부 API를 제외하고는 토큰을 통해 접근 할 수 있습니다.
>
> 토큰 없이 접근가능한 API는 다음과 같습니다.
> - "/sings/**" - 가입 / 로그인 / 토큰 재발행 / 이메일 인증
> - "/exception/**" - 예외처리
> - "/profile" - 작동되고 있는 서버 프로필 확인 
> - "/actuator/**" - 애플리케이션 상태 확인
> - "/test" - 테스트


<br/>

### 테스트 계정 및 토큰 인증 안내
```bash 
* email : wjdalsdnchlr@naver.com
* password : qwer1234

---

* HTTP 헤더
key : X-AUTH-TOKEN
value : 엑세스 토큰값 (로그인시 Response 값으로 확인할 수 있습니다.)

```

<br/>

## 회원 서비스

<br/>

### 회원가입

<details><summary>세부정보</summary>
  
```bash 
Post
* 회원가입 : 43.200.144.129/signs/register
{
    "email" : "이메일주소",
    "password" : "패스워드",
    "nickname" : "닉네임"
}

```

<p align="center">
<img src = "./img/join.png" width=100%>
</p>

> * 3가지 정보로 간단한 회원가입을 구현했습니다.
> * 회원가입 후 이메일 인증을 진행해야 정상적으로 로그인이 됩니다.
> * 이메일 미 인증 후 로그인 시 이메일 인증을 해야된다는 오류 메시지가 전달됩니다.

</details>

<br/>


### 이메일 인증

<details><summary>세부정보</summary>
  
```bash 
Get
* 이메일 인증 : 43.200.144.129/signs/confirm-email?email=이메일주소&authToken=엑세스토큰
* 인증 유효시간 : 5분
* 회원가입 시 Response 값으로 나왔던 엑세스 토큰과 이메일주소로 진행하면 됩니다.
* 인증방식 : 
  - Postman을 통한 인증방식
  - 수신받은 인증 메일을 통한 인증방식
* 2가지 방식 중 하나를 선택하시면 됩니다.
* 대략 30초 ~ 2분 내로 인증메일이 수신됩니다.

```
<p align="center">
<img src = "./img/emailAuth1.png" width=100%>
<img src = "./img/emailAuth2.png" width=100%>
</p>

> * 3가지 정보로 간단한 회원가입을 구현했습니다.
> * 회원가입 후 이메일 인증을 진행해야 정상적으로 로그인이 됩니다.
> * 이메일 미 인증 후 로그인 시 이메일 인증을 해야된다는 오류 메시지가 전달됩니다.

</details>

<br/>

### 로그인

<details><summary>세부정보</summary>
  
```bash 
Post
* 로그인 : 43.200.144.129/signs/login
* 엑세스 토큰 유효시간 : 30분
* 재발행 토큰 유효기간 : 7일

{
    "email" : "이메일주소",
    "password" : "패스워드"
}

```
<p align="center">
<img src = "./img/login.png" width=100%>
</p>

> * Response 값에 있는 토큰으로 토큰 없이 접근 가능한 API외에 다른 API들을 이용할 때 사용하시면 됩니다.
> * Response 값에 재발행 토큰 값이 있는데, 이것으로 토큰 만료 후 토큰을 재발행 할 수 있습니다.

</details>

<br/>

### 토큰 재발행

<details><summary>세부정보</summary>
  
```bash 
Post
* 토큰 재발행 : 43.200.144.129/signs/reissue
{
    "accessToken" : "엑세스 토큰",
    "refreshToken" : "리프레시 토큰"
}

```
<p align="center">
<img src = "./img/reissue.png" width=100%>
</p>

> * 토큰 재발행 시, 로그인할때 나온 Response 값(accessToken, refreshToken)으로 사용하시면 됩니다.

</details>

<br/>

### 토큰 사용 방법

<details><summary>세부정보</summary>
  
```bash 
* 각 API의 Headers 에서 Key값과 Value값을 넣어주세요
* key : X-AUTH-TOKEN
* value : access 토큰
```
<p align="center">
<img src = "./img/tokenAuth.png" width=100%>
</p>

</details>

<br/>

### 내 정보 보기

<details><summary>세부정보</summary>
  
```bash 
Get
* 내 정보 보기 : 43.200.144.129/members
```
<p align="center">
<img src = "./img/myInfo.png" width=100%>
</p>

</details>

<br/>

### 내 정보 수정

<details><summary>세부정보</summary>
  
```bash 
Put
* 내 정보 수정 : 43.200.144.129/members
* 닉네임과 보유 자금을 수정 할 수 있습니다.

{
    "nickname" : "수정할 닉네임",
    "money" : 보유 현금 기입
}

```
<p align="center">
<img src = "./img/myInfoUpdate.png" width=100%>
</p>

</details>


<br/>

## 게시판 서비스

<br/>

### 게시글 작성

<details><summary>세부정보</summary>
  
```bash
Post 
* 게시글 작성 : 43.200.144.129/boards
* boardStatus : PRIVATE_BOARD - 게시글 잠금 / PUBLIC_BOARD - 공개 게시글
{
    "title" : "제목",
    "contents" : "내용",
    "boardStatus" : "PRIVATE_BOARD / PUBLIC_BOARD"
}
```
<p align="center">
<img src = "./img/write.png" width=100%>
</p>

> * boardStatus를 통해 게시글을 잠금할지 공개할지 선택할 수 있습니다.

</details>

<br/>

### 게시글 전체 조회

<details><summary>세부정보</summary>
  
```bash
Get 
* 게시글 전체 조회 : 43.200.144.129/boards
```
<p align="center">
<img src = "./img/getAll.png" width=100%>
</p>

> * boardStatus가 PUBLIC_BOARD인 전체 게시글을 조회 할 수 있습니다.

</details>

<br/>

### 내가 작성한 게시글 전체 조회
  
<details><summary>세부정보</summary>

```bash
Get 
* 내가 작성한 게시글 전체 조회 : 43.200.144.129/boards/my-board-list/email/{이메일주소}
```
<p align="center">
<img src = "./img/myBoardInfo.png" width=100%>
<img src = "./img/failureBoardAuth.png" width=100%>
</p>

> * 내가 작성한 PUBLIC_BOARD, PRIVATE_BOARD 모두 조회를 할 수 있습니다.
> * 다만, 작성한 유저가 아닌 다른 유저가 내 게시물을 조회하려고 할 시 경고를 출력합니다.

</details>

<br/>

### 게시글 상세 조회

<details><summary>세부정보</summary>
  
```bash
Get 
* 게시글 상세조회 : 43.200.144.129/boards/board-detail/boardUUID/{게시글UUID}
```
<p align="center">
<img src = "./img/boardDetail.png" width=100%>
</p>

> * 전체 조회 / 내가 작성한 글 전체 조회의 Response 값에서 상세조회 하려는 게시글 UUID을 복사하여 사용하면 됩니다.
> * 게시물의 상세정보를 볼 수 있습니다. 
> * 조회 시, 조회수가 1 증가합니다.
> * PRIVATE_BOARD 게시물의 경우 본인이 아니면 조회 할 수 없습니다.
> * 작성한 유저가 아닌 다른 유저가 PRIVATE_BOARD 게시물을 조회하려고 할 시 경고를 출력합니다.

</details>

<br/>

### 게시글 수정

<details><summary>세부정보</summary>
  
```bash
Put 
* 게시글 수정 : 43.200.144.129/boards/{게시글UUID}

{
    "title" : "수정할 제목",
    "contents" : "내용",
    "boardStatus" : "PUBLIC_BOARD / PRIVATE_BOARD"
}
```
<p align="center">
<img src = "./img/updateBoard.png" width=100%>
</p>

> * 내가 작성한 글을 수정할 수 있습니다.
> * boardStatus 도 수정이 가능하며, 여기서 게시글을 잠금해제하거나 잠금할 수 있습니다.
> * 타인이 내 게시글을 수정할려고 할 시, 경고가 출력됩니다.

</details>

<br/>

### 게시글 삭제

<details><summary>세부정보</summary>
  
```bash
DELETE 
* 게시글 삭제 : 43.200.144.129/boards/delete/{게시글UUID}
```
<p align="center">
<img src = "./img/deleteBoard.png" width=100%>
</p>

> * 내가 작성한 글을 삭제할 수 있습니다.
> * 타인이 내 게시글을 삭제할려고 할 시, 경고가 출력됩니다.

</details>

<br/>

## 상품 서비스

<br/>

### 상품 등록

<details><summary>세부정보</summary>
  
```bash
POST 
* 상품 등록 : 43.200.144.129/products

{
    "category" : "FOOD / HOUSE_WARE / GIFT_CARD",
    "name" : "상품명",
    "price" : 상품가격 / int,
    "quantity" : 상품수량 / int,
    "pointRate" : 상품별 포인트 적립률 / double,
    "vander" : "GS25 / Homeplus / CGV"
}

```
<p align="center">
<img src = "./img/addProduct.png" width=100%>
</p>

> * 구매할 수 있는 상품들을 등록합니다.
> * category 와 vander 는 enum값으로 아래와 같이 고정되어 있습니다.
> * category (FOOD / HOUSE_WARE / GIFT_CARD)
> * vander (GS25 / Homeplus / CGV)

</details>

<br/>

### 상품 수정

<details><summary>세부정보</summary>
  
```bash
Put 
* 상품 수정 : 43.200.144.129/products/{상품UUID}

{
    "category" : "FOOD / HOUSE_WARE / GIFT_CARD",
    "name" : "상품명",
    "price" : 상품가격 / int,
    "quantity" : 상품수량 / int,
    "pointRate" : 상품별 포인트 적립률 / double,
    "vander" : "GS25 / Homeplus / CGV"
}

```
<p align="center">
<img src = "./img/updateProduct.png" width=100%>
</p>


</details>

<br/>

### 상품 전체조회

<details><summary>세부정보</summary>
  
```bash
Get 
* 상품 전체조회 : 43.200.144.129/products
```
<p align="center">
<img src = "./img/getAllProduct.png" width=100%>
</p>

</details>

<br/>

### 상품 카테고리별 조회

<details><summary>세부정보</summary>
  
```bash
Get 
* 상품 카테고리별 조회 : 43.200.144.129/products/category/{FOOD / HOUSE_WARE / GIFT_CARD}
```
<p align="center">
<img src = "./img/getCategoryProduct.png" width=100%>
</p>

</details>

<br/>

### 상품 카테고리별 조회

<details><summary>세부정보</summary>
  
```bash
Get 
* 상품 카테고리별 조회 : 43.200.144.129/products/category/{FOOD / HOUSE_WARE / GIFT_CARD}
```
<p align="center">
<img src = "./img/getCategoryProduct.png" width=100%>
</p>

</details>

<br/>

### 상품 벤더사별 조회

<details><summary>세부정보</summary>
  
```bash
Get 
* 상품 벤더사별 조회 : 43.200.144.129/products/vander/{GS25 / Homeplus / CGV}
```
<p align="center">
<img src = "./img/getVenderProduct.png" width=100%>
</p>

</details>

<br/>

### 상품 이름별 조회

<details><summary>세부정보</summary>
  
```bash
Get 
* 상품 이름별 조회 : 43.200.144.129/products/product-name/{상품이름}
```
<p align="center">
<img src = "./img/getNameProduct.png" width=100%>
</p>

</details>

<br/>

### 상품 UUID 조회

<details><summary>세부정보</summary>
  
```bash
Get 
* 상품 UUID 조회 : 43.200.144.129/products/product-uuid/{상품UUID}
```
<p align="center">
<img src = "./img/getAllProduct.png" width=100%>
</p>

</details>

<br/>

### 상품 삭제

<details><summary>세부정보</summary>
  
```bash
Delete 
* 상품 삭제 : 43.200.144.129/products/{상품UUID}
```
<p align="center">
<img src = "./img/deleteProduct.png" width=100%>
</p>

</details>

<br/>

## 장바구니 서비스

<br/>

### 장바구니 상품 추가

<details><summary>세부정보</summary>
  
```bash
Post 
* 장바구니 상품 추가 : 43.200.144.129/carts

{
    "productUUID" : "상품UUID",
    "orderCount" : 장바구니 상품 수량
}

```
<p align="center">
<img src = "./img/addCart.png" width=100%>
</p>

</details>

<br/>

### 장바구니 상품 수량 수정

<details><summary>세부정보</summary>
  
```bash
Put 
* 장바구니 상품 수량 수정 : 43.200.144.129/carts/cart-item-uuid/{cartItemUUID}
* cartItemUUID : cartUUID 가 아닌 장바구니상품 UUID 입니다.

{
    "orderCount" : 수정할 상품 수량 수
}

```
<p align="center">
<img src = "./img/updateCartItem.png" width=100%>
</p>

</details>

<br/>

### 장바구니 조회

<details><summary>세부정보</summary>
  
```bash
Get 
* 장바구니 조회 : 43.200.144.129/carts
```
<p align="center">
<img src = "./img/getCart.png" width=100%>
</p>

</details>

<br/>

### 장바구니 상품 선택 삭제

<details><summary>세부정보</summary>
  
```bash
Delete 
* 장바구니 상품 선택 삭제 : 43.200.144.129/carts/delete-cart-item/cart-item-uuid/{장바구니상품 UUID}
```
<p align="center">
<img src = "./img/deleteSeleteCartItem.png" width=100%>
</p>

</details>

<br/>

### 장바구니 상품 전체 삭제

<details><summary>세부정보</summary>
  
```bash
Delete 
* 장바구니 상품 전체 삭제 : 43.200.144.129/carts/delete-all-cart-item
```
<p align="center">
<img src = "./img/deleteAllCartItem.png" width=100%>
<img src = "./img/deleteAllCartItemAfterCart.png" width=100%>
</p>

> 두번째 사진에서, 장바구니 전체 삭제 후 비어있는 장바구니를 확인할 수 있습니다. 

</details>

<br/>

## 주문 서비스

<br/>

### 일반 주문

<details><summary>세부정보</summary>
  
```bash
Post 
* 일반 주문 : 43.200.144.129/orders/push-order

{
    "productUUID" : "상품UUID",
    "orderMethod" : "CASH / POINT",
    "orderCount" : 구매수량
}

```
<p align="center">
<img src = "./img/pushOrder.png" width=100%>
<img src = "./img/pushOrderMemberInfo.png" width=100%>
</p>

> * 두번째 사진에서, 주문 후 회원 보유자금 차감 및 포인트 적립된 상태를 확인할 수 있습니다.
> * orderMethod가 CASH라면, 포인트가 적립됩니다.
> * orderMethod가 POINT라면, 포인트가 적립되지않고 주문금액에 따라 보유 포인트가 차감됩니다.

</details>

<br/>

### 장바구니 상품 주문

<details><summary>세부정보</summary>
  
```bash
Post 
* 장바구니 주문 : 43.200.144.129/orders/push-order-by-cart/order-method/{CASH / POINT}

* 장바구니에 등록한 상품들 전체가 결제됩니다.

```
<p align="center">
<img src = "./img/pushOrderByCart.png" width=100%>
</p>

> * 장바구니에 등록된 상품들 전체가 결제됩니다.
> * Response 값으로 보이는 "orderPrice"는 장바구니 주문상품 1건당 금액입니다.
> * "orderTotalPrice" 이번 주문 총 결제금액 입니다.

</details>

<br/>

### 전체 주문 내역 조회

<details><summary>세부정보</summary>
  
```bash
Get 
* 전체 주문 내역 조회 : 43.200.144.129/orders

```
<p align="center">
<img src = "./img/orderHistory.png" width=100%>
</p>

> * 주문내역 Response값에서 "orderUUID" 를 통해 같은 주문건인지 확인 할 수 있습니다.

</details>

<br/>


## Spring Boot (API Server)
> 요청한 데이터를 JSON으로 response 합니다.

- 구조는 다음과 같습니다.
  - security : security, jwt 관련 기능들을 관리합니다.
  - exception : custom exception message들을 관리합니다.
  - web
    - controller : API를 관리합니다.
  - domain
    - dto : 도메인별 request / response DTO를 관리합니다.
    - entity : 도메인별 entity를 관리합니다.
    - repository : 도메인별 JPA / QueryDSL를 관리합니다.
    - service : 도메인별 business logic을 수행하고 repository를 이용해서 결과값을 처리합니다.

<br/>

## Spring Security (Security)
> Security 설정을 통해 인가된 사용자만 특정 API에 접근할 수 있도록 제한합니다.

- 구조는 다음과 같습니다.
  - Http basic Auth : disable -> REST API 이므로 기본설정 미사용
  - CSRF : disable  -> REST API이므로 CSRF 보안 미사용
  - Form Login : disable  -> REST API 이므로 Form Login 미사용
  - Session Creation Policy : STATELESS -> JWT로 인증하므로 세션 미사용
  - AuthorizeRequest : 
    - “ /api/sign/** ” : 회원가입 / 로그인 / 이메일 인증 / 토큰 관련 API를 허용합니다.
    - “ /exception/** ” - 예외처리 API를 허용합니다.
    - “ /test ” - 테스트 API를 허용합니다.
    - " /profile "  - profile API를 허용합니다.
    - " /actuator/** " - 애플리케이션 상태 확인을 위한 actuator API를 허용합니다. 
  - Token Authentication Filter : UsernamePasswordAuthenticationFilter.class -> JWT 필터 추가

<br/>

## JPA & QueryDSL (ORM)
> JPA를 사용하여 객체 중심 domain 설계 및 반복적인 CRUD 작업을 간단히 DB 데이터를 조회함으로써 대체합니다.
> JPA에서 구현하기 어려운 SQL문들을 QueryDSL으로 작성합니다.
> 다만, 이번에 QueryDSL을 사용하기 위한 어려운 SQL문들은 없었으나 QueryDSL을 사용해보기 위해 적용하였습니다.

- 구조는 다음과 같습니다. (ex. board)
  - Board (Domain Class)
  - BoardRepository (JPA Interface)
  - BoardCustomRepository (QueryDSL Interface)
  - BoardCustomRepositoryImpl (QueryDSL Implements Class)
    - QueryDSL 사용예시
    ``` bash
      public List<Board> findAllMyBoard(String email) {
        List<Board> boardList = jpaQueryFactory.selectFrom(board)
                .where(board.writer.email.eq(email))
                .leftJoin(board.writer, member)
                .fetch();
        return boardList;
    }
    ```

<br/>

## ~Travis CI & Codedeploy & EC2 (CI/CD, Infra)~ - 무료계정 만료로, 깃액션으로 변경

<details><summary>세부정보</summary>

> * Travis CI 과 AWS Codedeploy를 통한 배포 자동화와 Nginx를 통한 무중단 배포를 구축하였습니다.
> * EC2의 SSH 접근권한은 제 IP만 허용했습니다.
> * 보안성을 강화하기 위해, 루트 계정이 아닌 IAM 계정에서 사용자를 생성하고  S3 및 Codedeploy 접근권한 부여하여 사용하였습니다.


- 배포 구조는 다음과 같습니다.
<p align="center">
  <img src = "./img/ci&cd.png" width=100%>
</p>

</details>

<br/>

## GitHub Actions & Codedeploy & EC2 (CI/CD, Infra)
> * GitHub Actions 과 AWS Codedeploy를 통한 배포 자동화와 Nginx를 통한 무중단 배포를 구축하였습니다.
> * EC2의 SSH 접근권한은 제 IP만 허용했습니다.
> * 보안성을 강화하기 위해, 루트 계정이 아닌 IAM 계정에서 사용자를 생성하고  S3 및 Codedeploy 접근권한 부여하여 사용하였습니다.


- 배포 구조는 다음과 같습니다.
<p align="center">
  <img src = "./img/gitActions_CiCD.png" width=100%>
</p>

- EC2에서 Nginx와 스프링부트 JAR 2개를 사용하여 무중단 배포를 구현 하였습니다.
- Nginx에는 80(http), 443(https) 포트를 할당 하였습니다.
- 스프링부트1(profile : set1)은 8081 포트로, 스프링부트2(profile : set2)은 8082포트로 실행합니다. 
- Nginx와 연결된 스프링부트1(set1)이 구동 중 일 때, 배포를 하면 Nginx가 바라보고 있지 않은 스프링부트2(set2)에서 배포가 진행됩니다. 
- 배포가 끝나면 nginx reload를 통해 8081(set1) 대신 8082(set2)를 바라보게 합니다.
- 이러한 과정을 통해 배포시에도 서비스가 중단되지 않고 무중단 배포가 가능하게 됩니다. 


<br/>

## 패키지 구조

```bash

domain
  ㄴ board
      ㄴ dto
          ㄴ requestDTO
              ㄴ BoardUpdateRequestDTO.class
              ㄴ BoardWriteRequestDTO.class
          ㄴ responseDTO
              ㄴ BoardDeleteResponseDTO.class
              ㄴ BoardGetBoardListResponseDTO.class
              ㄴ BoardGetBoardResponseDTO.class
              ㄴ BoardUpdateResponseDTO.class
              ㄴ BoardWriteResponseDTO.class
      ㄴ entity
          ㄴ Board.class
          ㄴ enumPackage
              ㄴ BoardStatus.enum
      ㄴ repository
          ㄴ BoardCustomRepository.interface
          ㄴ BoardCustomRepositoryImpl.class
          ㄴ BoardRepository.interface
      ㄴ service 
          ㄴ BoardService.interface
          ㄴ BoardServiceImpl.class
  ㄴ email
      ㄴ dto
          ㄴ requestDTO
              ㄴ EmailAuthRequestDTO.class
      ㄴ entity
          ㄴ EmailAuth.class
      ㄴ repository
          ㄴ EmailAuthCustomRepository.interface
          ㄴ EmailAuthCustomRepositoryImpl.class
          ㄴ EmailAuthRepository.interface
      ㄴ service 
          ㄴ EmailService.class
  ㄴ member
      ㄴ dto
          ㄴ requestDTO
              ㄴ MemberLoginRequestDTO.class
              ㄴ MemberRegisterRequestDTO.class
              ㄴ TokenRequestDto.class
          ㄴ responseDTO
              ㄴ MemberGetInfoResponseDTO.class
              ㄴ MemberLoginResponseDTO.class
              ㄴ MemberRegisterResponseDTO.class
              ㄴ TokenResponseDTO.class
      ㄴ entity
          ㄴ Member.class
          ㄴ enumPackage
              ㄴ Role.enum
      ㄴ repository
          ㄴ MemberRepository.interface
      ㄴ service 
          ㄴ MemberService.interface
          ㄴ MemberServiceImpl.class
          ㄴ SingService.class
  ㄴ response
      ㄴ service 
          ㄴ ResponseService.class
  ㄴ response
      ㄴ service 
          ㄴ ResponseService.class
  ㄴ result
      ㄴ MultipleResult.class
      ㄴ Result.class
      ㄴ SingleResult.class
exception
  ㄴ AuthenticationEntryPointException.class
  ㄴ BoardDeleteFailureException.class
  ㄴ BoardNotFoundException.class
  ㄴ EmailAuthTokenNotFountException.class
  ㄴ EmailNotAuthenticatedException.class
  ㄴ InvalidRefreshTokenException.class
  ㄴ LoginFailureException.class
  ㄴ MemberDoNotUseOtherThingException.class
  ㄴ MemberEmailAlreadyExistsException.class
  ㄴ MemberNicknameAlreadyExistsException.class
  ㄴ MemberNotFoundException.class
  ㄴ MemberNotWriterException.class
  ㄴ ProcessFailureException.class
  ㄴ advise
      ㄴ ExceptionAdvice.class
security
  ㄴ accessDeniedHandler
      ㄴ CustomAccessDeniedHandler.class
  ㄴ authenticationEntryPoint
      ㄴ CustomAuthenticationEntryPoint.class
  ㄴ jwt
      ㄴ JwtAuthenticationFilter.class
      ㄴ JwtTokenProvider.class
  ㄴ member
      ㄴ MemberDatails.class
      ㄴ MemberDetailsService.class
  ㄴ util
      ㄴ SecurityUtil.class
  ㄴ SecurityConfig.class
web
  ㄴ controller
      ㄴ board
          ㄴ BoardController.class
      ㄴ exception
          ㄴ ExceptionController.class
      ㄴ member
          ㄴ MemberController.class
          ㄴ SignController.class
      ㄴ profile
          ㄴ ProfileController.class
          
```
