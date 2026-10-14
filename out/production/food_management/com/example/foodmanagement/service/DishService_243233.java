package com.example.foodmanagement.service;

import com.example.foodmanagement.dto.DishForm_243233;
import com.example.foodmanagement.dto.DishFilter_243233;
import com.example.foodmanagement.specification.DishSpecification_243233;
import com.example.foodmanagement.entity.DishCategory_243233;
import com.example.foodmanagement.entity.DishImage_243233;
import com.example.foodmanagement.entity.Dish_243233;
import com.example.foodmanagement.exception.InvalidDishException;
import com.example.foodmanagement.exception.ResourceNotFoundException;
import com.example.foodmanagement.repository.DishCategoryRepository_243233;
import com.example.foodmanagement.repository.DishImageRepository_243233;
import com.example.foodmanagement.repository.DishRepository_243233;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DishService_243233 {

    private final DishRepository_243233 dishRepository;
    private final DishCategoryRepository_243233 categoryRepository;
    private final DishImageRepository_243233 imageRepository;

    public DishService_243233(DishRepository_243233 dishRepository,
                              DishCategoryRepository_243233 categoryRepository,
                              DishImageRepository_243233 imageRepository) {
        this.dishRepository = dishRepository;
        this.categoryRepository = categoryRepository;
        this.imageRepository = imageRepository;
    }

    @Transactional(readOnly = true)
    public List<Dish_243233> findAll() {
        return dishRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Dish_243233> search(DishFilter_243233 filter) {
        Sort sort = switch (filter.getSort() == null ? "" : filter.getSort()) {
            case "priceAsc" -> Sort.by("price").ascending();
            case "priceDesc" -> Sort.by("price").descending();
            case "preparationTimeAsc" -> Sort.by("preparationTime").ascending();
            default -> Sort.by("id").ascending();
        };
        return dishRepository.findAll(DishSpecification_243233.matching(filter), sort);
    }

    @Transactional(readOnly = true)
    public Dish_243233 findById(Long id) {
        return dishRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("error.dishNotFound"));
    }

    @Transactional(readOnly = true)
    public List<DishImage_243233> findImagesByDishId(Long dishId) {
        return imageRepository.findByDish_Id(dishId);
    }

    @Transactional
    public Dish_243233 create(DishForm_243233 form) {
        DishCategory_243233 category = resolveCategory(form.getDishCategoryId());
        if (dishRepository.existsByNameAndDishCategory_Id(form.getName().trim(), category.getId())) {
            throw new InvalidDishException("name", "validation.name.duplicate");
        }

        Dish_243233 dish = new Dish_243233();
        copyForm(form, category, dish);
        return dishRepository.saveAndFlush(dish);
    }

    @Transactional
    public Dish_243233 update(Long id, DishForm_243233 form) {
        Dish_243233 dish = dishRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("error.dishNotFound"));
        DishCategory_243233 category = resolveCategory(form.getDishCategoryId());
        if (dishRepository.existsByNameAndDishCategory_IdAndIdNot(form.getName().trim(), category.getId(), id)) {
            throw new InvalidDishException("name", "validation.name.duplicate");
        }

        copyForm(form, category, dish);
        return dishRepository.saveAndFlush(dish);
    }

    private DishCategory_243233 resolveCategory(Long categoryId) {
        if (categoryId == null) {
            throw new InvalidDishException("dishCategoryId", "validation.category.required");
        }
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new InvalidDishException("dishCategoryId", "validation.category.existing"));
    }

    private void copyForm(DishForm_243233 form, DishCategory_243233 category, Dish_243233 dish) {
        dish.setName(form.getName().trim());
        dish.setPrice(form.getPrice());
        dish.setPreparationTime(form.getPreparationTime());
        dish.setIsAvailable(Boolean.TRUE.equals(form.getIsAvailable()));
        dish.setDescription(form.getDescription());
        dish.setDishCategory(category);
    }
}
