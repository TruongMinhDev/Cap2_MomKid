package com.example.momkid.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.momkid.R;
import com.example.momkid.helper.SharedPreferenceHelper;
import com.example.momkid.ui.home.HomeActivity;
import com.example.momkid.utils.DateFormatTime;

public class ProfileBabyFragment extends Fragment {

    View view;
    private HomeActivity homeActivity;


    private TextView tvNameKid,tvBmi,tvAgeDay,tvNameMom,tvBirthDay,tvPhone,tvAddress;
    private Button btnListBaby;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile_baby,container,false);

        homeActivity= (HomeActivity) getActivity();


        tvNameKid=view.findViewById(R.id.tv_nameKid);
        tvBmi=view.findViewById(R.id.tv_bmi);
        tvAgeDay=view.findViewById(R.id.tv_timeDay);
        tvNameMom=view.findViewById(R.id.tv_nameMom);
        tvBirthDay=view.findViewById(R.id.tv_birthDay);
        tvPhone=view.findViewById(R.id.tv_phone);
        tvAddress=view.findViewById(R.id.tv_addressKid);

        //name kid
        String nameKid = SharedPreferenceHelper.getSharedPreferenceString(getContext(),"nameKid","");
        tvNameKid.setText(nameKid);
        //Số tháng tuổi
        String birthDate = SharedPreferenceHelper.getSharedPreferenceString(getContext(),"birthDateKid","1");
        int age = DateFormatTime.calculateAge(birthDate);
        tvAgeDay.setText(String.valueOf(age));
        //Ngày sinh
        tvBirthDay.setText(birthDate);
        //Tên mẹ bé
        String nameMom = SharedPreferenceHelper.getSharedPreferenceString(getContext(),"firstName","")
                .concat(" ")
                .concat(SharedPreferenceHelper.getSharedPreferenceString(getContext(),"lastName",""));
        tvNameMom.setText(nameMom);
        //phone
//        String phone = SharedPreferenceHelper.getSharedPreferenceString(getContext(),"phone","");
        tvPhone.setText("0783375499");
        //Address
//        String address = SharedPreferenceHelper.getSharedPreferenceString(getContext(),"address","");
        tvAddress.setText("195 Tôn Đản, Hòa An, Hòa Khánh Nam, Đà Nẵng");
        //bmi
        String bmi = SharedPreferenceHelper.getSharedPreferenceString(getContext(),"bmi","");
        tvBmi.setText(bmi);


        btnListBaby=view.findViewById(R.id.btn_listBaby);
        btnListBaby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeActivity.goToBabyFragment();
            }
        });

        return view;

    }
}
