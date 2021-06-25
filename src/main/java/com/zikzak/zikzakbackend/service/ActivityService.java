package com.zikzak.zikzakbackend.service;

import com.zikzak.zikzakbackend.model.ActivityModel;
import com.zikzak.zikzakbackend.model.Categories;
import com.zikzak.zikzakbackend.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    public List<ActivityModel> getActivitiesByFilters(String city, String category, String age) {
        if (category.equals("ALL")){
            if (age.equals("ALL")){
                return activityRepository.findAllByCityIgnoreCase(city);
            }
            return activityRepository.findAllByCityIgnoreCaseAndAgeMaxLimitGreaterThanEqualAndAgeMinLimitLessThanEqual(city, Integer.parseInt(age), Integer.parseInt(age));
        }else if (age.equals("ALL")){
            return activityRepository.findAllByCityIgnoreCaseAndCategory(city, Categories.valueOf(category));
        }
        return activityRepository.findAllByCityIgnoreCaseAndCategoryAndAgeMaxLimitGreaterThanEqualAndAgeMinLimitLessThanEqual(city, Categories.valueOf(category), Integer.parseInt(age), Integer.parseInt(age));
    }
}
