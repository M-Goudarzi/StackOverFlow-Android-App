package com.example.retrofit_test.ModelObject;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StackExchangeQuestion {

    @SerializedName("items")
    private List<Item> items = null;
    @SerializedName("has_more")
    private Boolean hasMore;
    @SerializedName("quota_max")
    private Integer quotaMax;
    @SerializedName("quota_remaining")
    private Integer quotaRemaining;

    public List<Item> getItems() {
        return items;
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
