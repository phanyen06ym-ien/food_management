package com.example.foodmanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "243233_DishImages")
public class DishImage_243233 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "243233_Id")
    private Long id;

    @Column(name = "243233_ImageUrl")
    private String imageUrl;

    @Column(name = "243233_IsThumbnail")
    private Boolean isThumbnail = false;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "243233_DishId", referencedColumnName = "243233_Id", nullable = false)
    private Dish_243233 dish;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getIsThumbnail() {
        return isThumbnail;
    }

    public void setIsThumbnail(Boolean thumbnail) {
        isThumbnail = thumbnail;
    }

    public Dish_243233 getDish() {
        return dish;
    }

    public void setDish(Dish_243233 dish) {
        this.dish = dish;
    }
}
