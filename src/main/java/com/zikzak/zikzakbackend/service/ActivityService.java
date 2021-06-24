package com.zikzak.zikzakbackend.service;

import com.zikzak.zikzakbackend.model.ActivityModel;
import com.zikzak.zikzakbackend.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;


    public List<ActivityModel> getActivitiesByCategory(String category) {
        return activityRepository.findAllByCategories(category);
    }
}
