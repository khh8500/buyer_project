package com.example.buyer_project.order;

import com.example.buyer_project.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public void saveOrder(OrderRequest.SaveDTO reqDTO, User sessionUser) {
        orderRepository.saveOrder(reqDTO, sessionUser.getId());
    }

}
