package com.example.momkid.ui.book_doctor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.momkid.R;
import com.example.momkid.service.IClickItemDoctor;
import com.example.momkid.service.IClickItemKid;
import com.example.momkid.ui.baby.BabyAdapter;
import com.example.momkid.ui.baby.BabyDto;

import java.util.List;

public class BookDoctorAdapter extends RecyclerView.Adapter<BookDoctorAdapter.ViewHolder>{
    private List<DoctorDto> doctorDtos;

    private IClickItemDoctor clickItemDoctor;



    public BookDoctorAdapter(List<DoctorDto> doctorDtos, IClickItemDoctor clickItemDoctor) {
        this.doctorDtos = doctorDtos;
        this.clickItemDoctor = clickItemDoctor;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View doctorView = inflater.inflate(R.layout.list_item_doctor, parent, false);

        BookDoctorAdapter.ViewHolder viewHolder = new BookDoctorAdapter.ViewHolder(doctorView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DoctorDto doctorDto = doctorDtos.get(position);

        holder.tvClinicName.setText(doctorDto.getRoomName());
        holder.tvDoctorName.setText(doctorDto.getFirstName().concat(" ").concat(doctorDto.getLastName()));
        holder.tvAddress.setText(doctorDto.getAddress());

        holder.doctorItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickItemDoctor.onClickItemDoctor(doctorDto);
            }
        });

    }

    @Override
    public int getItemCount() {
        return doctorDtos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public RelativeLayout doctorItem;
        public TextView tvClinicName;
        public TextView tvDoctorName;
        public TextView tvAddress;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            doctorItem=itemView.findViewById(R.id.doctorItem);
            tvClinicName = itemView.findViewById(R.id.tv_clinicName);
            tvDoctorName = itemView.findViewById(R.id.tv_doctorName);
            tvAddress = itemView.findViewById(R.id.tv_address);
        }
    }
}
