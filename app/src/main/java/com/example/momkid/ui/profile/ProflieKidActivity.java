package com.example.momkid.ui.profile;

import static java.lang.Math.log;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.example.momkid.R;
import com.example.momkid.helper.ResponseCommonDto;
import com.example.momkid.helper.SharedPreferenceHelper;
import com.example.momkid.helper.SystemConfig;
import com.example.momkid.ui.authentication.LoginActivity;
import com.example.momkid.ui.authentication.UserDto;
import com.example.momkid.ui.baby.BabyAdapter;
import com.example.momkid.ui.baby.BabyDto;
import com.example.momkid.ui.baby.BabyFragment;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ProflieKidActivity extends AppCompatActivity {

    public static final String TAG = ProflieKidActivity.class.getName();

    EditText edt_name_kid,edt_birthdate,edt_height,edt_weight;
    RadioButton rbt_female,rbt_male;
    private ProgressDialog nDialog;

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
                loadData();
            }
        });


        nDialog = new ProgressDialog(this);
        nDialog.setMessage("Loading..");
        nDialog.setTitle("Get Data");
        nDialog.setIndeterminate(false);
        nDialog.setCancelable(true);
        nDialog.show();

    }

    private void loadData(String name, String birthDate) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("firstname", "Rohit");
            jsonObject.put("lastname", "Kumar");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String token = SharedPreferenceHelper.getSharedPreferenceString(this,"token","");
        AndroidNetworking.post(SystemConfig.BASE_URL.concat("/client/babies"))
                .addHeaders("Authorization", String.format("Bearer  %s",token))
                .addJSONObjectBody(jsonObject)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String json) {
                        nDialog.cancel();
                        Toast.makeText(ProflieKidActivity.this,"Bạn đã thêm bé thành công",Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onError(ANError anError) {
                        log(anError.getErrorBody());
                    }
                });
    }

    private void log(String mess){
        Log.d(TAG, mess);
    }

}