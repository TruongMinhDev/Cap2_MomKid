package com.example.momkid.ui.schedule;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.momkid.R;
import com.example.momkid.databinding.FragmentScheduleBinding;

import java.util.ArrayList;

public class ScheduleFragment extends Fragment {

    private ListView lvSchedule;
    private ArrayList<ScheduleViewModel> arrSchedule;
    private ScheduleAdapter scheduleAdapter;

    private View view;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_schedule,container,false);

        lvSchedule=(ListView) view.findViewById(R.id.lv_schedule);
        arrSchedule= new ArrayList<>();
        arrSchedule.add(new ScheduleViewModel("7:00","Cho bé ăn sáng"));
        arrSchedule.add(new ScheduleViewModel("8:00","Cho bé ngủ"));

        scheduleAdapter=new ScheduleAdapter(getContext(),R.layout.item_schedule,arrSchedule);
        lvSchedule.setAdapter(scheduleAdapter);




        return view;
    }
}