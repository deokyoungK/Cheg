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
}
