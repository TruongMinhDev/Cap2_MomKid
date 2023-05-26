package com.example.momkid.ui.schedule;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextClock;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
import com.example.momkid.service.IClickItemKid;
import com.example.momkid.service.IClickItemSchedule;
import com.example.momkid.ui.authentication.UserDto;
import com.example.momkid.ui.baby.BabyAdapter;
import com.example.momkid.ui.baby.BabyDto;
import com.example.momkid.ui.baby.BabyFragment;
import com.example.momkid.ui.home.HomeActivity;
import com.example.momkid.utils.DateFormatTime;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ScheduleFragment extends Fragment {


    private RecyclerView rcvSchedule;
    private ProgressDialog nDialog;

    private HomeActivity homeActivity;
    private TextClock textClock;


    private View view;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_schedule,container,false);

        homeActivity = (HomeActivity) getActivity();

        rcvSchedule=view.findViewById(R.id.rcvSchedule);
        rcvSchedule.setLayoutManager(new LinearLayoutManager(homeActivity));

        textClock=view.findViewById(R.id.clockTime);
        textClock.setFormat12Hour("E, dd-MM-yyyy");

        //check điều kiện số tháng tuổi
        String birthDate = SharedPreferenceHelper.getSharedPreferenceString(getContext(),"birthDateKid","1");
        int age = DateFormatTime.calculateAge(birthDate);
        if(age>0 && age <= 90){
            loadData("1");
            Toast.makeText(getContext(), "Áp dụng chuẩn EASY 2", Toast.LENGTH_SHORT).show();
        }else if(age>90 && age <= 210){
            loadData("2");
            Toast.makeText(getContext(), "Áp dụng chuẩn EASY 3", Toast.LENGTH_SHORT).show();
        }else if(age> 210 && age < 300){
            loadData("3");
            Toast.makeText(getContext(), "Áp dụng chuẩn EASY 2 3 4", Toast.LENGTH_SHORT).show();
        }else {
            loadData("4");
            Toast.makeText(getContext(), "Áp dụng chuẩn EASY 5 6", Toast.LENGTH_SHORT).show();
        }



        nDialog = new ProgressDialog(getContext());
        nDialog.setMessage("Loading..");
        nDialog.setTitle("Get Data");
        nDialog.setIndeterminate(false);
        nDialog.setCancelable(true);
        nDialog.show();



        return view;
    }

    private void loadData(String scheduleId) {
        log("da vao day lich trinh");

        String token = SharedPreferenceHelper.getSharedPreferenceString(getContext(),"token","");

        AndroidNetworking.get(SystemConfig.BASE_URL.concat("/client/suggest-schedule").concat("?filter=suggestScheduleCategoryId||$eq||{scheduleId}").concat("&sort=id,ASC"))
                .addHeaders("Authorization", String.format("Bearer  %s",token))
                .addPathParameter("scheduleId",scheduleId)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String json) {
                        nDialog.cancel();
                        List<ScheduleDto> scheduleDtos = new ArrayList<>();
                        ScheduleDto temp = null;
                        GsonBuilder gson = new GsonBuilder();
                        Type collectionType = new TypeToken<ResponseCommonDto<ScheduleDto>>(){}.getType();
                        ResponseCommonDto<ScheduleDto> response = gson.create().fromJson(json, collectionType);
                        for (int i = 0; i < response.getData().size(); i ++){
                            temp=new ScheduleDto();
                            temp.setBeginTime(response.getData().get(i).getBeginTime());
                            temp.setEndTime(response.getData().get(i).getEndTime());
                            temp.setContent(response.getData().get(i).getContent());
                            temp.setNote(response.getData().get(i).getNote());
                            scheduleDtos.add(temp);
                        }
                        ScheduleAdapter adapter = new ScheduleAdapter(scheduleDtos, new IClickItemSchedule() {
                            @Override
                            public void onClickItemSchedule(ScheduleDto scheduleDto) {

                            }
                        });

                        rcvSchedule.setAdapter(adapter);
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