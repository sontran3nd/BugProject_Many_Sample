package com.example.admin.bugproject.Services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Admin on 8/31/2017.
 */

public class RestClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl) {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getStringClient(String baseUrl){
        if (retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(baseUrl).build();
        }
        return retrofit;
    }
}
