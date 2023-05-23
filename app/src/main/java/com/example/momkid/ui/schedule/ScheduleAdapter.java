package com.example.momkid.ui.schedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.momkid.R;
import com.example.momkid.service.IClickItemSchedule;
import com.example.momkid.ui.bmi.BmiAdapter;
import com.example.momkid.ui.bmi.BmiDto;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {

    private List<ScheduleDto> scheduleList;
    private IClickItemSchedule clickItemSchedule;

    public ScheduleAdapter(List<ScheduleDto> scheduleList, IClickItemSchedule clickItemSchedule) {
        this.scheduleList = scheduleList;
        this.clickItemSchedule=clickItemSchedule;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_schedule,parent,false);
        ScheduleAdapter.ViewHolder viewHolder = new ScheduleAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ScheduleDto scheduleDto = scheduleList.get(position);
        holder.tvBeginTime.setText(scheduleDto.getBeginTime());
        holder.tvEndTime.setText(scheduleDto.getEndTime());
        holder.tvContent.setText(scheduleDto.getContent());
        holder.tvNote.setText(scheduleDto.getNote());

        holder.itemSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickItemSchedule.onClickItemSchedule(scheduleDto);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(scheduleList != null){
            return scheduleList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout itemSchedule;
        public TextView tvBeginTime;
        public TextView tvEndTime;
        public TextView tvContent;
        public TextView tvNote;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemSchedule=itemView.findViewById(R.id.itemSchedule);
            tvBeginTime=itemView.findViewById(R.id.tv_beginTime);
            tvEndTime=itemView.findViewById(R.id.tv_endTime);
            tvContent=itemView.findViewById(R.id.tv_content);
            tvNote=itemView.findViewById(R.id.tv_note);

        }
    }
}
