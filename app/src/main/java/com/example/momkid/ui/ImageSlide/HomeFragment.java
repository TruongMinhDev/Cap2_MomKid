package com.example.momkid.ui.ImageSlide;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.momkid.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    ImageSlider imageSlider;
    public HomeFragment ()
    {

    }
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View view=inflater.inflate(R.layout.fragment_home,container,false);

        imageSlider = view.findViewById(R.id.image_slider);

        ArrayList<SlideModel> imageList=new ArrayList<>();
        imageList.add(new SlideModel("https://fastly.picsum.photos/id/20/200/300.jpg?hmac=DFzxrn8j8N0vtdAloI4hYTGWP-nNrDqMbVcWjF2BLwA", "description 1", null));imageList.add(new SlideModel("https://fastly.picsum.photos/id/20/200/300.jpg?hmac=DFzxrn8j8N0vtdAloI4hYTGWP-nNrDqMbVcWjF2BLwA", "description 1", null));
        imageList.add(new SlideModel("https://fastly.picsum.photos/id/89/200/300.jpg?hmac=Mkt49bCquTyq1IBgwbLmaT43TeyQgXNig052fowQB1M", "description 1", null));imageList.add(new SlideModel("https://fastly.picsum.photos/id/20/200/300.jpg?hmac=DFzxrn8j8N0vtdAloI4hYTGWP-nNrDqMbVcWjF2BLwA", "description 2", null));

        return view;
    }
}
