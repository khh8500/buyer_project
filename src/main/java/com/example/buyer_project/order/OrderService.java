package com.example.buyer_project.order;

import com.example.buyer_project.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    // 구매 목록보기
    public List<OrderResponse.ListDTO> getOrderList(Integer userId) {
        List<Order> orderList = orderRepository.getOrderList(userId);
        return orderList.stream().map(order ->
                new OrderResponse.ListDTO(order)).toList();
    }

    // 구매하기
    @Transactional
    public void saveOrder(OrderRequest.SaveDTO reqDTO, User sessionUser) {
        orderRepository.saveOrder(reqDTO, sessionUser.getId());
    }

}
