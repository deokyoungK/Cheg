package com.likelion.cheg.web.controller;

import com.likelion.cheg.domain.order.Order;
import com.likelion.cheg.domain.product.Product;
import com.likelion.cheg.domain.user.User;
import com.likelion.cheg.domain.user.UserRepository;
import com.likelion.cheg.service.OrderService;
import com.likelion.cheg.service.ProductService;
import com.likelion.cheg.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.*;
@RequiredArgsConstructor
@Controller
public class AdminController {

    private final ProductService productService;
    private final UserRepository userRepository;
    private final OrderService orderService;

    @GetMapping("/admin")
    public String admin(Model model){
        List<User> userList = userRepository.findAll();
        model.addAttribute("userList",userList);
        return "admin/admin";
    }

    @GetMapping("/admin/productList")
    public String productList(Model model){
        List<Product> productList = productService.loadAllProducts();
        model.addAttribute("productList",productList);
        return "admin/productList";
    }

    @GetMapping("/admin/orderList")
    public String orderList(Model model){
        List<Order> orderList = orderService.loadAll();
        model.addAttribute("orderList",orderList);
        return "admin/orderList";
    }

    @GetMapping("/admin/addProduct")
    public String addProduct(){

        return "admin/addProduct";
    }

}
