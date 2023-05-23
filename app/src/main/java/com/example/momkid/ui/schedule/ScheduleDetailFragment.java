package com.example.momkid.ui.schedule;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.momkid.R;
import com.example.momkid.helper.SharedPreferenceHelper;
import com.example.momkid.ui.baby.BabyDto;
import com.example.momkid.utils.DateFormatTime;

public class ScheduleDetailFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_kid,container,false);




        Bundle bundle = getArguments();
        if(bundle != null){
            ScheduleDto scheduleDto = (ScheduleDto) bundle.get("object_schedule");
            if(scheduleDto != null){

            }

        }

        return view;
    }
}
