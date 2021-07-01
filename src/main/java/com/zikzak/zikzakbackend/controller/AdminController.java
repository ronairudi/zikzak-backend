package com.zikzak.zikzakbackend.controller;

import com.zikzak.zikzakbackend.model.ActivityModel;
import com.zikzak.zikzakbackend.service.ActivityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"${development.url}", "${production.url}"})
@RequestMapping("/admin")
public class AdminController {

    private final ActivityService activityService;

    public AdminController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping
    public List<ActivityModel> getInactiveActivities() {
        return activityService.getInactiveActivities();
    }

    @PostMapping("/update/{id}")
    public void updateActivity(@RequestBody Boolean isAccepted, @PathVariable("id") Long id) {
        if ((isAccepted)) {
            activityService.activateActivity(id);
        } else {
            activityService.deleteActivityById(id);
        }
    }
}
