package com.likelion.cheg.web.controller;

import com.likelion.cheg.domain.category.Category;
import com.likelion.cheg.domain.category.CategoryRepository;
import com.likelion.cheg.domain.order.Order;
import com.likelion.cheg.domain.order.OrderRepository;
import com.likelion.cheg.domain.product.Product;
import com.likelion.cheg.domain.product.ProductRepository;
import com.likelion.cheg.domain.user.User;
import com.likelion.cheg.domain.user.UserRepository;
import com.likelion.cheg.service.CategoryService;
import com.likelion.cheg.service.OrderService;
import com.likelion.cheg.service.ProductService;
import com.likelion.cheg.service.UserService;
import com.likelion.cheg.web.dto.category.CategoryResponseDto;
import com.likelion.cheg.web.dto.order.OrderResponseDto;
import com.likelion.cheg.web.dto.product.ProductResponseDto;
import com.likelion.cheg.web.dto.product.ProductUploadDto;
import com.likelion.cheg.web.dto.user.UserResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@RequiredArgsConstructor
@Controller
@Slf4j
public class AdminController {

    private final ProductService productService;
    private final ProductRepository productRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final OrderService orderService;
    private final OrderRepository orderRepository;
    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;


    @GetMapping("/admin/users")
    public String userList(Model model){
        List<User> userList = userRepository.findAll();
        List<UserResponseDto> userListDto = userService.makeResponseDto(userList);
        model.addAttribute("userListDto",userListDto);
        return "admin/users";
    }

    /**
     카테고리 관련
     **/
    @GetMapping("/admin/addCategory")
    public String addCategory(Model model){
        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryResponseDto> categoryListDto = categoryService.makeResponseDto(categoryList);
        model.addAttribute("categoryListDto",categoryListDto);
        return "admin/createCategory";
    }

    @PostMapping("/admin/addCategory")
    public String addCategory(String categoryName){
        categoryService.saveCategory(categoryName);
        return "redirect:/admin/addCategory";
    }

    /**
     상품 관련
     **/
    @GetMapping("/admin/products")
    public String productList(Model model){
        List<Product> productList = productService.loadProductsDESC();
        List<ProductResponseDto> productListDto = productService.makeResponseDto(productList);
        model.addAttribute("productListDto",productListDto);
        return "admin/products";
    }

    @GetMapping("/admin/addProduct")
    public String createProduct(Model model){
        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryResponseDto> categoryListDto = categoryService.makeResponseDto(categoryList);
        model.addAttribute("categoryListDto",categoryListDto);
        return "admin/createProduct";
    }

    @PostMapping("/admin/addProduct")
    public String uploadProduct(@Validated ProductUploadDto productUploadDto){
        //상품등록
        productService.addProduct(productUploadDto);
        return "redirect:/admin/products";

    }

    /**
     주문 관련
     **/
    @GetMapping("/admin/orders")
    public String orderList(Model model){
        List<Order> orderList = orderRepository.findAll();
        List<OrderResponseDto> orderListDto = orderService.makeResponseDto(orderList);
        model.addAttribute("orderListDto",orderListDto);
        return "admin/orders";
    }


    /**
     검색 관련
     **/
    @GetMapping("/admin/search/user")
    public String searchUser(@RequestParam(value="keyword") String keyword, Model model){
        List<User> userList = userRepository.searchByKeyword(keyword);
        List<UserResponseDto> userListDto = userService.makeResponseDto(userList);
        model.addAttribute("userListDto",userListDto);
        return "admin/users";
    }

    @GetMapping("/admin/search/product")
    public String searchProduct(@RequestParam(value="keyword") String keyword, Model model){
        List<Product> productList = productRepository.searchByKeyword(keyword);
        List<ProductResponseDto> productListDto = productService.makeResponseDto(productList);
        model.addAttribute("productListDto",productListDto);
        return "admin/products";
    }

    @GetMapping("/admin/search/order")
    public String searchOrder(@RequestParam(value="keyword") String keyword, Model model){
        List<Order> orderList = orderRepository.searchByKeyword(keyword);
        List<OrderResponseDto> orderListDto = orderService.makeResponseDto(orderList);
        model.addAttribute("orderListDto",orderListDto);
        return "admin/orders";
    }
}
