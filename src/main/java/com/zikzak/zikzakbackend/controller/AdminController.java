package com.zikzak.zikzakbackend.controller;

import com.zikzak.zikzakbackend.model.ActivityModel;
import com.zikzak.zikzakbackend.model.ClientForm;
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

    @DeleteMapping("/update/{id}")
    public void deleteActivity(@PathVariable("id") Long id) {
        activityService.deleteActivityById(id);
    }

    @PostMapping("/update/{id}")
    public void updateActivity(@PathVariable("id") Long id, @RequestBody ClientForm clientForm) {
        ActivityModel activityModel = activityService.clientFormToActivity(clientForm);
        activityService.updateActivityById(id, activityModel);
    }
}
