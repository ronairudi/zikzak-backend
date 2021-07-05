package com.zikzak.zikzakbackend.service;

import com.zikzak.zikzakbackend.model.ActivityModel;
import com.zikzak.zikzakbackend.model.Categories;
import com.zikzak.zikzakbackend.model.ClientForm;
import com.zikzak.zikzakbackend.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    public void saveActivity(ActivityModel activityModel) {
        activityRepository.save(activityModel);
    }

    public void deleteActivityById(Long id) {
        activityRepository.deleteById(id);
    }

    public void activateActivity(Long id) {
        ActivityModel activityModel = activityRepository.findById(id).orElseThrow( ()-> new NoSuchElementException("Activity not found by id - " + id));
        activityModel.setActive(true);
        activityRepository.save(activityModel);
    }

    public void updateActivityById(Long id, ActivityModel newActivityModel) {
        ActivityModel oldActivityModel = activityRepository.findById(id).orElseThrow( ()-> new NoSuchElementException("Activity not found by id - " + id));

        oldActivityModel.setAddress(newActivityModel.getAddress());
        oldActivityModel.setAgeMinLimit(newActivityModel.getAgeMinLimit());
        oldActivityModel.setAgeMaxLimit(newActivityModel.getAgeMaxLimit());
        oldActivityModel.setCategory(newActivityModel.getCategory());
        oldActivityModel.setCity(newActivityModel.getCity());
        oldActivityModel.setComment(newActivityModel.getComment());
        oldActivityModel.setDurationInMinutes(newActivityModel.getDurationInMinutes());
        oldActivityModel.setEmail(newActivityModel.getEmail());
        oldActivityModel.setLatitude(newActivityModel.getLatitude());
        oldActivityModel.setLongitude(newActivityModel.getLongitude());
        oldActivityModel.setName(newActivityModel.getName());
        oldActivityModel.setPhoneNumber(newActivityModel.getPhoneNumber());
        oldActivityModel.setActive(newActivityModel.isActive());
        oldActivityModel.setPremium(newActivityModel.isPremium());
        oldActivityModel.setPriceInHUF(newActivityModel.getPriceInHUF());
        oldActivityModel.setSubcategory(newActivityModel.getSubcategory());
        oldActivityModel.setZipCode(newActivityModel.getZipCode());
        activityRepository.save(oldActivityModel);
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
