package org.example.bai1.service;

import org.example.bai1.entity.Category;
import org.example.bai1.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category find(Long id){
        return categoryRepository.findById(id).get();
    }
}
