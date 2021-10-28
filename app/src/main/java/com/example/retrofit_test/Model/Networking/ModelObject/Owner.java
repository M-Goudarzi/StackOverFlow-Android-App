package com.example.retrofit_test.Model.Networking.ModelObject;

import com.google.gson.annotations.SerializedName;

public class Owner {

    @SerializedName("account_id")
    private Integer accountId;
    @SerializedName("reputation")
    private Integer reputation;
    @SerializedName("user_id")
    private Integer userId;
    @SerializedName("user_type")
    private String userType;
    @SerializedName("profile_image")
    private String profileImage;
    @SerializedName("display_name")
    private String displayName;
    @SerializedName("link")
    private String link;
    @SerializedName("accept_rate")
    private Integer acceptRate;

    public Integer getAccountId() {
        return accountId;
    }

    public Integer getReputation() {
        return reputation;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUserType() {
        return userType;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getLink() {
        return link;
    }

    public Integer getAcceptRate() {
        return acceptRate;
    }


}
