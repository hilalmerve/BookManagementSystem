package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.Book;
import com.example.demo.model.request.BookRequest;
import com.example.demo.model.response.BookResponse;
import com.example.demo.repository.BookRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper modelMapper;

    public int addBook(BookRequest bookRequest) {
        Book book = modelMapper.map(bookRequest, Book.class);
        // book.setAuthor(bookRequest.getAuthor());
        // book.setCategory(bookRequest.getCategory());
        // book.setPublisher(bookRequest.getPublisher());
        //bookRepository.save(book);
        Optional<Book> optionalBook = bookRepository.findByName(bookRequest.getName());
        int quantity;
        if (optionalBook.isPresent()) {
            // quantity = bookRequest.getQuantity() + book.getQuantity();
            // book.setQuantity(quantity);
            throw new RuntimeException("Error: Book is already found. Book's Id:  " + optionalBook.get().getId() + "   ");
        } else {
            quantity = bookRequest.getQuantity();
            book.setQuantity(quantity);
            bookRepository.save(book);
        }
        
        return book.getId();
    }

    public List<BookResponse> getAllBooks(){
        List<Book> bookList = bookRepository.getAllBooks();// findall ile aynı. bu entity listesi dönüyor.
        List<BookResponse> resultList = bookList.stream()
        .map(book -> modelMapper.map(book, BookResponse.class))
        .collect(Collectors.toList());
        return resultList;
    }

    public BookResponse getBookById(int id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        
        // Eğer değer varsa, Category nesnesini döndürür.
        if (optionalBook.isPresent()) {
            BookResponse bookResponse = modelMapper.map(optionalBook, BookResponse.class);
            return bookResponse;
        } else {
            // Değer bulunamadıysa, gerekli işlemleri yapabilir veya null dönebiliriz.
            throw new RuntimeException("Error: Book is not found.");
        }
    }

    public BookResponse updateBook(BookRequest bookRequest){
        var optionalBook = bookRepository.findById(bookRequest.getId());
        if (optionalBook.isPresent()){
            var book = optionalBook.get();
            book.setQuantity(bookRequest.getQuantity() + book.getQuantity());
            book = bookRepository.save(book);
            BookResponse bookResponse = modelMapper.map(book, BookResponse.class);
            return bookResponse;
        }
        throw new RuntimeException("Book not found for id: " + bookRequest.getId());

    }

    public String deleteBookById(int id) {
        Book book = bookRepository.findById(id).orElseThrow();
        bookRepository.deleteById(book.getId());
        return "Book is deleted : ";
    }


}
