package com.example.momkid.service;

import com.example.momkid.model.request.auth.LoginRequest;
import com.example.momkid.model.request.auth.RegisterRequest;
import com.example.momkid.model.response.auth.LoginResponse;
import com.example.momkid.model.response.auth.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("api/v1/auth/login")
    Call<LoginResponse> loginApi(@Body LoginRequest loginRequest);

    @FormUrlEncoded
    @POST("api/v1/auth/register")
    Call<RegisterResponse> registerApi(@Body RegisterRequest registerRequest);

}
