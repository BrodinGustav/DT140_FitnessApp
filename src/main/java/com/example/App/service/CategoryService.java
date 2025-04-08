package com.example.App.service;


import com.example.App.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    Category createCategory(Category category); 
    Category getCategoryById(Integer id); 
    List<Category>getAllCategories();
    Category updateCategory(Integer id, Category categoryDetails);
    void deleteCategory(Integer id);

 
}

