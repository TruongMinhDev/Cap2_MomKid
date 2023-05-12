package com.example.momkid.ui.schedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.momkid.R;

import java.util.List;

public class ScheduleAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<ScheduleDto> scheduleList;

    public ScheduleAdapter(Context context, int layout, List<ScheduleDto> scheduleList) {
        this.context = context;
        this.layout = layout;
        this.scheduleList = scheduleList;
    }

    @Override
    public int getCount() {
        return scheduleList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView txtTime,txtContent;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(layout,null);
            holder = new ViewHolder();
            //ánh xạ view
            holder.txtTime=convertView.findViewById(R.id.txt_time);
            holder.txtContent=convertView.findViewById(R.id.txt_content);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //gán giá trị
        ScheduleDto scheduleModel = scheduleList.get(position);
        holder.txtTime.setText(scheduleModel.getTimeSchedule());
        holder.txtContent.setText(scheduleModel.getContentSchedule());

        return convertView;
    }
}
