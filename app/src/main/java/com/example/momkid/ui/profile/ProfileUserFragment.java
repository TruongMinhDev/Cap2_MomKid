package com.example.momkid.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.momkid.R;
import com.example.momkid.helper.SharedPreferenceHelper;

public class ProfileUserFragment extends Fragment {
    View view;

    private TextView tvNameMom,tvNameKid,tvEmail,tvPhone,tvAddress;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_profile_user,container,false);


        tvNameMom=view.findViewById(R.id.tv_nameMom);
        tvNameKid=view.findViewById(R.id.tv_nameKid);
        tvEmail=view.findViewById(R.id.tv_email);
        tvPhone=view.findViewById(R.id.tv_phone);
        tvAddress=view.findViewById(R.id.tv_address);


        String nameMom = SharedPreferenceHelper.getSharedPreferenceString(getContext(),"firstName","")
                .concat(" ")
                .concat(SharedPreferenceHelper.getSharedPreferenceString(getContext(),"lastName",""));
        tvNameMom.setText(nameMom);

        String nameKid = SharedPreferenceHelper.getSharedPreferenceString(getContext(),"nameKid","");
        tvNameKid.setText(nameKid);

        String email = SharedPreferenceHelper.getSharedPreferenceString(getContext(),"email","");
        tvEmail.setText(email);

        //phone
//        String phone = SharedPreferenceHelper.getSharedPreferenceString(getContext(),"phone","");
        tvPhone.setText("0783375499");
        //Address
//        String address = SharedPreferenceHelper.getSharedPreferenceString(getContext(),"address","");
        tvAddress.setText("195 Tôn Đản, Hòa An, Hòa Khánh Nam, Đà Nẵng");


        return view;

    }
}
