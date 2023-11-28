package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Borrowed;
import com.example.demo.model.entity.BorrowedItem;

@Repository
public interface BorrowedItemRepository extends JpaRepository<BorrowedItem, Integer>{
    // Optional<BorrowedItem> findByName(String name);

    //  Boolean existsByName(String name);

    @Query("select c from BorrowedItem c")
    List<BorrowedItem> getAllBorrowedItems();

    Optional<BorrowedItem> findById(int id);
}
