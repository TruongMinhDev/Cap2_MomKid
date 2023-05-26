package com.example.momkid.ui.blog;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.momkid.R;
import com.example.momkid.ui.book_doctor.DoctorDto;

public class BlogDetailFragment extends Fragment {
    View view;
    private TextView tvTitle,tvName,tvContent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_blog_detail, container, false);


        tvTitle=view.findViewById(R.id.post_detail_title);
        tvName=view.findViewById(R.id.post_name);
        tvContent=view.findViewById(R.id.post_detail_desc);

        Bundle bundle= getArguments();
        if(bundle != null){
            BlogDto blogDto = (BlogDto) bundle.get("object_blog");
            if(blogDto != null){
                tvTitle.setText(blogDto.getName());
                tvName.setText(blogDto.getNameUser());
                tvContent.setText(blogDto.getContent());
            }
        }



        return view;
    }
}