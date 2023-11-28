package com.example.demo.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookResponse {
    private int id;
    private String name;
    // private Author author;
    // private Category category;
    // private Publisher publisher;
    private String authorName;
    private String categoryName;
    private String publisherName;
    private int quantity;
}
