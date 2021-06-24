package com.zikzak.zikzakbackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Data
public class ActivityModel {

    @Id
//    @SerializedName("id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //  @SerializedName("name")
    private String name;

    //  @SerializedName("category")
    private Categories category;

    //  @SerializedName("phoneNumber")
    private String phoneNumber;

    //  @SerializedName("email")
    private String email;

    //  @SerializedName("ageMinLimit")
    private int ageMinLimit;

    //  @SerializedName("ageMaxLimit")
    private int ageMaxLimit;

    //  @SerializedName("latitude")
    private Float latitude;

    //  @SerializedName("longitude")
    private Float longitude;

    //  @SerializedName("comment")
    private String comment;

    //  @SerializedName("city")
    private String city;
}
