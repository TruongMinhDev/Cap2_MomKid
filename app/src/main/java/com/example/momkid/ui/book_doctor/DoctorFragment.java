package com.example.momkid.ui.book_doctor;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.momkid.R;


public class DoctorFragment extends Fragment {

    View view;
    private TextView txtTitle,txtName,txtAddress,txtTime,txtPhone,txtMedicalDegree;
    private Button btnBook;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_doctor, container, false);

        txtTitle=view.findViewById(R.id.txt_title);
        txtName=view.findViewById(R.id.txt_nameDoctor);
        txtAddress=view.findViewById(R.id.txt_address);
        txtPhone=view.findViewById(R.id.txt_phone);
        txtTime=view.findViewById(R.id.txt_time);
        txtMedicalDegree=view.findViewById(R.id.txt_medicalDegree);
        btnBook=view.findViewById(R.id.btn_bookDoctor);

        Bundle bundle= getArguments();
        if(bundle != null){
            DoctorDto doctorDto = (DoctorDto) bundle.get("object_doctor");
            if(doctorDto != null){
                txtTitle.setText(doctorDto.getRoomName());
                txtName.setText(doctorDto.getFirstName().concat(" ").concat(doctorDto.getLastName()));
                txtAddress.setText(doctorDto.getAddress());
                txtTime.setText(doctorDto.getBusinessHours());
                txtPhone.setText(doctorDto.getPhoneNumber());
                txtMedicalDegree.setText(doctorDto.getMedicalDegree());
            }
        }


        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }
}