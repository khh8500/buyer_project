package com.example.buyer_project.order;

import lombok.Data;

public class OrderResponse {

    @Data
    public static class SaveDTO {
        // 유저
        private Integer userId;
        private String name;
        private String phone;
        private String address;

        // 구매 상품
        private Integer productId;
        private String pName;
        private Integer price;

        // 구매 수량
        private Integer buyQty;

        public SaveDTO(Order order) {
            this.userId = order.getUser().getId();
            this.name = order.getUser().getName();
            this.address = order.getUser().getAddress();
            this.phone = order.getUser().getPhone();
            this.productId = order.getProduct().getId();
            this.pName = order.getProduct().getName();
            this.price = order.getProduct().getPrice();
            this.buyQty = order.getBuyQty();
        }
    }

}
