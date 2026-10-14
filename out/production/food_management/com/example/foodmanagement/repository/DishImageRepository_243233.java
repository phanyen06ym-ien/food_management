package com.example.foodmanagement.repository;

import com.example.foodmanagement.entity.DishImage_243233;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DishImageRepository_243233 extends JpaRepository<DishImage_243233, Long> {

    List<DishImage_243233> findByDish_Id(Long dishId);

    Optional<DishImage_243233> findFirstByDish_IdAndIsThumbnailTrueOrderByIdAsc(Long dishId);

    boolean existsByDish_IdAndIsThumbnailTrue(Long dishId);
}
