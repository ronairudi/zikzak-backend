package com.zikzak.zikzakbackend.controller;

import com.zikzak.zikzakbackend.service.ActivityService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"${development.url}", "${production.url}"}, allowCredentials = "true")
@RequestMapping("/admin")
public class AdminController {

    private final ActivityService activityService;

    public AdminController(ActivityService activityService) {
        this.activityService = activityService;
    }
}
