package com.example.foodmanagement.repository;

import com.example.foodmanagement.entity.DishCategory_243233;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DishCategoryRepository_243233 extends JpaRepository<DishCategory_243233, Long> {

    Optional<DishCategory_243233> findFirstByNameOrderByIdAsc(String name);
}
