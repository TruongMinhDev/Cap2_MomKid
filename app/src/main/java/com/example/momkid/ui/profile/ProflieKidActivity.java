package com.example.momkid.ui.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.momkid.R;

public class ProflieKidActivity extends AppCompatActivity {

    EditText edt_name_kid,edt_birthdate,edt_height,edt_weight;
    RadioButton rbt_female,rbt_male;

    Button btn_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proflie_kid);

        edt_name_kid=findViewById(R.id.edt_namekid);
        edt_birthdate=findViewById(R.id.edt_birthdate);
        edt_height=findViewById(R.id.edt_height);
        edt_weight=findViewById(R.id.edt_weight);

        rbt_female=findViewById(R.id.btn_sex_female);
        rbt_male=findViewById(R.id.btn_sex_male);

        btn_confirm=findViewById(R.id.btn_confirm);

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                senDataToHomePage();
            }
        });


    }

    private void senDataToHomePage() {
    }
}