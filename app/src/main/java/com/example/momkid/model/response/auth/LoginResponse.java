package com.example.momkid.model.response.auth;

import com.example.momkid.model.User;

public class LoginResponse {
    private User data;
    private String accessToken;
    private String refreshToken;
    private String message;

    public LoginResponse(User data, String accessToken, String refreshToken, String message) {
        this.data = data;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.message = message;
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
