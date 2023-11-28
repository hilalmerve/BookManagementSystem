package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.Publisher;
import com.example.demo.model.request.PublisherRequest;
import com.example.demo.model.response.PublisherResponse;
import com.example.demo.repository.PublisherRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Service
public class PublisherService {
    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private ModelMapper modelMapper;

    public int addPublisher(PublisherRequest publisherRequest) {
        Publisher publisher = modelMapper.map(publisherRequest, Publisher.class);
        publisherRepository.save(publisher);
        return publisher.getId();
    }

    public List<PublisherResponse> getAllPublishers(){
        List<Publisher> publisherList = publisherRepository.getAllPublishers();// findall ile aynı. bu entity listesi dönüyor.
        List<PublisherResponse> resultList = publisherList.stream()
        .map(publisher -> modelMapper.map(publisher, PublisherResponse.class))
        .collect(Collectors.toList());
        return resultList;
    }

    public PublisherResponse getPublisherById(int id) {
        Optional<Publisher> optionalPublisher = publisherRepository.findById(id);
        
        // Eğer değer varsa, Category nesnesini döndürür.
        if (optionalPublisher.isPresent()) {
            PublisherResponse publisherResponse = modelMapper.map(optionalPublisher, PublisherResponse.class);
            return publisherResponse;
        } else {
            // Değer bulunamadıysa, gerekli işlemleri yapabilir veya null dönebilirsiniz.
            throw new RuntimeException("Error: Publisher is not found.");
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

    public String deletePublisherById(int id) {
        Publisher publisher = publisherRepository.findById(id).orElseThrow();
        publisherRepository.deleteById(publisher.getId());;
        return "Publisher is deleted : ";
    }
}
