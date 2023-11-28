package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.request.BorrowedAddRequest;
import com.example.demo.model.request.BorrowedItemAddRequest;
import com.example.demo.model.request.BorrowedRequest;
import com.example.demo.model.response.BorrowedResponse;
import com.example.demo.service.BorrowedService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/borrowed")
@NoArgsConstructor
@AllArgsConstructor
public class BorrowedController {
    
    @Autowired
    private BorrowedService borrowedService;

    @PostMapping("/add")
    ResponseEntity<BorrowedResponse> createBorrowed(@RequestBody BorrowedRequest request) {
        BorrowedResponse borrowed = borrowedService.createBorrowed(request);
        return ResponseEntity.ok(borrowed);
    }

    @PostMapping("/addBorrowedItem")
    public ResponseEntity<String> addBookToBorrowed(@RequestBody BorrowedItemAddRequest request) {
        return ResponseEntity.ok(borrowedService.addBookToBorrowed(request));
    }

    @GetMapping("/getBorrowed")
    public ResponseEntity<BorrowedResponse> getBorrowedBooks(@RequestParam("userId") int userId) {
        BorrowedResponse borrowed = borrowedService.getBorrowedBooks(userId);
        return ResponseEntity.ok(borrowed);
    }
}
