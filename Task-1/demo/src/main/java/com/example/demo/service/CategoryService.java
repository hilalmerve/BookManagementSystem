package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.Category;
import com.example.demo.model.request.CategoryRequest;
import com.example.demo.model.response.CategoryResponse;
import com.example.demo.repository.CategoryRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    public int addCategory(CategoryRequest categoryRequest) {
        Category category = modelMapper.map(categoryRequest, Category.class);
        categoryRepository.save(category);
        return category.getId();
    }

    public List<CategoryResponse> getAllCategories(){
        List<Category> categoryList = categoryRepository.getAllCategories();// findall ile aynı. bu entity listesi dönüyor.
        List<CategoryResponse> resultList = categoryList.stream()
        .map(author -> modelMapper.map(author, CategoryResponse.class))
        .collect(Collectors.toList());
        return resultList;
    }

    public CategoryResponse getCategoryById(int id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        
        // Eğer değer varsa, Category nesnesini döndürür.
        if (optionalCategory.isPresent()) {
            CategoryResponse categoryResponse = modelMapper.map(optionalCategory, CategoryResponse.class);
            return categoryResponse;
        } else {
            // Değer bulunamadıysa, gerekli işlemleri yapabilir veya null dönebilirsiniz.
            throw new RuntimeException("Error: Category is not found.");
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

    public String deleteCategoryById(int id) {
        Category category = categoryRepository.findById(id).orElseThrow();
        categoryRepository.deleteById(category.getId());
        //categoryRepository.save(category);
        return "Category is deleted : ";
    }
}
