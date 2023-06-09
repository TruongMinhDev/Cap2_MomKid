package com.example.momkid;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import com.example.momkid.chatgpt.ChatGPTActivity;

import androidx.appcompat.app.AppCompatActivity;



import androidx.appcompat.app.AppCompatActivity;

import com.example.momkid.helper.SharedPreferenceHelper;
import com.example.momkid.ui.authentication.LoginActivity;
import com.example.momkid.ui.home.HomeActivity;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button nextApp = findViewById(R.id.btn_nextApp);
        nextApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String token = SharedPreferenceHelper.getSharedPreferenceString(MainActivity.this, "token", "");
                if (!"".equals(token)){
                    navigateToActivity( new Intent(MainActivity.this, HomeActivity.class));
                } else {
                    navigateToActivity( new Intent(MainActivity.this, LoginActivity.class));
                }
            }
        });

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