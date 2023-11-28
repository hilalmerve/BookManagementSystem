package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.Author;
import com.example.demo.model.request.AuthorRequest;
import com.example.demo.model.response.AuthorResponse;
import com.example.demo.repository.AuthorRepository;


@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private ModelMapper modelMapper;

    public int addAuthor(AuthorRequest authorRequest) {
        Author author = modelMapper.map(authorRequest, Author.class);
        authorRepository.save(author);
        return author.getId();
    }

    public List<AuthorResponse> getAllAuthors(){
        List<Author> authorList = authorRepository.getAllAuthors();// findall ile aynı. bu entity listesi dönüyor.
        List<AuthorResponse> resultList = authorList.stream()
        .map(author -> modelMapper.map(author, AuthorResponse.class))
        .collect(Collectors.toList());
        return resultList;
    }

    public AuthorResponse getAuthorById(int id) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        
        // Eğer değer varsa, Category nesnesini döndürür.
        if (optionalAuthor.isPresent()) {
            AuthorResponse authorResponse = modelMapper.map(optionalAuthor, AuthorResponse.class);
            return authorResponse;
        } else {
            // Değer bulunamadıysa, gerekli işlemleri yapabilir veya null dönebilirsiniz.
            throw new RuntimeException("Error: Author is not found.");
        }
    }

    // public User updateProfile(User user, Long id) {
    //     User persistedUser = getUserById(id);
	// 	if(persistedUser == null){
	// 		throw new UserNotFoundException("User "+user.getId()+" doesn't exist");
	// 	}
	// 	persistedUser.setUsername(user.getUsername());
	// 	persistedUser.setEmail(user.getEmail());
	// 	//persistedUser.setRole(user.getRole());
	// 	return userRepository.save(persistedUser);
    // }

    public String deleteAuthorById(int id) {
        Author author = authorRepository.findById(id).orElseThrow();
        authorRepository.deleteById(author.getId());;
        return "Author is deleted : ";
    }
}
