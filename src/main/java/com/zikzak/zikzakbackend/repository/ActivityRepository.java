package com.zikzak.zikzakbackend.repository;

import com.zikzak.zikzakbackend.model.ActivityModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<ActivityModel, Long> {

    List<ActivityModel> findAllByCategories(String category);
}
