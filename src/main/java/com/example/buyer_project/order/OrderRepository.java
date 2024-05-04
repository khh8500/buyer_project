package com.example.buyer_project.order;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class OrderRepository {

    private final EntityManager em;

    // 구매 목록보기
    public List<Order> getOrderList(Integer userId) {

        String q = """
                select o from Order o join fetch o.user u where u.id = :userId
                order by o.createdAt desc
                """;

        Query query = em.createQuery(q, Order.class);

        query.setParameter("userId", userId);

        return query.getResultList();
    }

    // 구매하기
    public void saveOrder(OrderRequest.SaveDTO reqDTO, int sessionUserId) {

        String q = """
                insert into order_tb (user_id, product_id, buy_qty, created_at) values (?,?,?,now())
                """;
        
        Query query = em.createNativeQuery(q);

        query.setParameter(1, sessionUserId);
        query.setParameter(2, reqDTO.getProductId());
        query.setParameter(3, reqDTO.getBuyQty());

        query.executeUpdate();
    }

}
