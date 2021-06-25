package com.zikzak.zikzakbackend.repository;

import com.zikzak.zikzakbackend.model.ActivityModel;
import com.zikzak.zikzakbackend.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ActivityRepository extends JpaRepository<ActivityModel, Long> {

    List<ActivityModel> findAllByCityIgnoreCase(String city);

    List<ActivityModel> findAllByCityIgnoreCaseAndAgeMaxLimitGreaterThanEqualAndAgeMinLimitLessThanEqual(String city, int age, int age2);

    List<ActivityModel> findAllByCityIgnoreCaseAndCategory(String city, Categories category);

    List<ActivityModel> findAllByCityIgnoreCaseAndCategoryAndAgeMaxLimitGreaterThanEqualAndAgeMinLimitLessThanEqual(String city, Categories category, int age, int age2);
}
