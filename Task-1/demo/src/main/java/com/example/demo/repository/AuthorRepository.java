package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer>{
    Optional<Author> findByName(String name);

     Boolean existsByName(String name);

    @Query("select c from Author c")
    List<Author> getAllAuthors();

    Optional<Author> findById(int id);
}


