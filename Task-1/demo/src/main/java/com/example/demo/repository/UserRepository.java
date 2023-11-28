package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Author;
import com.example.demo.model.entity.Book;
import com.example.demo.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByName(String name);

     Boolean existsByName(String name);

    @Query("select c from User c")
    List<User> getAllUsers();

    Optional<User> findById(int id);
}


