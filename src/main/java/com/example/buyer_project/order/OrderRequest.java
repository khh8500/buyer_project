package com.example.buyer_project.order;

import lombok.Data;

public class OrderRequest {

    // 구매하기
    @Data
    public static class SaveDTO {
        private Integer productId;
        private Integer buyQty;
    }

}
