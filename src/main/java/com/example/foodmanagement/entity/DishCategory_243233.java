package com.example.foodmanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "243233_DishCategories")
public class DishCategory_243233 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "243233_Id")
    private Long id;

    @NotBlank
    @Column(name = "243233_Name")
    private String name;

    @Column(name = "243233_Description")
    private String description;

    @OneToMany(mappedBy = "dishCategory")
    private List<Dish_243233> dishes = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Dish_243233> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish_243233> dishes) {
        this.dishes = dishes;
    }
}
