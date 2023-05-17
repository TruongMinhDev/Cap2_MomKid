package com.example.momkid.ui.bmi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.momkid.R;
import com.example.momkid.ui.baby.BabyAdapter;

import java.util.List;


public class BmiAdapter extends RecyclerView.Adapter<BmiAdapter.ViewHolder>{

    private List<BmiDto> bmiDtos;


    public BmiAdapter(List<BmiDto> bmiDtos) {
        this.bmiDtos = bmiDtos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bmi,parent,false);
        BmiAdapter.ViewHolder viewHolder = new BmiAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BmiAdapter.ViewHolder holder, int position) {
        BmiDto bmiDto = bmiDtos.get(position);
        if(bmiDto == null){
            return;
        }
        holder.
    }

    @Override
    public int getItemCount() {
        if(bmiDtos != null){
            return bmiDtos.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
