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

    public List<ActivityModel> getAllActivities() {
        return activityRepository.findAll();
    }

    public void saveActivity(ActivityModel activityModel) {
        activityRepository.save(activityModel);
    }

    public void deleteActivityById(Long id) {
        activityRepository.deleteById(id);
    }

    public void updateActivityById(Long id, ActivityModel activityModel) {
        activityModel.setId(id);
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

    public List<ActivityModel> getInactiveActivities(){
        return activityRepository.getInactiveActivities();
    }

    public ActivityModel clientFormToActivity(ClientForm clientForm) {
        return ActivityModel.builder()
                .city(clientForm.getCity())
                .zipCode(Integer.parseInt(clientForm.getZipCode()))
                .address(clientForm.getStreet() + " " + clientForm.getStreetNum())
                .latitude(clientForm.getLatitude())
                .longitude(clientForm.getLongitude())
                .name(clientForm.getActivityName())
                .category(clientForm.getCategory())
                .subcategory(clientForm.getSubcategory())
                .phoneNumber(clientForm.getPhone())
                .email(clientForm.getEmail())
                .comment(clientForm.getDescription())
                .priceInHUF(clientForm.getPrice())
                .durationInMinutes(clientForm.getMinutes())
                .ageMinLimit(clientForm.getMinAge())
                .ageMaxLimit(clientForm.getMaxAge())
                .isPremium(clientForm.isPremium())
                .build();
    }
}
