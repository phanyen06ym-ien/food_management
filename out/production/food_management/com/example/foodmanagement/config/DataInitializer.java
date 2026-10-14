package com.example.foodmanagement.config;

import com.example.foodmanagement.entity.DishCategory_243233;
import com.example.foodmanagement.entity.Dish_243233;
import com.example.foodmanagement.repository.DishCategoryRepository_243233;
import com.example.foodmanagement.repository.DishRepository_243233;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {

    private final DishCategoryRepository_243233 categoryRepository;
    private final DishRepository_243233 dishRepository;

    public DataInitializer(DishCategoryRepository_243233 categoryRepository,
                           DishRepository_243233 dishRepository) {
        this.categoryRepository = categoryRepository;
        this.dishRepository = dishRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        if (dishRepository.count() > 0) {
            return;
        }

        DishCategory_243233 mainDishes = ensureCategory("Main Dishes", "Main dishes");
        DishCategory_243233 fastFood = ensureCategory("Fast Food", "Quick meals and snacks");
        DishCategory_243233 drinks = ensureCategory("Drinks", "Beverages");

        ensureDish("Pho Bo", 50000, 20, true, mainDishes);
        ensureDish("Com Ga", 45000, 18, true, mainDishes);
        ensureDish("Hamburger", 40000, 10, true, fastFood);
        ensureDish("French Fries", 30000, 8, false, fastFood);
        ensureDish("Lemon Tea", 20000, 5, true, drinks);
    }

    private DishCategory_243233 ensureCategory(String name, String description) {
        return categoryRepository.findFirstByNameOrderByIdAsc(name).orElseGet(() -> {
            DishCategory_243233 category = new DishCategory_243233();
            category.setName(name);
            category.setDescription(description);
            return categoryRepository.save(category);
        });
    }

    private void ensureDish(String name, long price, int preparationTime, boolean available,
                            DishCategory_243233 category) {
        if (dishRepository.existsByNameAndDishCategory_Id(name, category.getId())) {
            return;
        }

        Dish_243233 dish = new Dish_243233();
        dish.setName(name);
        dish.setPrice(BigDecimal.valueOf(price));
        dish.setPreparationTime(preparationTime);
        dish.setIsAvailable(available);
        dish.setDishCategory(category);
        dishRepository.save(dish);
    }
}
