package com.zikzak.zikzakbackend;

import com.zikzak.zikzakbackend.model.ActivityModel;
import com.zikzak.zikzakbackend.model.Categories;
import com.zikzak.zikzakbackend.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public void run(String... args) {
        activityRepository.saveAll(
        Arrays.asList(new ActivityModel(1,
                "Festés",
                Categories.ART,
                "0123456789",
                "festés@gmail.com",
                3,
                12,
                47.48504283919209F,
                19.216608705686475F,
                "",
                "Budapest"),
        new ActivityModel(2,
                "Úszás",
                Categories.SPORT,
                "012334354657",
                "úszás@gmail.com",
                8,
                10,
                47.480137429426364F,
                19.115226066466942F,
                "",
                "Budapest"),
        new ActivityModel(3,
                "Múzeum",
                Categories.EDUCATION,
                "012343452436",
                "múzeum@gmail.com",
                6,
                10,
                47.51572120351807F,
                19.07556873442414F,
                "",
                "Budapest"))
        );
    }
}
