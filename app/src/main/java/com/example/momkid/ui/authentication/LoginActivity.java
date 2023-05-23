package com.example.momkid.ui.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = LoginActivity.class.getName();
    Button btn_login,btn_register;
    EditText edtEmail,edtPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString();
                String pass = edtPassword.getText().toString();
                if (isEmailValid(email)) {
                    login(email, pass);
                } else {
                    edtEmail.setError("Email không hợp lệ!");
                    edtEmail.requestFocus();
                }

            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean isEmailValid(CharSequence email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    private void login(String userName, String password) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", userName);
            jsonObject.put("password", password);
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), "Tài khoản hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        AndroidNetworking.post(SystemConfig.BASE_URL.concat("/auth/login"))
                .addJSONObjectBody(jsonObject) // posting json
                .build()
                .getAsJSONObject(new JSONObjectRequestListener(){
                    @Override
                    public void onResponse(JSONObject response) {
                        log(String.valueOf(response));
                        try {
                            String token = response.getString("accessToken");
                            SharedPreferenceHelper.setSharedPreferenceString(LoginActivity.this,"token",token);

                            //userId
                            Integer userId = response.getJSONObject("data").get("id").hashCode();
                            SharedPreferenceHelper.setSharedPreferenceInt(LoginActivity.this,"userId",userId);

                            //first name
                            String firstName = response.getJSONObject("data").get("firstName").toString();
                            SharedPreferenceHelper.setSharedPreferenceString(LoginActivity.this,"firstName",firstName);

                            //last name
                            String lastName = response.getJSONObject("data").get("lastName").toString();
                            SharedPreferenceHelper.setSharedPreferenceString(LoginActivity.this,"lastName",lastName);

                            //email
                            String email = response.getJSONObject("data").get("email").toString();
                            SharedPreferenceHelper.setSharedPreferenceString(LoginActivity.this,"email",email);

                            //role
                            String role = response.getJSONObject("data").get("role").toString();
                            SharedPreferenceHelper.setSharedPreferenceString(LoginActivity.this,"role",role);

                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                        } catch (JSONException e) {

                            throw new RuntimeException(e);
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(getApplicationContext(), "Tài khoản hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                        log(String.valueOf(anError));
                    }
                });
    }

    private void log(String mess){
        Log.d(TAG,mess);
    }

    public void getDataUser(JSONObject response) throws JSONException {
        UserDto.setId(response.getJSONObject("data").get("id").hashCode());
        UserDto.setFirstName(response.getJSONObject("data").get("firstName").toString());
        UserDto.setLastName(response.getJSONObject("data").get("lastName").toString());
        UserDto.setEmail(response.getJSONObject("data").get("email").toString());
        UserDto.setRole(response.getJSONObject("data").get("role").toString());
    }
}