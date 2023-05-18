package com.example.momkid.ui.home;

import android.app.AlertDialog;
import android.app.Dialog;
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

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.momkid.R;
import com.example.momkid.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    ImageSlider imageSlider;

    private View view;

    private TextView txt_birthdate,txt_height,txt_weight;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home,container,false);

        imageSlider = view.findViewById(R.id.image_slider);

        ArrayList<SlideModel> imageList=new ArrayList<>();
//        imageList.add(new SlideModel("https://fastly.picsum.photos/id/20/200/300.jpg?hmac=DFzxrn8j8N0vtdAloI4hYTGWP-nNrDqMbVcWjF2BLwA", "description 1", null));imageList.add(new SlideModel("https://fastly.picsum.photos/id/20/200/300.jpg?hmac=DFzxrn8j8N0vtdAloI4hYTGWP-nNrDqMbVcWjF2BLwA", "description 1", null));
//        imageList.add(new SlideModel("https://fastly.picsum.photos/id/89/200/300.jpg?hmac=Mkt49bCquTyq1IBgwbLmaT43TeyQgXNig052fowQB1M", "description 1", null));imageList.add(new SlideModel("https://fastly.picsum.photos/id/20/200/300.jpg?hmac=DFzxrn8j8N0vtdAloI4hYTGWP-nNrDqMbVcWjF2BLwA", "description 2", null));
        imageList.add(new SlideModel(R.drawable.imgone,null));
        imageList.add(new SlideModel(R.drawable.imgtwo,null));
        imageList.add(new SlideModel(R.drawable.imgthree,null));
        imageList.add(new SlideModel(R.drawable.imgfour,null));
        imageList.add(new SlideModel(R.drawable.imgfive,null));

        imageSlider.setImageList(imageList);
        return view;

    }



}