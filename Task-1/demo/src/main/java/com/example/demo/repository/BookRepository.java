package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{
    Optional<Book> findByName(String name);

     Boolean existsByName(String name);

    @Query("select c from Book c")
    List<Book> getAllBooks();

    Optional<Book> findById(int id);
}
