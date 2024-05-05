package com.example.buyer_project.order;

import lombok.Data;

public class OrderResponse {

    // 구매 목록보기
    @Data
    public static class ListDTO {
        private Integer id;
        private String name;
        private Integer price;
        private Integer buyQty;

        public ListDTO(Order order) {
            this.id = order.getId();
            this.name = order.getProduct().getName();
            this.price = order.getProduct().getPrice();
            this.buyQty = order.getBuyQty();
        }
    }

}
