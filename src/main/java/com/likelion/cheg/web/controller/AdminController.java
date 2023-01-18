package com.likelion.cheg.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class AdminController {

    @GetMapping("/admin")
    public String admin(){

        return "admin/admin";
    }

    @GetMapping("/admin/userList")
    public String userList(){

        return "admin/userList";
    }

    @GetMapping("/admin/productList")
    public String productList(){

        return "admin/productList";
    }

    @GetMapping("/admin/orderList")
    public String orderList(){

        return "admin/orderList";
    }

    @GetMapping("/admin/addProduct")
    public String addProduct(){

        return "admin/addProduct";
    }

}
