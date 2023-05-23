package com.example.momkid.ui.book_doctor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.example.momkid.R;
import com.example.momkid.helper.ResponseCommonDto;
import com.example.momkid.helper.SharedPreferenceHelper;
import com.example.momkid.helper.SystemConfig;
import com.example.momkid.service.IClickItemDoctor;
import com.example.momkid.ui.baby.BabyDto;
import com.example.momkid.utils.DateFormatTime;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DoctorDetailFragment extends Fragment {

    private TextView txtTitle,txtName,txtAddress,txtTime,txtPhone,txtMedicalDegree;
    private Button btnBook;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.activity_profile_doctor,container,false);

       txtTitle=view.findViewById(R.id.txt_title);
       txtName=view.findViewById(R.id.txt_nameDoctor);
       txtAddress=view.findViewById(R.id.txt_address);
       txtPhone=view.findViewById(R.id.txt_phone);
       txtTime=view.findViewById(R.id.txt_time);
       txtMedicalDegree=view.findViewById(R.id.txt_medicalDegree);

        Bundle bundle = getArguments();
        if(bundle != null){
            DoctorDto doctorDto = (DoctorDto) bundle.get("object_doctor");
            if(doctorDto != null){
                txtTitle.setText(doctorDto.getRoomName());
                txtName.setText(doctorDto.getFirstName().concat(" ").concat(doctorDto.getLastName()));
                txtAddress.setText(doctorDto.getAddress());
                txtTime.setText(doctorDto.getBusinessHours());
                txtPhone.setText(doctorDto.getPhoneNumber());
                txtMedicalDegree.setText(doctorDto.getMedicalDegree());
            }

        }

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



       return view;
    }

//    private void updateStatusBook(){
//        String token = SharedPreferenceHelper.getSharedPreferenceString(getContext(),"token","");
//
//        AndroidNetworking.get(SystemConfig.BASE_URL.concat("/client/users").concat("?filter=role||$eq||{role}"))
//                .addHeaders("Authorization", String.format("Bearer  %s",token))
//                .addPathParameter("role","doctor")
//                .build()
//                .getAsString(new StringRequestListener() {
//                    @Override
//                    public void onResponse(String json) {
//                        nDialog.cancel();
//                        List<DoctorDto> doctorDtos = new ArrayList<>();
//                        DoctorDto temp = null;
//                        GsonBuilder gson = new GsonBuilder();
//                        Type collectionType = new TypeToken<ResponseCommonDto<DoctorDto>>(){}.getType();
//                        ResponseCommonDto<DoctorDto> response = gson.create().fromJson(json, collectionType);
//                        for (int i = 0; i < response.getData().size(); i ++){
//                            temp=new DoctorDto();
//                            temp.setFirstName(response.getData().get(i).getFirstName());
//                            temp.setLastName(response.getData().get(i).getLastName());
//                            temp.setPhoneNumber(response.getData().get(i).getPhoneNumber());
//                            temp.setAddress(response.getData().get(i).getAddress());
//                            temp.setImage(response.getData().get(i).getImage());
//                            temp.setRoomName(response.getData().get(i).getRoomName());
//                            temp.setBusinessHours(response.getData().get(i).getBusinessHours());
//                            temp.setMedicalDegree(response.getData().get(i).getMedicalDegree());
//
//                            doctorDtos.add(temp);
//                        }
//                        BookDoctorAdapter adapter = new BookDoctorAdapter(doctorDtos, new IClickItemDoctor() {
//                            @Override
//                            public void onClickItemDoctor(DoctorDto doctorDto) {
//                                homeActivity.goToDetailDoctor(doctorDto);
//                            }
//                        });
//
//                        rcvDoctor.setAdapter(adapter);
//                    }
//                    @Override
//                    public void onError(ANError anError) {
//                        log(anError.getErrorBody());
//                    }
//                });
//    }
//
//    private void replaceFragment(Fragment fragment){
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.content_frame,fragment);
//        transaction.commit();
//    }
}
