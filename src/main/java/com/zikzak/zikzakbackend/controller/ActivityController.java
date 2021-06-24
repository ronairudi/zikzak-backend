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

    @GetMapping("/activities/{category}")
    public String getActivitiesByCategory(@PathVariable("category") String category) {
        return gson.toJson(activityService.getActivitiesByCategory(category));
    }
}
