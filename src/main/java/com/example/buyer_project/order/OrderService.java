package com.example.buyer_project.order;

import com.example.buyer_project.product.Product;
import com.example.buyer_project.product.ProductRepository;
import com.example.buyer_project.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    // 구매 목록보기
    public List<OrderResponse.ListDTO> getOrderList(Integer userId) {
        List<Order> orderList = orderRepository.getOrderList(userId);
        return orderList.stream().map(order ->
                new OrderResponse.ListDTO(order)).toList();
    }

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

}
