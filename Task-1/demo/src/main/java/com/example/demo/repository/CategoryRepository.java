package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
    Optional<Category> findByName(String name);

     Boolean existsByName(String name);

    @Query("select c from Category c")
    List<Category> getAllCategories();

    Optional<Category> findById(int id);
}
