package com.example.momkid;

import android.app.Application;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;

public class MyApplication extends Application {

    public static final String TAG = MyApplication.class.getName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        AndroidNetworking.initialize(getApplicationContext());
    }

}
