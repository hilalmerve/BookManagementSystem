package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.Book;
import com.example.demo.model.entity.BorrowedItem;
import com.example.demo.model.request.BookRequest;
import com.example.demo.model.request.BorrowedItemRequest;
import com.example.demo.model.response.BookResponse;
import com.example.demo.model.response.BorrowedItemResponse;
import com.example.demo.repository.BorrowedItemRepository;

@Service
public class BorrowedItemService {
    
    @Autowired
    private BorrowedItemRepository borrowedItemRepository;

    @Autowired
    private ModelMapper modelMapper;


    public int addBorrowedItem(BorrowedItem borrowedItem) {

        borrowedItemRepository.save(borrowedItem);
        BorrowedItemResponse borrowedItemResponse = modelMapper.map(borrowedItem, BorrowedItemResponse.class);
        
        //Optional<BorrowedItem> optionalBorrowedItem = borrowedItemRepository.findByName(borrowedItemRequest.getName());
        return borrowedItemResponse.getId();
    }
    
    public BorrowedItemResponse getBorrowedItemById(int id) {
        Optional<BorrowedItem> optionalBorrowedItem = borrowedItemRepository.findById(id);
        
        // Eğer değer varsa, Category nesnesini döndürür.
        if (optionalBorrowedItem.isPresent()) {
            BorrowedItemResponse bookResponse = modelMapper.map(optionalBorrowedItem, BorrowedItemResponse.class);
            return bookResponse;
        } else {
            // Değer bulunamadıysa, gerekli işlemleri yapabilir veya null dönebiliriz.
            throw new RuntimeException("Error: BorrowedItem is not found.");
        }
    }

    public List<BorrowedItem> findAll(List<Integer> basketItemIds) {
        return borrowedItemRepository.findAllById(basketItemIds);

    }
    
}
