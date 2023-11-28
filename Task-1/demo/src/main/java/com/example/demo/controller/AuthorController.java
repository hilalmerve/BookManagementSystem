package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.request.AuthorRequest;
import com.example.demo.model.response.AuthorResponse;
import com.example.demo.service.AuthorService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@RestController
@RequestMapping("/author")
@NoArgsConstructor
@AllArgsConstructor
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping(value = "/add")
    public ResponseEntity<?> addAuthor(@RequestBody AuthorRequest authorRequest) {
        
        int recordedId = authorService.addAuthor(authorRequest);
        return ResponseEntity.ok("Author registered successfully! Author id: " + recordedId);
    }

    @GetMapping("/getall")
    public List<AuthorResponse> getAllAuthors() {
        List<AuthorResponse> authorList = authorService.getAllAuthors();
        return authorList;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AuthorResponse> profileUser(@PathVariable("id") int authorId) {
        AuthorResponse authorResponse = authorService.getAuthorById(authorId);
        return ResponseEntity.ok(authorResponse);
        
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAuthorById(@PathVariable int id){
        return ResponseEntity.ok(authorService.deleteAuthorById(id) + id);
    }
}
