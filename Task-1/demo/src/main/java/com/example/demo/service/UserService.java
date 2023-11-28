package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.Book;
import com.example.demo.model.entity.User;
import com.example.demo.model.request.BookRequest;
import com.example.demo.model.request.UserRequest;
import com.example.demo.model.response.BookResponse;
import com.example.demo.model.response.UserResponse;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public int addUser(UserRequest userRequest) {
        if (userRepository.existsByName(userRequest.getName())) {
            throw new RuntimeException("Error: Username is already in use!");
          }
      
        User user = modelMapper.map(userRequest, User.class);
        user = userRepository.save(user);
        UserResponse result = modelMapper.map(user, UserResponse.class);
        return result.getId();
    }

    public List<UserResponse> getAllUsers(){
        List<User> userList = userRepository.getAllUsers();// findall ile aynı. bu entity listesi dönüyor.
        List<UserResponse> resultList = userList.stream()
        .map(book -> modelMapper.map(book, UserResponse.class))
        .collect(Collectors.toList());
        return resultList;
    }

    public UserResponse getUserById(int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        
        // Eğer değer varsa, Category nesnesini döndürür.
        if (optionalUser.isPresent()) {
            UserResponse userResponse = modelMapper.map(optionalUser, UserResponse.class);
            return userResponse;
        } else {
            // Değer bulunamadıysa, gerekli işlemleri yapabilir veya null dönebiliriz.
            throw new RuntimeException("Error: User is not found.");
        }
    }

    public UserResponse updateUser(UserRequest userRequest){
        var optionalUser = userRepository.findById(userRequest.getId());
        if (optionalUser.isPresent()){
            var user = optionalUser.get();
            //book.setQuantity(userRequest.getQuantity());
            user = userRepository.save(user);
            UserResponse bookResponse = modelMapper.map(user, UserResponse.class);
            return bookResponse;
        }
        throw new RuntimeException("User not found for id: " + userRequest.getId());

    }

    public String deleteBookById(int id) {
        User user = userRepository.findById(id).orElseThrow();
        userRepository.deleteById(user.getId());
        return "User is deleted : ";
    }
}
