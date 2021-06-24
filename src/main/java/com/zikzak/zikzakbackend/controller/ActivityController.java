package com.zikzak.zikzakbackend.controller;

import com.zikzak.zikzakbackend.service.ActivityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.google.gson.Gson;

@Controller
public class ActivityController {

    private final ActivityService activityService;
    private final Gson gson;


    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
        this.gson = new Gson();
    }

    @GetMapping
    public ResponseEntity getActivitiesByFilters(@RequestParam("city") String city,
                                                 @RequestParam("category") String category,
                                                 @RequestParam("age") String age
                                                 ) {
        if (!categoryService.isInEnum(category) && !category.equals("ALL")) return ResponseEntity.badRequest().body("Invalid category");

        if (!isNumeric(age) && !age.equals("ALL")) return ResponseEntity.badRequest().body("Invalid age");

        return ResponseEntity.ok(activityService.getActivitiesByFilters(city, category, age));
    }

    private boolean isNumeric(String strNum) {
        return strNum.matches("\\d+");
    }

}
