package com.example.foodmanagement.controller;

import com.example.foodmanagement.service.DishCategoryService_243233;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DishCategoryController_243233 {

    private static final String CATEGORY_IN_USE = "category.inUse";

    private final DishCategoryService_243233 categoryService;

    public DishCategoryController_243233(DishCategoryService_243233 categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public String list(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "categories/list";
    }

    @PostMapping("/categories/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            if (categoryService.deleteIfUnused(id)) {
                redirectAttributes.addFlashAttribute("success", "category.deleted");
            } else {
                redirectAttributes.addFlashAttribute("error", CATEGORY_IN_USE);
            }
        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addFlashAttribute("error", CATEGORY_IN_USE);
        }
        return "redirect:/categories";
    }
}
