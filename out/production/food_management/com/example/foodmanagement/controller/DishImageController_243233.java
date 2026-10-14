package com.example.foodmanagement.controller;

import com.example.foodmanagement.dto.ImageForm_243233;
import com.example.foodmanagement.service.DishImageService_243233;
import com.example.foodmanagement.service.DishService_243233;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DishImageController_243233 {
    private final DishImageService_243233 images;
    private final DishService_243233 dishes;

    public DishImageController_243233(DishImageService_243233 images, DishService_243233 dishes) {
        this.images = images;
        this.dishes = dishes;
    }

    @PostMapping("/dishes/{dishId}/images")
    public String add(@PathVariable Long dishId, @Valid @ModelAttribute("imageForm") ImageForm_243233 form,
                      BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("dish", dishes.findById(dishId));
            model.addAttribute("images", dishes.findImagesByDishId(dishId));
            return "dishes/detail";
        }
        images.addImage(dishId, form.getImageUrl());
        return "redirect:/dishes/" + dishId;
    }

    @PostMapping("/dishes/{dishId}/images/{imageId}/thumbnail")
    public String thumbnail(@PathVariable Long dishId, @PathVariable Long imageId) {
        images.setThumbnail(dishId, imageId);
        return "redirect:/dishes/" + dishId;
    }
}
