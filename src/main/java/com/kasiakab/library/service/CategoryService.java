package com.kasiakab.library.service;

import com.kasiakab.library.dto.CategoryDTO;
import com.kasiakab.library.exception.NotFoundException;
import com.kasiakab.library.model.Category;
import com.kasiakab.library.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category save(CategoryDTO dto) {
        Category category = new Category();
        category.setName(dto.getName());
        return categoryRepository.save(category);
    }


    public Category update(Long id, CategoryDTO dto) {
        return categoryRepository.findById(id)
                .map(existing -> {
                    existing.setName(dto.getName());
                    return categoryRepository.save(existing);
                })
                .orElseThrow(() -> new NotFoundException("Category with id " + id + " not found"));
    }

}
