package com.zikzak.zikzakbackend;

import com.zikzak.zikzakbackend.model.*;
import com.zikzak.zikzakbackend.repository.ActivityRepository;
import com.zikzak.zikzakbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) {
        ActivityModel art = ActivityModel.builder()
                .name("Alkotásutca").category(Categories.ART)
                .phoneNumber("36203340772").email("info@alkotasutca.hu")
                .ageMinLimit(10).ageMaxLimit(12)
                .latitude(47.515390F).longitude(19.052890F)
                .comment("Az ország legnagyobb és legkényelmesebb élményfestés foglalkozását nyújtjuk. Az inspiráló környezeten túl az alkotáshoz minden alapanyagot és segítséget biztosítunk.")
                .city("Budapest").zipCode(1136).address("Pannónia u. 30")
                .priceInHUF(9990).durationInMinutes(180)
                .subcategory("Élményfestés")
                .isActive(true).isPremium(false)
                .build();

        ActivityModel art2 = ActivityModel.builder()
                .name("GlückFrigyesút").category(Categories.ART)
                .phoneNumber("36203340772").email("info@glückfrigyes.hu")
                .ageMinLimit(10).ageMaxLimit(12)
                .latitude(47.545454F).longitude(18.984495F)
                .comment("Az ország legnagyobb és legkényelmesebb zongora foglalkozását nyújtjuk. Az inspiráló környezeten túl az alkotáshoz minden alapanyagot és segítséget biztosítunk.")
                .city("Budapest").zipCode(1025).address("Glück Frigyes út")
                .priceInHUF(9990).durationInMinutes(180)
                .subcategory("Zongora")
                .isActive(true).isPremium(false)
                .build();

        ActivityModel sport = ActivityModel.builder()
                .name("Smile Úszóiskola").category(Categories.SPORT)
                .phoneNumber("36305995762").email("info@smileuszoiskola.hu")
                .ageMinLimit(6).ageMaxLimit(8)
                .latitude(47.520990F).longitude(19.127897F)
                .comment("A Smile Úszóiskola elsődleges célja, hogy megszerettesse a vizet a gyermekkel, és megfelelő úszásalapot biztosítson a jövőre – akár verseny, akár hobbi szinten szeretnék folytatni az úszást.")
                .city("Budapest").zipCode(1149).address("Egressy út 178/F")
                .priceInHUF(3500).durationInMinutes(45)
                .subcategory("Kezdő úszás")
                .isActive(true).isPremium(true)
                .build();

        ActivityModel sport2 = ActivityModel.builder()
                .name("Atlétika").category(Categories.SPORT)
                .phoneNumber("36305595762").email("info@atletika.hu")
                .ageMinLimit(6).ageMaxLimit(8)
                .latitude(47.407745F).longitude(19.002362F)
                .comment("Az Atlétika elsődleges célja, hogy megszerettesse a sportot a gyermekkel, és megfelelő altétika alapot biztosítson a jövőre – akár verseny, akár hobbi szinten szeretnék folytatni az atlétikát.")
                .city("Budapest").zipCode(1223).address("Rózsakert u. 58")
                .priceInHUF(3500).durationInMinutes(45)
                .subcategory("Kezdő atlétika")
                .isActive(true).isPremium(true)
                .build();

        ActivityModel education = ActivityModel.builder()
                .name("Anyanyelvi Angol Tanár").category(Categories.EDUCATION)
                .phoneNumber("36305423790").email("anyanyelviangoltanar@gmail.com")
                .ageMinLimit(8).ageMaxLimit(12)
                .latitude(47.532160F).longitude(19.017500F)
                .comment("Tarné Anna vagyok, anyanyelvi magántanár, 25 éves tapasztalattal, töretlen lelkesedéssel várok minden kedves tanítványt.")
                .city("Budapest").zipCode(1037).address("Szépvölgyi út 115")
                .priceInHUF(7500).durationInMinutes(60)
                .subcategory("Angol óra")
                .isActive(false).isPremium(false)
                .build();

        ActivityModel education2 = ActivityModel.builder()
                .name("Anyanyelvi Német Tanár").category(Categories.EDUCATION)
                .phoneNumber("36305422790").email("anyanyelvinemettanar@gmail.com")
                .ageMinLimit(6).ageMaxLimit(12)
                .latitude(47.49046943019401F).longitude(19.03447201067646F)
                .comment("Tarné Gréta vagyok, anyanyelvi magántanár, 25 éves tapasztalattal, töretlen lelkesedéssel várok minden kedves tanítványt.")
                .city("Budapest").zipCode(1016).address("Galeotti u. 14-2")
                .priceInHUF(7000).durationInMinutes(60)
                .subcategory("Német óra")
                .isActive(false).isPremium(false)
                .build();

        activityRepository.saveAll(Arrays.asList(art, art2, sport, sport2, education, education2));

        UserModel user = UserModel.builder().email("admin@zikzak.hu").password("admin").role(Role.ADMIN).build();
        userService.addUser(user);
    }
}
