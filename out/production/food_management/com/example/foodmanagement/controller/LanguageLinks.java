package com.example.foodmanagement.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.util.UriComponentsBuilder;

@ControllerAdvice
public class LanguageLinks {
    @ModelAttribute
    public void addLinks(HttpServletRequest request, Model model) {
        String path = request.getRequestURI();
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            path = path.replaceFirst("/images(?:/\\d+/thumbnail)?$", "");
        }
        String query = request.getQueryString();
        UriComponentsBuilder base = UriComponentsBuilder.fromPath(path);
        if (query != null && !query.isBlank()) {
            base.query(query);
        }
        model.addAttribute("englishUrl", base.cloneBuilder().replaceQueryParam("lang", "en").build().toUriString());
        model.addAttribute("vietnameseUrl", base.cloneBuilder().replaceQueryParam("lang", "vi").build().toUriString());
    }
}
