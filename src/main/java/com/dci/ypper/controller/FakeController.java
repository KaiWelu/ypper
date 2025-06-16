package com.dci.ypper.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FakeController {
    @GetMapping("/fake")
    public String hello(HttpServletRequest request) {
        return "Hello!";
    }

}
