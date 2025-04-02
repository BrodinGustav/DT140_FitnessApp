package com.example.App.service;

import com.example.App.model.Category;
import com.example.App.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
 
    @Autowired
    private CategoryRepository categoryRepository;

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category getCategoryById(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}

