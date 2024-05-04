package com.example.buyer_project.user;

import lombok.Data;

public class UserRequest {

    // 회원가입하기
    @Data
    public static class JoinDTO {
        private String userId;
        private String password;
        private String name;
        private String phone;
        private String address;

        public User toEntity() {
            return User.builder()
                    .userId(userId)
                    .password(password)
                    .name(name)
                    .phone(phone)
                    .address(address)
                    .build();
        }
    }

}
