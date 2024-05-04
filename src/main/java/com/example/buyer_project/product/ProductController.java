package com.example.buyer_project.product;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ProductController {

    private final ProductService productService;

    // 상품 목록보기
    @GetMapping("/product")
    public String list(HttpServletRequest request) {
        List<ProductResponse.ListDTO> productList = productService.findAll();
        request.setAttribute("productList", productList);

        return "product/list";
    }

    // 메인 목록보기
    @GetMapping("/")
    public String main(HttpServletRequest request) {
        List<ProductResponse.ListDTO> productList = productService.findAll();
        request.setAttribute("productList", productList);

        return "/index";
    }

}
