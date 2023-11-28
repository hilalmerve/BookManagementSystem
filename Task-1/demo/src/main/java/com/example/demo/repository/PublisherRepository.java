package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer>{
    Optional<Publisher> findByName(String name);

     Boolean existsByName(String name);

    @Query("select c from Publisher c")
    List<Publisher> getAllPublishers();

    Optional<Publisher> findById(int id);
}
