package com.example.momkid;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.momkid.helper.SharedPreferenceHelper;
import com.example.momkid.ui.authentication.LoginActivity;
import com.example.momkid.ui.home.HomeActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String token = SharedPreferenceHelper.getSharedPreferenceString(this, "token", "");
        if (!"".equals(token)){
            navigateToActivity( new Intent(MainActivity.this, HomeActivity.class));
        } else {
            navigateToActivity( new Intent(MainActivity.this, LoginActivity.class));
        }
    }

    private void navigateToActivity(Intent intent){
        startActivity(intent);
    }
}