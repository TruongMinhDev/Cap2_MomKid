package com.example.momkid.ui.home;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.momkid.R;
import com.example.momkid.databinding.FragmentHomeBinding;
import com.example.momkid.ui.baby.BabyDto;
import com.example.momkid.ui.baby.BabyFragment;

public class HomeFragment extends Fragment {
    private View view;

    private TextView txt_birthdate,txt_height,txt_weight;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home,container,false);

        txt_birthdate=view.findViewById(R.id.txt_birthdate);
        txt_height = view.findViewById(R.id.txt_height);
        txt_weight = view.findViewById(R.id.txt_weight);

        Bundle bundle = getArguments();
        if(bundle != null){
            BabyDto babyDto = (BabyDto) bundle.get("object_baby");
            if(babyDto != null){
                txt_birthdate.setText(babyDto.getBirthDay().toString());
            }

        }




        return view;

    }



}