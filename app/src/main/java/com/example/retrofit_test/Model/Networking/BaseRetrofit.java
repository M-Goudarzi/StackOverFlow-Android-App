package com.example.retrofit_test.Model.Networking;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseRetrofit {

    private static Retrofit retrofit;
    private static final String baseUrl = "https://api.stackexchange.com/2.3/";

    public StackExchangeApi getApi() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();
        }

        return retrofit.create(StackExchangeApi.class);
    }




}
