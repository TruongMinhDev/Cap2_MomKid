package com.example.momkid.ui.baby;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.momkid.R;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class BabyAdapter extends RecyclerView.Adapter<BabyAdapter.ViewHolder>{

    private List<BabyDto> mKids;
    private Context context;


    public BabyAdapter(List<BabyDto> mKids, Context context) {
        this.mKids = mKids;
        this.context = context;
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
        holder.tvBirtDay.setText(baby.getBirthDay());
        if(baby.isMale()==true){
            holder.tvSex.setText("Bé Trai");
        }else holder.tvSex.setText("Bé Gái");


    }

    @Override
    public int getItemCount() {
        return mKids.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public TextView tvBirtDay;
        public TextView tvSex;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvBirtDay = itemView.findViewById(R.id.tvBirtDay);
            tvSex = itemView.findViewById(R.id.tvSex);
        }
    }
}
