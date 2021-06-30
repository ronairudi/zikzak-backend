package com.zikzak.zikzakbackend.model;

import lombok.Data;

@Data
public class ClientForm {
    private String city;
    private String zipCode;
    private String street;
    private String streetNum;
    private String activityName;
    private Categories category;
    private String phone;
    private String email;
    private String description;
    private int minAge;
    private int maxAge;
    private boolean isPremium;
}
