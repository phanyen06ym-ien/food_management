package com.example.foodmanagement.dto;

import com.example.foodmanagement.entity.Dish_243233;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class DishForm_243233 {

    @NotBlank(message = "{validation.name.required}")
    private String name;

    @NotNull(message = "{validation.price.required}")
    @DecimalMin(value = "0.01", message = "{validation.price.positive}")
    private BigDecimal price;

    @NotNull(message = "{validation.time.required}")
    @Min(value = 1, message = "{validation.time.positive}")
    private Integer preparationTime;

    private Boolean isAvailable = true;

    private String description;

    @NotNull(message = "{validation.category.required}")
    private Long dishCategoryId;

    public static DishForm_243233 fromDish(Dish_243233 dish) {
        DishForm_243233 form = new DishForm_243233();
        form.setName(dish.getName());
        form.setPrice(dish.getPrice());
        form.setPreparationTime(dish.getPreparationTime());
        form.setIsAvailable(dish.getIsAvailable());
        form.setDescription(dish.getDescription());
        form.setDishCategoryId(dish.getDishCategory().getId());
        return form;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(Integer preparationTime) {
        this.preparationTime = preparationTime;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean available) {
        isAvailable = available;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getDishCategoryId() {
        return dishCategoryId;
    }

    public void setDishCategoryId(Long dishCategoryId) {
        this.dishCategoryId = dishCategoryId;
    }
}
