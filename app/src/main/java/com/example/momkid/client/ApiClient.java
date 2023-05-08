package com.example.momkid.client;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    //lick: https://72c3-118-69-176-254.ngrok-free.app/swagger
    public static Retrofit retrofit;

    public static Retrofit getRetrofitCallApi(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://72c3-118-69-176-254.ngrok-free.app/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }
}
