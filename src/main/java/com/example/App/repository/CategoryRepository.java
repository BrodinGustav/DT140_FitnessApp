package com.example.App.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.App.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}

