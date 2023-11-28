package com.example.demo.model.request;

import com.example.demo.model.entity.Author;
import com.example.demo.model.entity.Category;
import com.example.demo.model.entity.Publisher;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookRequest {
    private int id;
    private String name;
    private Author author;
    private Category category;
    private Publisher publisher;
    private int quantity;
}
