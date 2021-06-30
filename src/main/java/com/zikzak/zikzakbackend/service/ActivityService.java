package com.zikzak.zikzakbackend.service;

import com.zikzak.zikzakbackend.model.ActivityModel;
import com.zikzak.zikzakbackend.model.Categories;
import com.zikzak.zikzakbackend.model.ClientForm;
import com.zikzak.zikzakbackend.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    public void saveActivity(ActivityModel activityModel) {
        activityRepository.save(activityModel);
    }

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

    public ActivityModel clientFormToActivity(ClientForm clientForm) {
        return ActivityModel.builder()
                .city(clientForm.getCity())
                .zipCode(Integer.parseInt(clientForm.getZipCode()))
                .address(clientForm.getStreet() + ", " + clientForm.getStreetNum())
                .name(clientForm.getActivityName())
                .category(clientForm.getCategory())
                .phoneNumber(clientForm.getPhone())
                .email(clientForm.getEmail())
                .comment(clientForm.getDescription())
                .ageMinLimit(clientForm.getMinAge())
                .ageMaxLimit(clientForm.getMaxAge())
                .isPremium(clientForm.isPremium())
                .build();
    }
}
