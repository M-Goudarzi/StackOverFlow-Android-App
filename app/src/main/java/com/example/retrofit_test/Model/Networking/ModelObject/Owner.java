package com.example.retrofit_test.Model.Networking.ModelObject;

import com.google.gson.annotations.SerializedName;

public class Owner {

    @SerializedName("user_id")
    private Integer userId;
    @SerializedName("profile_image")
    private String profileImage;

    public Integer getUserId() {
        return userId;
    }

    public String getProfileImage() {
        return profileImage;
    }

}
