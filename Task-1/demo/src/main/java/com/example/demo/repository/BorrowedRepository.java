package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Book;
import com.example.demo.model.entity.Borrowed;

@Repository
public interface BorrowedRepository extends JpaRepository<Borrowed, Integer>{
    // Optional<Borrowed> findByName(String name);

    //  Boolean existsByName(String name);

    @Query("select c from Borrowed c")
    List<Borrowed> getAllBorroweds();

    Optional<Borrowed> findById(int id);

    Borrowed findByUserId(int id);
}
