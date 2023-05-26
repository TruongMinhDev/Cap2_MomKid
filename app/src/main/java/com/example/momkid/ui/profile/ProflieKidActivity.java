package com.example.momkid.ui.profile;

import static java.lang.Math.log;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
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
import com.example.momkid.ui.bmi.BmiFragment;
import com.example.momkid.utils.DateFormatTime;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ProflieKidActivity extends AppCompatActivity {

    public static final String TAG = ProflieKidActivity.class.getName();

    EditText edt_name_kid,edt_birthdate,edt_height,edt_weight;
    RadioButton rbtFemale,rbtMale;
    private ProgressDialog nDialog;

    Button btn_confirm;

    Boolean sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proflie_kid);

        edt_name_kid=findViewById(R.id.edt_namekid);
        edt_birthdate=findViewById(R.id.edt_birthdate);

        rbtFemale=findViewById(R.id.rbt_female);
        rbtMale=findViewById(R.id.rbt_male);

        btn_confirm=findViewById(R.id.btn_confirm);

        rbtFemale.setOnCheckedChangeListener(listener);
        rbtMale.setOnCheckedChangeListener(listener);
        edt_birthdate.addTextChangedListener(new TextWatcher() {
            private String current = "";
            private String ddmmyyyy = "DDMMYYYY";
            private Calendar cal = Calendar.getInstance();
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current)) {
                    String clean = s.toString().replaceAll("[^\\d.]", "");
                    String cleanC = current.replaceAll("[^\\d.]", "");
                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }
                    //Fix for pressing delete next to a forward slash
                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8){
                        clean = clean + ddmmyyyy.substring(clean.length());
                    }else{
                        //This part makes sure that when we finish entering numbers
                        //the date is correct, fixing it otherwise
                        int day  = Integer.parseInt(clean.substring(0,2));
                        int mon  = Integer.parseInt(clean.substring(2,4));
                        int year = Integer.parseInt(clean.substring(4,8));
                        if(mon > 12) mon = 12;
                        cal.set(Calendar.MONTH, mon-1);
                        year = (year<1900)?1900:(year>2100)?2100:year;
                        cal.set(Calendar.YEAR, year);
                        // ^ first set year for the line below to work correctly
                        //with leap years - otherwise, date e.g. 29/02/2012
                        //would be automatically corrected to 28/02/2012

                        day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                        clean = String.format("%02d%02d%02d",day, mon, year);
                    }
                    clean = String.format("%s-%s-%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = sel < 0 ? 0 : sel;
                    current = clean;
                    edt_birthdate.setText(current);
                    edt_birthdate.setSelection(sel < current.length() ? sel : current.length());
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable s) {}
        });

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edt_name_kid.getText().toString();
                String birthDate= edt_birthdate.getText().toString();
                if(rbtFemale.isChecked()){
                    sex=false;
                } else if (rbtMale.isChecked()) {
                    sex=true;
                }
                nDialog = new ProgressDialog(ProflieKidActivity.this);
                nDialog.setMessage("Loading..");
                nDialog.setTitle("Get Data");
                nDialog.setIndeterminate(false);
                nDialog.setCancelable(true);
                nDialog.show();

                addData(name,birthDate,sex);

            }
        });
    }

    CompoundButton.OnCheckedChangeListener listener= new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        }
    };


    private void addData(String name, String birthDate, Boolean sex) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", name);
            jsonObject.put("birthDate", birthDate);
            jsonObject.put("isMale",sex);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String token = SharedPreferenceHelper.getSharedPreferenceString(this,"token","");
        AndroidNetworking.post(SystemConfig.BASE_URL.concat("/client/babies"))
                .addHeaders("Authorization", String.format("Bearer  %s",token))
                .addJSONObjectBody(jsonObject)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        nDialog.cancel();
                        log("ok");
                        try {
                            String birthDateKid = response.getString("birthDate");
                            SharedPreferenceHelper.setSharedPreferenceString(ProflieKidActivity.this,"birthDateKid", DateFormatTime.formatTime(birthDateKid));
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                        try {
                            Integer babyId= Integer.valueOf(response.getString("id"));
                            SharedPreferenceHelper.setSharedPreferenceInt(ProflieKidActivity.this,"babyId",babyId);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            String nameKid=response.getString("name");
                            SharedPreferenceHelper.setSharedPreferenceString(ProflieKidActivity.this,"nameKid",nameKid);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        AlertDialog.Builder builder = new AlertDialog.Builder(ProflieKidActivity.this);
                        builder.setMessage("Tiếp theo sẽ cập nhật chỉ số BMI cho bé nào :3 ");
                        builder.setCancelable(false);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // code khi người dùng nhấn nút OK
//                                replaceFragment(new BmiFragment());
                                ProflieKidActivity.this.finish();
                            }
                        });
                        AlertDialog alert = builder.create();
                        alert.show();

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
    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame,fragment);
        transaction.commit();
    }

}