package com.example.App.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.App.execption.ResourceNotFoundException;
import com.example.App.model.Category;
import com.example.App.repository.CategoryRepository;

public class CategoryServiceImpl implements CategoryService {

     @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(Integer id) {
        return categoryRepository.findById(id)
         .orElseThrow(() -> new ResourceNotFoundException("Kategori med id " + id + " hittades inte."));
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category updateCategory(Integer id, Category categoryDetails) {
        Category category = categoryRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Kategori med id " + id + " hittades inte."));
    
        category.setName(categoryDetails.getName());
        return categoryRepository.save(category);
    }
    
    @Override
    public void deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
    }
    
}
