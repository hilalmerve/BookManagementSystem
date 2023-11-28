package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.request.BookRequest;
import com.example.demo.model.response.BookResponse;
import com.example.demo.service.BookService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@RestController
@RequestMapping("/book")
@NoArgsConstructor
@AllArgsConstructor
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping(value = "/add")
    public ResponseEntity<?> addBook(@RequestBody BookRequest bookRequest) {
        
        int recordedId = bookService.addBook(bookRequest);
        return ResponseEntity.ok("Book registered successfully! Book id: " + recordedId);
    }

    @GetMapping("/getall")
    public List<BookResponse> getAllBooks() {
        List<BookResponse> bookList = bookService.getAllBooks();
        return bookList;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable("id") int bookId) {
        BookResponse bookResponse = bookService.getBookById(bookId);
        return ResponseEntity.ok(bookResponse);
        
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<BookResponse> updateBook(@RequestBody BookRequest bookRequest) {
        return ResponseEntity.ok(bookService.updateBook(bookRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable int id){
        return ResponseEntity.ok(bookService.deleteBookById(id) + id);
    }
}
