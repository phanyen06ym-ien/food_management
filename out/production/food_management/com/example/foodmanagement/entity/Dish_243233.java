package com.example.foodmanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "243233_Dishes", uniqueConstraints = @UniqueConstraint(
        name = "UK_243233_Dishes_Name_Category",
        columnNames = {"243233_Name", "243233_DishCategoryId"}
))
public class Dish_243233 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "243233_Id")
    private Long id;

    @NotBlank
    @Column(name = "243233_Name")
    private String name;

    @NotNull
    @DecimalMin(value = "0.01")
    @Column(name = "243233_Price")
    private BigDecimal price;

    @NotNull
    @Min(1)
    @Column(name = "243233_PreparationTime")
    private Integer preparationTime;

    @Column(name = "243233_IsAvailable")
    private Boolean isAvailable = true;

    @Column(name = "243233_Description")
    private String description;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "243233_DishCategoryId", referencedColumnName = "243233_Id", nullable = false)
    private DishCategory_243233 dishCategory;

    @OneToMany(mappedBy = "dish")
    private List<DishImage_243233> dishImages = new ArrayList<>();

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

    @Transient
    public boolean isQuickPreparation() {
        return preparationTime != null && preparationTime <= 15;
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

    public DishCategory_243233 getDishCategory() {
        return dishCategory;
    }

    public void setDishCategory(DishCategory_243233 dishCategory) {
        this.dishCategory = dishCategory;
    }

    public List<DishImage_243233> getDishImages() {
        return dishImages;
    }

    public void setDishImages(List<DishImage_243233> dishImages) {
        this.dishImages = dishImages;
    }
}
