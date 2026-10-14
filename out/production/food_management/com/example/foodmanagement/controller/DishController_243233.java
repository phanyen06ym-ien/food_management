package com.example.foodmanagement.controller;

import com.example.foodmanagement.dto.DishForm_243233;
import com.example.foodmanagement.dto.DishFilter_243233;
import com.example.foodmanagement.dto.ImageForm_243233;
import com.example.foodmanagement.entity.Dish_243233;
import com.example.foodmanagement.exception.InvalidDishException;
import com.example.foodmanagement.service.DishCategoryService_243233;
import com.example.foodmanagement.service.DishService_243233;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DishController_243233 {

    private final DishService_243233 dishService;
    private final DishCategoryService_243233 categoryService;

    public DishController_243233(DishService_243233 dishService,
                                 DishCategoryService_243233 categoryService) {
        this.dishService = dishService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/dishes";
    }

    @GetMapping("/dishes")
    public String list(@ModelAttribute("filter") DishFilter_243233 filter,
                       BindingResult bindingResult, Model model) {
        model.addAttribute("categories", categoryService.findAll());
        if (bindingResult.hasErrors()) {
            model.addAttribute("filterError", "filter.invalid");
            model.addAttribute("dishes", java.util.List.of());
        } else if (filter.getMinPrice() != null && filter.getMaxPrice() != null
                && filter.getMinPrice().compareTo(filter.getMaxPrice()) > 0) {
            model.addAttribute("filterError", "filter.invalidRange");
            model.addAttribute("dishes", java.util.List.of());
        } else {
            model.addAttribute("dishes", dishService.search(filter));
        }
        return "dishes/list";
    }

    @GetMapping("/dishes/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("dish", dishService.findById(id));
        model.addAttribute("images", dishService.findImagesByDishId(id));
        model.addAttribute("imageForm", new ImageForm_243233());
        return "dishes/detail";
    }

    @GetMapping("/dishes/create")
    public String createForm(Model model) {
        model.addAttribute("dishForm", new DishForm_243233());
        return showForm(model, null);
    }

    @PostMapping("/dishes/create")
    public String create(@Valid @ModelAttribute("dishForm") DishForm_243233 form,
                         BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return showForm(model, null);
        }
        try {
            dishService.create(form);
            return "redirect:/dishes";
        } catch (InvalidDishException ex) {
            bindingResult.rejectValue(ex.getField(), ex.getMessage());
        } catch (DataIntegrityViolationException ex) {
            bindingResult.reject("dish.saveFailed");
        }
        return showForm(model, null);
    }

    @GetMapping("/dishes/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Dish_243233 dish = dishService.findById(id);
        model.addAttribute("dishForm", DishForm_243233.fromDish(dish));
        return showForm(model, id);
    }

    @PostMapping("/dishes/{id}/edit")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("dishForm") DishForm_243233 form,
                         BindingResult bindingResult, Model model) {
        dishService.findById(id);
        if (bindingResult.hasErrors()) {
            return showForm(model, id);
        }
        try {
            dishService.update(id, form);
            return "redirect:/dishes/" + id;
        } catch (InvalidDishException ex) {
            bindingResult.rejectValue(ex.getField(), ex.getMessage());
        } catch (DataIntegrityViolationException ex) {
            bindingResult.reject("dish.saveFailed");
        }
        return showForm(model, id);
    }

    private String showForm(Model model, Long id) {
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("editing", id != null);
        model.addAttribute("formAction", id == null ? "/dishes/create" : "/dishes/" + id + "/edit");
        return "dishes/form";
    }
}
