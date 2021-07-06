package com.zikzak.zikzakbackend.controller;

import com.zikzak.zikzakbackend.model.Categories;
import com.zikzak.zikzakbackend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = {"${development.url}", "${production.url}"}, allowCredentials = "true")
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<String> getAllCategories() {
        return categoryService.getAllCategories();
    }
}
