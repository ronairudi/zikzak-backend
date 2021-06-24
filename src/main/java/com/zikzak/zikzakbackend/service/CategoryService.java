package com.zikzak.zikzakbackend.service;

import com.zikzak.zikzakbackend.model.Categories;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CategoryService {

    public List<String> getAllCategories() {
        return Stream.of(Categories.values())
                .map(Categories::name)
                .collect(Collectors.toList());
    }

    public boolean isInEnum(String value) {
        return Arrays.stream(Categories.values()).anyMatch(e -> e.name().equals(value));
    }
}
