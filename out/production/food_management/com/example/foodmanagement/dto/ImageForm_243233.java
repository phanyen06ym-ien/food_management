package com.example.foodmanagement.dto;

import jakarta.validation.constraints.NotBlank;

public class ImageForm_243233 {
    @NotBlank(message = "{validation.imageUrl.required}")
    private String imageUrl;

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
