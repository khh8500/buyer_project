# final project - 상품 구매 사이트


## 프로그램 기능

+ 상품 구매하기


## 기술스택

+ JDK 21
+ Springboot 3.2.3
+ HTML
+ JAVAScript
+ Bootstrap


## 의존성

+ Lombok
+ Spring Boot DevTools
+ Spring WEB
+ Spring Data JPA
+ MySQL
+ Mustache


## 구매하기

1. 하나의 @Transactional에서 save와 update가 실행되도록 한다.
2. 상품 재고를 확인하여, 상품이 없거나 재고가 부족한 경우에는 해당 로직이 실행되지 않도록 한다.


+ Service

```
  // 구매하기
  @Transactional
  public boolean saveOrder(OrderRequest.SaveDTO reqDTO, User sessionUser) {
      // 상품 재고 확인
      Product product = productRepository.findById(reqDTO.getProductId());
      if (product == null || product.getQty() < reqDTO.getBuyQty()) {
          // 상품이 없거나 재고가 충분하지 않은 경우
          return false;
      }

      // 구매 내역 저장
      orderRepository.saveOrder(reqDTO, sessionUser.getId());
      // 상품 재고 업데이트
      orderRepository.updateQty(reqDTO.getBuyQty(), reqDTO.getProductId());

      return true;
  }
```

+ View

1. 상품의 재고 정보를 확인하고 구매 수량을 입력할 수 있다.
2. 구매 수량을 입력한 후 구매하기 버튼을 클릭하면, 상품의 재고를 확인하여 구매가 진행된다.
3. 재고가 부족한 경우 알림이 뜨며, 다시 구매 수량을 입력할 수 있다.

```
  <input type="hidden" name="productId" value="{{product.id}}">

  <div class="mb-3 mt-3">
      상 품 명 : <input name="name" type="text" class="form-control" value="{{product.name}}" readonly>
  </div>
  <div class="mb-3 mt-3">
      상품가격 : <input name="price" type="text" class="form-control" value="{{product.price}}" readonly>
  </div>
  <div class="mb-3 mt-3">
      <div>구매수량 : <span style="color: grey;">(재고 : {{product.qty}})</span></div>
      <input name="buyQty" type="number" class="form-control" placeholder="수량을 입력해주세요" required>
  </div>

  <div class="d-flex justify-content-center">
      <button id="qtyCheck" type="submit" class="btn btn-primary mt-3">구매하기
      </button>
  </div>
  <script>
      // 페이지가 로드되면 바로 실행될 함수
      window.onload = function () {
          var productQty = {{product.qty}};
          var qtyCheckButton = document.getElementById("qtyCheck");

          if (productQty <= 0) {
              alert("죄송합니다. 해당 상품의 재고가 없습니다.");
              qtyCheckButton.disabled = true; // 재고없으면 버튼 비활성화
          }
      }

      // 구매하기 버튼 클릭 시 실행되는 함수
      function qtyCheckButtonHandler() {
          var productQty = {{product.qty}};
          var buyQty = document.getElementsByName("buyQty")[0].value; // 입력된 구매 수량 가져오기

          if (buyQty <= 0) {
              alert("구매 수량을 올바르게 입력해주세요.");
              return;
          }

          if (buyQty > productQty) {
              alert("죄송합니다. 해당 상품의 재고가 부족합니다.");
              document.getElementsByName("buyQty")[0].value = ""; // 입력된 수량 초기화
              return;
          }

          // 수량이 충분하면 폼을 서버로 제출
          document.querySelector("form").submit();
      }

      // 구매하기 버튼에 클릭 이벤트 핸들러 등록
      var qtyCheckButton = document.getElementById("qtyCheck");
      qtyCheckButton.addEventListener("click", qtyCheckButtonHandler);

  </script>
```
