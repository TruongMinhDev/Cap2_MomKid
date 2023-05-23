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

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.momkid.R;
import com.example.momkid.helper.SharedPreferenceHelper;
import com.example.momkid.ui.baby.BabyDto;
import com.example.momkid.ui.baby.BabyFragment;
import com.example.momkid.utils.DateFormatTime;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    ImageSlider imageSlider;

    private View view;

    private TextView txt_birthdate,txt_height,txt_weight;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home,container,false);


        //Hiển thị quảng cáo
        imageSlider = view.findViewById(R.id.image_slider);
        ArrayList<SlideModel> imageList=new ArrayList<>();
        imageList.add(new SlideModel(R.drawable.imgone,null));
        imageList.add(new SlideModel(R.drawable.imgtwo,null));
        imageList.add(new SlideModel(R.drawable.imgthree,null));
        imageList.add(new SlideModel(R.drawable.imgfour,null));
        imageList.add(new SlideModel(R.drawable.imgfive,null));


        txt_birthdate=view.findViewById(R.id.txt_birthdate);
        txt_height = view.findViewById(R.id.txt_height);
        txt_weight = view.findViewById(R.id.txt_weight);

        Bundle bundle = getArguments();
        if(bundle != null){
            BabyDto babyDto = (BabyDto) bundle.get("object_baby");
            if(babyDto != null){
                txt_birthdate.setText(DateFormatTime.formatTime(babyDto.getBirthDate()));
                String age = DateFormatTime.formatTime(babyDto.getBirthDate());
                SharedPreferenceHelper.setSharedPreferenceString(getContext(),"birthDateKid",age);
                Integer babyId = babyDto.getId();
                SharedPreferenceHelper.setSharedPreferenceInt(getContext(),"babyId",babyId);
            }

        }

        imageSlider.setImageList(imageList);
        return view;

    }

}