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

import com.example.demo.model.request.CategoryRequest;
import com.example.demo.model.response.CategoryResponse;
import com.example.demo.service.CategoryService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;



@RestController
@RequestMapping("/category")
@NoArgsConstructor
@AllArgsConstructor
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping(value = "/add")
    public ResponseEntity<?> addCategory(@RequestBody CategoryRequest categoryRequest) {
        
        int recordedId = categoryService.addCategory(categoryRequest);
        return ResponseEntity.ok("Category registered successfully! Category id: " + recordedId);
    }

    @GetMapping("/getall")
    public List<CategoryResponse> getAllCategories() {
        List<CategoryResponse> authorList = categoryService.getAllCategories();
        return authorList;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable("id") int categoryId) {
        CategoryResponse categoryResponse = categoryService.getCategoryById(categoryId);
        return ResponseEntity.ok(categoryResponse);
        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable int id){
        return ResponseEntity.ok(categoryService.deleteCategoryById(id) + id);
    }
}
