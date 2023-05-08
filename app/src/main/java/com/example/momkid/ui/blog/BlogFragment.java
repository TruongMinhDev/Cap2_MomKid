package com.example.momkid.ui.blog;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.momkid.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class BlogFragment extends Fragment {
    private RecyclerView rcvBlogs ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        loadData();
        View view = inflater.inflate(R.layout.fragment_blog,container,false);
        rcvBlogs = view.findViewById(R.id.rcvBlog);
        rcvBlogs.setLayoutManager(new LinearLayoutManager(view.getContext()));

        ProgressDialog nDialog;
        nDialog = new ProgressDialog(getContext());
        nDialog.setMessage("Loading..");
        nDialog.setTitle("Get Data");
        nDialog.setIndeterminate(false);
        nDialog.setCancelable(true);
        nDialog.show();
        return view;
    }

    private void log(String mess){
        Log.d(BlogFragment.class.getName(), mess);
    }

    private void loadData() {
        log("da vao day");
        String url = "https://api.ipify.org?format=json";
        AndroidNetworking.get(url).build().getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                log(String.valueOf(response));

                List<BlogDto> blogs = new ArrayList<>();
                BlogDto temp = null;
                for (int i = 0; i < 3; i++) {
                    temp = new BlogDto();
                    temp.setContent(String.format("Content %d", i));
                    temp.setId(i);
                    temp.setName(String.format("Name %d", i));
                    blogs.add(temp);
                }
                BlogAdapter adapter = new BlogAdapter(blogs, getContext());
                rcvBlogs.setAdapter(adapter);
            }

            @Override
            public void onError(ANError anError) {
                log( String.valueOf(anError));
            }
        });
    }
}