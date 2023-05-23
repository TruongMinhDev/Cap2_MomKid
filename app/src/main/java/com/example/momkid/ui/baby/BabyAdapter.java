package com.example.momkid.ui.baby;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.momkid.R;
import com.example.momkid.service.IClickItemKid;
import com.example.momkid.utils.DateFormatTime;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BabyAdapter extends RecyclerView.Adapter<BabyAdapter.ViewHolder>{

    private List<BabyDto> mKids;
    private IClickItemKid clickItem;

    public BabyAdapter(List<BabyDto> mKids, IClickItemKid clickItem) {
        this.mKids = mKids;
        this.clickItem = clickItem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View babyView = inflater.inflate(R.layout.item_kid, parent, false);

        BabyAdapter.ViewHolder viewHolder = new BabyAdapter.ViewHolder(babyView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BabyDto baby = mKids.get(position);
        holder.tvName.setText(baby.getName());

        holder.tvBirtDay.setText(DateFormatTime.formatTime(baby.getBirthDate()));

        if(baby.isMale()==true){
            holder.tvSex.setText("Bé Trai");
        }else holder.tvSex.setText("Bé Gái");

        holder.kidItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickItem.onClickItemBaby(baby);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mKids.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public RelativeLayout kidItem;
        public TextView tvName;
        public TextView tvBirtDay;
        public TextView tvSex;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            kidItem=itemView.findViewById(R.id.kidItem);
            tvName = itemView.findViewById(R.id.tvName);
            tvBirtDay = itemView.findViewById(R.id.tvBirtDay);
            tvSex = itemView.findViewById(R.id.tvSex);
        }
    }


}
