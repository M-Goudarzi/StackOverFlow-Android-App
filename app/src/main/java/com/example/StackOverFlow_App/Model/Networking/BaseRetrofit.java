package com.example.StackOverFlow_App.Model.Networking;

import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseRetrofit {

    private static Retrofit retrofit;
    private static final String baseUrl = "https://api.stackexchange.com/2.3/";

    public StackExchangeApi getApi() {
        if (retrofit == null) {
            GsonBuilder builder = new GsonBuilder();
            builder.serializeNulls();
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(builder.create()))
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();
        }

        return retrofit.create(StackExchangeApi.class);
    }




}
