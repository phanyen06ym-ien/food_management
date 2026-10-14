package com.example.foodmanagement.specification;

import com.example.foodmanagement.dto.DishFilter_243233;
import com.example.foodmanagement.entity.Dish_243233;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;

public final class DishSpecification_243233 {
    private DishSpecification_243233() { }

    public static Specification<Dish_243233> matching(DishFilter_243233 filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filter.getName() != null && !filter.getName().isBlank()) {
                String escaped = filter.getName().trim().toLowerCase()
                        .replace("\\", "\\\\").replace("%", "\\%").replace("_", "\\_");
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + escaped + "%", '\\'));
            }
            if (filter.getCategory() != null) {
                predicates.add(cb.equal(root.get("dishCategory").get("id"), filter.getCategory()));
            }
            if (filter.getAvailable() != null) {
                predicates.add(cb.equal(root.get("isAvailable"), filter.getAvailable()));
            }
            if (filter.getMinPrice() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("price"), filter.getMinPrice()));
            }
            if (filter.getMaxPrice() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("price"), filter.getMaxPrice()));
            }
            return cb.and(predicates.toArray(Predicate[]::new));
        };
    }
}
