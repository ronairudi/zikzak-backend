package com.zikzak.zikzakbackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ClientForm {
    private String city;
    private String zipCode;
    private String street;
    private String streetNum;
    private float latitude;
    private float longitude;
    private String activityName;
    private Categories category;
    private String phone;
    private String email;
    private String description;
    private int price;
    private int minutes;
    private int minAge;
    private int maxAge;
    @JsonProperty
    private boolean isPremium;
}
