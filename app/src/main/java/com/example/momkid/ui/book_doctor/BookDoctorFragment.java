package com.example.momkid.ui.book_doctor;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.example.momkid.R;
import com.example.momkid.helper.ResponseCommonDto;
import com.example.momkid.helper.SharedPreferenceHelper;
import com.example.momkid.helper.SystemConfig;
import com.example.momkid.service.IClickItemDoctor;
import com.example.momkid.service.IClickItemKid;
import com.example.momkid.ui.baby.BabyAdapter;
import com.example.momkid.ui.baby.BabyDto;
import com.example.momkid.ui.baby.BabyFragment;
import com.example.momkid.ui.home.HomeActivity;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BookDoctorFragment extends Fragment {
    private RecyclerView rcvDoctor ;
    private ProgressDialog nDialog;
    private HomeActivity homeActivity;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_doctor,container,false);

        homeActivity = (HomeActivity) getActivity();


        rcvDoctor = view.findViewById(R.id.rcvDoctor);
        rcvDoctor.setLayoutManager(new LinearLayoutManager(homeActivity));

        loadData();

        nDialog = new ProgressDialog(getContext());
        nDialog.setMessage("Loading..");
        nDialog.setTitle("Get Data");
        nDialog.setIndeterminate(false);
        nDialog.setCancelable(true);
        nDialog.show();

        return view;
    }

    private void loadData() {
        String token = SharedPreferenceHelper.getSharedPreferenceString(getContext(),"token","");

        AndroidNetworking.get(SystemConfig.BASE_URL.concat("/client/users").concat("?filter=role||$eq||{role}"))
                .addHeaders("Authorization", String.format("Bearer  %s",token))
                .addPathParameter("role","doctor")
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String json) {
                        nDialog.cancel();
                        List<DoctorDto> doctorDtos = new ArrayList<>();
                        DoctorDto temp = null;
                        GsonBuilder gson = new GsonBuilder();
                        Type collectionType = new TypeToken<ResponseCommonDto<DoctorDto>>(){}.getType();
                        ResponseCommonDto<DoctorDto> response = gson.create().fromJson(json, collectionType);
                        for (int i = 0; i < response.getData().size(); i ++){
                            temp=new DoctorDto();
                            temp.setFirstName(response.getData().get(i).getFirstName());
                            temp.setLastName(response.getData().get(i).getLastName());
                            temp.setPhoneNumber(response.getData().get(i).getPhoneNumber());
                            temp.setAddress(response.getData().get(i).getAddress());
                            temp.setImage(response.getData().get(i).getImage());
                            temp.setRoomName(response.getData().get(i).getRoomName());
                            temp.setBusinessHours(response.getData().get(i).getBusinessHours());
                            temp.setMedicalDegree(response.getData().get(i).getMedicalDegree());

                            doctorDtos.add(temp);
                        }
                        BookDoctorAdapter adapter = new BookDoctorAdapter(doctorDtos, new IClickItemDoctor() {
                            @Override
                            public void onClickItemDoctor(DoctorDto doctorDto) {
                                homeActivity.goToDetailDoctor(doctorDto);
                            }
                        });

                        rcvDoctor.setAdapter(adapter);
                    }
                    @Override
                    public void onError(ANError anError) {
                        log(anError.getErrorBody());
                    }
                });
    }
    private void log(String mess){
        Log.d(BabyFragment.class.getName(), mess);
    }

}
