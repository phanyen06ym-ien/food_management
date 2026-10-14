package com.example.foodmanagement.service;

import com.example.foodmanagement.entity.DishCategory_243233;
import com.example.foodmanagement.exception.ResourceNotFoundException;
import com.example.foodmanagement.repository.DishCategoryRepository_243233;
import com.example.foodmanagement.repository.DishRepository_243233;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DishCategoryService_243233 {

    private final DishCategoryRepository_243233 categoryRepository;
    private final DishRepository_243233 dishRepository;

    public DishCategoryService_243233(DishCategoryRepository_243233 categoryRepository,
                                      DishRepository_243233 dishRepository) {
        this.categoryRepository = categoryRepository;
        this.dishRepository = dishRepository;
    }

    @Transactional(readOnly = true)
    public List<DishCategory_243233> findAll() {
        return categoryRepository.findAll();
    }

    @Transactional
    public boolean deleteIfUnused(Long id) {
        DishCategory_243233 category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("error.categoryNotFound"));
        if (dishRepository.existsByDishCategory_Id(id)) {
            return false;
        }
        categoryRepository.delete(category);
        categoryRepository.flush();
        return true;
    }
}
