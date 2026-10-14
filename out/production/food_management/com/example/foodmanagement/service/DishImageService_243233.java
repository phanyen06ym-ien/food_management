package com.example.foodmanagement.service;

import com.example.foodmanagement.entity.DishImage_243233;
import com.example.foodmanagement.entity.Dish_243233;
import com.example.foodmanagement.exception.ResourceNotFoundException;
import com.example.foodmanagement.repository.DishImageRepository_243233;
import com.example.foodmanagement.repository.DishRepository_243233;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DishImageService_243233 {
    private final DishRepository_243233 dishes;
    private final DishImageRepository_243233 images;

    public DishImageService_243233(DishRepository_243233 dishes, DishImageRepository_243233 images) {
        this.dishes = dishes;
        this.images = images;
    }

    @Transactional
    public void addImage(Long dishId, String imageUrl) {
        Dish_243233 dish = dishes.findById(dishId)
                .orElseThrow(() -> new ResourceNotFoundException("error.dishNotFound"));
        if (imageUrl == null || imageUrl.isBlank()) {
            throw new IllegalArgumentException("validation.imageUrl.required");
        }
        DishImage_243233 image = new DishImage_243233();
        image.setDish(dish);
        image.setImageUrl(imageUrl.trim());
        image.setIsThumbnail(false);
        images.save(image);
    }

    @Transactional
    public void setThumbnail(Long dishId, Long imageId) {
        dishes.lockById(dishId)
                .orElseThrow(() -> new ResourceNotFoundException("error.dishNotFound"));
        DishImage_243233 selected = images.findById(imageId)
                .orElseThrow(() -> new ResourceNotFoundException("error.imageNotFound"));
        if (!selected.getDish().getId().equals(dishId)) {
            throw new ResourceNotFoundException("error.imageNotFound");
        }
        for (DishImage_243233 image : images.findByDish_Id(dishId)) {
            image.setIsThumbnail(image.getId().equals(imageId));
        }
        images.flush();
    }
}
