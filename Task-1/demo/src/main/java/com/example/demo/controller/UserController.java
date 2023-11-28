package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.request.UserRequest;
import com.example.demo.service.UserService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/user")
@NoArgsConstructor
@AllArgsConstructor
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping(value = "/add")
    public ResponseEntity<?> addUser(@RequestBody UserRequest userRequest) {
        
        int recordedId = userService.addUser(userRequest);
        return ResponseEntity.ok("User registered successfully! User id: " + recordedId);
    }
}
