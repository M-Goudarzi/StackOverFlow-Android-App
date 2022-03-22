package com.example.retrofit_test.Model.Networking.ModelObject;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class UserResponse {

    @SerializedName("items")
    private final List<Owner> owners = null;
    @SerializedName("has_more")
    private Boolean hasMore;
    @SerializedName("quota_max")
    private Integer quotaMax;
    @SerializedName("quota_remaining")
    private Integer quotaRemaining;

    public List<Owner> getOwners() {
        return owners;
    }
    public Boolean getHasMore() {
        return hasMore;
    }
    public Integer getQuotaMax() {
        return quotaMax;
    }
    public Integer getQuotaRemaining() {
        return quotaRemaining;
    }

}
