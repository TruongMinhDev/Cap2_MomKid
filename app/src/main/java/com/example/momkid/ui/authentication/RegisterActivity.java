package com.example.momkid.ui.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.momkid.R;

public class RegisterActivity extends AppCompatActivity {

    Button btnSignUp;
    EditText edtFirstName,edtLastName,edtEmail,edtPassword,edtPhone,edtRole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtFirstName=findViewById(R.id.edt_firstName);
        edtLastName=findViewById(R.id.edt_lastName);
        edtEmail=findViewById(R.id.edt_email);
        edtPassword=findViewById(R.id.edt_password);
        edtPhone=findViewById(R.id.edt_phone);
        edtRole=findViewById(R.id.edt_role);

        btnSignUp = findViewById(R.id.btn_signUp);



//        btn_registerDone.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
//            }
//        });
    }



}