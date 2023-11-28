package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.request.PublisherRequest;
import com.example.demo.model.response.PublisherResponse;
import com.example.demo.service.PublisherService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;



@RestController
@RequestMapping("/publisher")
@NoArgsConstructor
@AllArgsConstructor
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @PostMapping(value = "/add")
    public ResponseEntity<?> addPublisher(@RequestBody PublisherRequest publisherRequest) {
        
        int recordedId = publisherService.addPublisher(publisherRequest);
        return ResponseEntity.ok("Publisher registered successfully! Publisher id: " + recordedId);
    }

    @GetMapping("/getall")
    public List<PublisherResponse> getAllPublishers() {
        List<PublisherResponse> publisherList = publisherService.getAllPublishers();
        return publisherList;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PublisherResponse> getPublisher(@PathVariable("id") int publisherId) {
        PublisherResponse publisherResponse = publisherService.getPublisherById(publisherId);
        return ResponseEntity.ok(publisherResponse);
        
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePublisherById(@PathVariable int id){
        return ResponseEntity.ok(publisherService.deletePublisherById(id) + id);
    }
}
