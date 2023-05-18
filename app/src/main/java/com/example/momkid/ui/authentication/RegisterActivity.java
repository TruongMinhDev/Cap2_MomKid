package com.example.momkid.ui.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.momkid.R;
import com.example.momkid.helper.SharedPreferenceHelper;
import com.example.momkid.helper.SystemConfig;
import com.example.momkid.ui.home.HomeActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    Button btnSignUp;
    EditText edtFirstName,edtLastName,edtEmail,edtPassword,edtConfirmPassword,edtPhone,edtRole;

    public static final String TAG = RegisterActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtFirstName=findViewById(R.id.edt_firstName);
        edtLastName=findViewById(R.id.edt_lastName);
        edtEmail=findViewById(R.id.edt_email);
        edtPassword=findViewById(R.id.edt_password);
        edtConfirmPassword = findViewById(R.id.edt_password_confirm);
        edtPhone=findViewById(R.id.edt_phone);
//        edtRole=findViewById(R.id.edt_role);

        btnSignUp = findViewById(R.id.btn_signUp);



        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = edtFirstName.getText().toString();
                String lastName = edtLastName.getText().toString();
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                String confirmPassword = edtConfirmPassword.getText().toString();
                String phoneNumber = edtPhone.getText().toString();
                String role = "mom";
                if (TextUtils.isEmpty(firstName)){
                    Toast.makeText(RegisterActivity.this,"Vui lòng nhập họ",Toast.LENGTH_LONG).show();
                    edtFirstName.setError("Họ không được để trống");
                    edtFirstName.requestFocus();
                }else if(TextUtils.isEmpty(lastName)){
                    Toast.makeText(RegisterActivity.this,"Vui lòng nhập tên",Toast.LENGTH_LONG).show();
                    edtLastName.setError("Tên không được để trống");
                    edtLastName.requestFocus();
                }else if (TextUtils.isEmpty(email)) {
                    Toast.makeText(RegisterActivity.this,"Vui lòng nhập Email!",Toast.LENGTH_LONG).show();
                    edtEmail.setError("Email không được để trống!");
                    edtEmail.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(RegisterActivity.this,"Vui lòng nhập email!",Toast.LENGTH_LONG).show();
                    edtEmail.setError("Email không đúng định dạng!");
                    edtEmail.requestFocus();
                }
                else if (TextUtils.isEmpty(password)){
                    Toast.makeText(RegisterActivity.this,"Vui lòng nhập mật khẩu",Toast.LENGTH_LONG).show();
                    edtPassword.setError("Mật khẩu không được để trống");
                    edtPassword.requestFocus();
                } else if (password.length()<8) {
                    Toast.makeText(RegisterActivity.this,"Mật khẩu phải dài hơn 8 kí tự",Toast.LENGTH_LONG).show();
                    edtPassword.setError("Mật khẩu chưa đủ mạnh!");
                    edtPassword.requestFocus();
                } else if (TextUtils.isEmpty(confirmPassword)) {
                    Toast.makeText(RegisterActivity.this,"Vui lòng nhập lại mật khẩu!",Toast.LENGTH_LONG).show();
                    edtConfirmPassword.setError("Mật khẩu không được để trống!");
                    edtConfirmPassword.requestFocus();
                } else if (!password.equals(confirmPassword)) {
                    Toast.makeText(RegisterActivity.this,"Vui lòng nhập trùng mật khẩu!",Toast.LENGTH_LONG).show();
                    edtConfirmPassword.setError("Xác nhận mật khẩu không được bỏ trống!");
                    edtConfirmPassword.requestFocus();
                    edtPassword.clearComposingText();
                    edtConfirmPassword.clearComposingText();
                }  else if (TextUtils.isEmpty(phoneNumber)) {
                    Toast.makeText(RegisterActivity.this,"Vui lòng nhập số điện thoại!",Toast.LENGTH_LONG).show();
                    edtPhone.setError("Số điện thoại không được để trống!");
                    edtPhone.requestFocus();
                } else if (phoneNumber.length()!=10) {
                    Toast.makeText(RegisterActivity.this,"Vui lòng nhập số điện thoại!",Toast.LENGTH_LONG).show();
                    edtPhone.setError("Số điện thoại phải có 10 số!");
                    edtPhone.requestFocus();
                }
                signUp(firstName,lastName,email,password,phoneNumber,role);
            }
        });
    }
    private void signUp(String firstName, String lastName, String email, String password,String phoneNumber, String role) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("firstName", firstName);
            jsonObject.put("lastName", lastName);
            jsonObject.put("email", email);
            jsonObject.put("password", password);
            jsonObject.put("phoneNumber", phoneNumber);
            jsonObject.put("role", role);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        AndroidNetworking.post(SystemConfig.BASE_URL.concat("/auth/register"))
                .addJSONObjectBody(jsonObject) // posting json
                .build()
                .getAsJSONObject(new JSONObjectRequestListener(){
                    @Override
                    public void onResponse(JSONObject response) {
                        log("ok");
                        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(intent);

                    }
                    @Override
                    public void onError(ANError anError) {
                        log(String.valueOf(anError));
                    }
                });
    }

    private void log(String mess){
        Log.d(TAG,mess);
    }


}