package com.example.buyer_project.order;

import com.example.buyer_project.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class OrderController {

    private final OrderService orderService;
    private final HttpSession session;

    // 구매 목록보기
    @GetMapping("/order/list")
    public String OrderList(HttpServletRequest request){

        User sessionUser = (User) session.getAttribute("sessionUser");

        List<OrderResponse.ListDTO> orderList = orderService.getOrderList(sessionUser.getId());
        request.setAttribute("orderList", orderList);

        return "order/list";
    }

    // 구매하기  //save
    @PostMapping("/order")
    public String saveOrder(OrderRequest.SaveDTO reqDTO, HttpSession session) {
        // 세션에서 사용자 정보 가져오기
        User sessionUser = (User) session.getAttribute("sessionUser");

        orderService.saveOrder(reqDTO, sessionUser);

        return "redirect:/order/list";
    }

}
