package com.example.buyer_project.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepository {

    private final EntityManager em;

    // 로그인하기
    public User findByUserIdAndPassword(UserRequest.LoginDTO reqDTO) {

        String q = """
                select u from User u where u.userId=:userId and u.password=:password             
                """;

        Query query = em.createQuery(q, User.class);

        query.setParameter("userId", reqDTO.getUserId());
        query.setParameter("password", reqDTO.getPassword());

        User user = (User) query.getSingleResult();

        return user;
    }
    
}
