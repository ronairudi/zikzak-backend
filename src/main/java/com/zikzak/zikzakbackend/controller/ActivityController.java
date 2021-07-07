package com.zikzak.zikzakbackend.controller;

import com.google.gson.Gson;
import com.zikzak.zikzakbackend.model.ActivityModel;
import com.zikzak.zikzakbackend.model.ClientForm;
import com.zikzak.zikzakbackend.service.ActivityService;
import com.zikzak.zikzakbackend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"${development.url}", "${production.url}"}, allowCredentials = "true")
@RequestMapping("/activities")
public class ActivityController {

    private final ActivityService activityService;
    private final Gson gson;
    @Autowired
    private CategoryService categoryService;


    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
        this.gson = new Gson();
    }

    @GetMapping
    public List<ActivityModel> getActivitiesByFilters(@RequestParam("city") String city,
                                                 @RequestParam("category") String category,
                                                 @RequestParam("age") String age
                                                 ) {
        if (!categoryService.isInEnum(category) && !category.equals("ALL")) throw new IllegalArgumentException("Invalid category");

        if (!isNumeric(age) && !age.equals("ALL")) throw new IllegalArgumentException("Invalid age");

        return activityService.getActivitiesByFilters(city, category, age);
    }

    @PostMapping("/add")
    public ResponseEntity addActivity(@RequestBody ClientForm clientForm) {
        ActivityModel activityModel = activityService.clientFormToActivity(clientForm);
        activityService.saveActivity(activityModel);
        return ResponseEntity.ok("");
    }

    @GetMapping("/all")
    public List<ActivityModel> getAllActivities() {
        return activityService.getAllActivities();
    }

    @GetMapping("/inactive")
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

    private boolean isNumeric(String strNum) {
        return strNum.matches("\\d+");
    }

}
