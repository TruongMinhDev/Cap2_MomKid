package com.example.momkid.ui.baby;

import static android.content.ContentValues.TAG;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.example.momkid.MainActivity;
import com.example.momkid.R;
import com.example.momkid.helper.ResponseCommonDto;
import com.example.momkid.helper.SharedPreferenceHelper;
import com.example.momkid.helper.SystemConfig;
import com.example.momkid.ui.authentication.UserDto;
import com.example.momkid.ui.blog.BlogAdapter;
import com.example.momkid.ui.blog.BlogDto;
import com.example.momkid.ui.blog.BlogFragment;
import com.example.momkid.ui.profile.ProflieKidActivity;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BabyFragment extends Fragment {
    private RecyclerView rcvKid ;
    private ProgressDialog nDialog;

    private Button btnAddKid;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        loadData();
        View view = inflater.inflate(R.layout.fragment_list_kid,container,false);
        rcvKid = view.findViewById(R.id.rcvKid);
        rcvKid.setLayoutManager(new LinearLayoutManager(view.getContext()));


        nDialog = new ProgressDialog(getContext());
        nDialog.setMessage("Loading..");
        nDialog.setTitle("Get Data");
        nDialog.setIndeterminate(false);
        nDialog.setCancelable(true);
        nDialog.show();

        btnAddKid=view.findViewById(R.id.btn_add_kid);
        btnAddKid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToActivity(new Intent(getContext(), ProflieKidActivity.class));
            }
        });

        return view;
    }

    private void log(String mess){
        Log.d(BabyFragment.class.getName(), mess);
    }

    private void loadData() {
        log("da vao day");
        String id = String.valueOf(UserDto.getId());
        String token = SharedPreferenceHelper.getSharedPreferenceString(getContext(),"token","");
        AndroidNetworking.get(SystemConfig.BASE_URL.concat("/client/babies"))
                .addHeaders("Authorization", String.format("Bearer  %s",token))
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String json) {
                        nDialog.cancel();
                        List<BabyDto> babys = new ArrayList<>();
                        BabyDto temp = null;
                        GsonBuilder gson = new GsonBuilder();
                        Type collectionType = new TypeToken<ResponseCommonDto<BabyDto>>(){}.getType();
                        ResponseCommonDto<BabyDto> response = gson.create().fromJson(json, collectionType);
                        for (int i = 0; i<response.getData().size(); i ++){
                            temp=new BabyDto();
                            temp.setName(response.getData().get(i).getName());
                            temp.setBirthDay(response.getData().get(i).getBirthDay());
                            temp.setMale(response.getData().get(i).isMale());
                            temp.setUserId(response.getData().get(i).getUserId());
                            babys.add(temp);
                        }
                        BabyAdapter adapter = new BabyAdapter(babys, getContext());
                        rcvKid.setAdapter(adapter);
                    }
                    @Override
                    public void onError(ANError anError) {
                        log(anError.getErrorBody());
                    }
                });
    }

    private void navigateToActivity(Intent intent){
        startActivity(intent);
    }

}
