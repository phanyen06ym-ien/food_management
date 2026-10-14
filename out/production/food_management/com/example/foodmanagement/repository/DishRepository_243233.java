package com.example.foodmanagement.repository;

import com.example.foodmanagement.entity.Dish_243233;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import jakarta.persistence.LockModeType;

import java.util.List;
import java.util.Optional;

public interface DishRepository_243233 extends JpaRepository<Dish_243233, Long>,
        JpaSpecificationExecutor<Dish_243233> {

    @Override
    @EntityGraph(attributePaths = "dishCategory")
    List<Dish_243233> findAll();

    @Override
    @EntityGraph(attributePaths = "dishCategory")
    Optional<Dish_243233> findById(Long id);

    @Override
    @EntityGraph(attributePaths = "dishCategory")
    List<Dish_243233> findAll(Specification<Dish_243233> spec, Sort sort);

    boolean existsByNameAndDishCategory_Id(String name, Long categoryId);

    boolean existsByNameAndDishCategory_IdAndIdNot(String name, Long categoryId, Long dishId);

    boolean existsByDishCategory_Id(Long categoryId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select d from Dish_243233 d where d.id = :id")
    Optional<Dish_243233> lockById(@Param("id") Long id);
}
