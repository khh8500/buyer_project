package com.example.buyer_project.product;

import lombok.Data;

public class ProductResponse {

    // 상품 목록보기
    @Data
    public static class ListDTO {
        private String name;
        private Integer price;
        private Integer qty;
        private Integer id;

        public ListDTO(Product product) {
            this.name = product.getName();
            this.price = product.getPrice();
            this.qty = product.getQty();
            this.id = product.getId();
        }
    }

}
