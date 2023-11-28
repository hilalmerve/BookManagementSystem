package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.Book;
import com.example.demo.model.entity.Borrowed;
import com.example.demo.model.entity.BorrowedItem;
import com.example.demo.model.entity.User;
import com.example.demo.model.request.BorrowedAddRequest;
import com.example.demo.model.request.BorrowedItemAddRequest;
import com.example.demo.model.request.BorrowedRequest;
import com.example.demo.model.response.BookResponse;
import com.example.demo.model.response.BorrowedItemResponse;
import com.example.demo.model.response.BorrowedResponse;
import com.example.demo.model.response.UserResponse;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.BorrowedRepository;
import com.example.demo.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class BorrowedService {
    @Autowired
    private BorrowedRepository borrowedRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private BorrowedItemService borrowedItemService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    ModelMapper modelMapper;

    @Transactional
    public BorrowedResponse createBorrowed(BorrowedRequest borrowedRequest) {
        //UserResponse userResponse = userService.getUserById(borrowedRequest.getUser_id());
        User user = userRepository.findById(borrowedRequest.getUser_id())
        .orElseThrow(() -> new RuntimeException("User Not Found with id: " + borrowedRequest.getUser_id()));
        List<Integer> borrowedItemIds = borrowedRequest.getBorrowedItemsId();
        List<BorrowedItem> borrowedItems = borrowedItemService.findAll(borrowedItemIds);
        
        //User user = modelMapper.map(userResponse, User.class);
        Borrowed borrowed = modelMapper.map(borrowedRequest, Borrowed.class);
        borrowed.setUser(user);
        borrowed.setBorrowedItems(borrowedItems);
        Borrowed savedBorrowed = borrowedRepository.save(borrowed);

        BorrowedResponse borrowedResponse = modelMapper.map(savedBorrowed, BorrowedResponse.class);
        List<BorrowedItemResponse> borrowedItemResponse = savedBorrowed.getBorrowedItems().stream()
        .map(saved -> modelMapper.map(saved, BorrowedItemResponse.class))
        .collect(Collectors.toList());
        borrowedResponse.setUser_id(savedBorrowed.getUser().getId());
        borrowedResponse.setBorrowedItemResponses(borrowedItemResponse);
        

        return borrowedResponse;
    }

    @Transactional
    public String addBookToBorrowed(BorrowedItemAddRequest request) {
        //BookResponse bookResponse = bookService.getBookById(request.getBookId());
        //Optional<Book> book = bookRepository.findById(request.getBookId());
        Book book = bookRepository.findById(request.getBookId())
        .orElseThrow(() -> new RuntimeException("Book Not Found with id: " + request.getBookId()));
        //Optional<Borrowed> borrowed = borrowedRepository.findById(request.getBorrowedId());
        Borrowed borrowed = borrowedRepository.findById(request.getBorrowedId())
        .orElseThrow(() -> new RuntimeException("Borrowed Not Found with id: " + request.getBorrowedId()));
        //BorrowedItem borrowedItem1 = modelMapper.map(request, BorrowedItem.class);
        
        //Book book = modelMapper.map(bookResponse, Book.class);
        BorrowedItem borrowedItem1 = BorrowedItem.builder().borrowed(borrowed)
        .book(book)
        .build();

        //borrowedItem1.setBook(book.get());
        //borrowedItem1.setBorrowed(borrowed.get());
        //BorrowedItem borrowedItem = modelMapper.map(borrowed, BorrowedItem.class);
        //borrowedItemService.addBorrowedItem(borrowed.get().);
        borrowedItemService.addBorrowedItem(borrowedItem1);

        //BorrowedResponse borrowedResponse = modelMapper.map(borrowedItem1, BorrowedResponse.class);
        return "Recorded";
    }

    @Transactional
    public BorrowedResponse getBorrowedBooks(int userId) {
        User user = userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("User Not Found " + userId));

        Borrowed borrowed = borrowedRepository.findByUserId(user.getId());
        //.orElseThrow(() -> new RuntimeException("Borrowed Not Found: "));

        //borrowed.getUser().setId(user.getId());;

        BorrowedResponse borrowedResponse = modelMapper.map(borrowed, BorrowedResponse.class);
        //BorrowedResponse borrowedResponse = modelMapper.map(savedBorrowed, BorrowedResponse.class);
        List<BorrowedItemResponse> borrowedItemResponse = borrowed.getBorrowedItems().stream()
        .map(saved -> modelMapper.map(saved, BorrowedItemResponse.class))
        .collect(Collectors.toList());
        borrowedResponse.setUser_id(borrowed.getUser().getId());
        borrowedResponse.setBorrowedItemResponses(borrowedItemResponse);
        return borrowedResponse;
    }
    
}
