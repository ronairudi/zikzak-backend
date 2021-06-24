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
                return activityRepository.findAllByCity(city);
            }
            return activityRepository.findAllByCityAndAgeMaxLimitLessThanEqualAndAgeMinLimitGreaterThanEqual(city, Integer.parseInt(age), Integer.parseInt(age));
        }else if (age.equals("ALL")){
            return activityRepository.findAllByCityAndCategory(city, Categories.valueOf(category));
        }
        return activityRepository.findAllByCityAndCategoryAndAgeMaxLimitLessThanEqualAndAgeMinLimitGreaterThanEqual(city, Categories.valueOf(category), Integer.parseInt(age), Integer.parseInt(age));
    }
}
