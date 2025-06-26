package com.dci.ypper.controller;

import com.dci.ypper.dto.UserRegisterRequest;
import com.dci.ypper.model.User;
import com.dci.ypper.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@Valid @RequestBody UserRegisterRequest user) {
        userService.register(user);
        Map<String, String> response = Map.of("name", user.getName(),
                                              "email", user.getEmail());
        return ResponseEntity.ok(response);
    }

//    @CrossOrigin(origins = "http://localhost:3000/")
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody User user) {
        String token = userService.verify(user);
        Map<String, String> response = Map.of("token", token,
                                              "username", user.getName());
        return ResponseEntity.ok(response);
    }
}
