package com.example.momkid.ui.blog;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.example.momkid.R;
import com.example.momkid.helper.ResponseCommonDto;
import com.example.momkid.helper.SharedPreferenceHelper;
import com.example.momkid.helper.SystemConfig;
import com.example.momkid.service.IClickItemBlog;
import com.example.momkid.ui.baby.BabyAdapter;
import com.example.momkid.ui.baby.BabyDto;
import com.example.momkid.ui.home.HomeActivity;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class BlogFragment extends Fragment {
    private RecyclerView rcvBlogs ;
    private ProgressDialog nDialog;

    private EditText edtContent,edtNameBlog;
    private Button btnAddBlog;

    private HomeActivity homeActivity;

    private SwipeRefreshLayout swipeRefreshLayout;

    private BlogAdapter blogAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        loadData();
        View view = inflater.inflate(R.layout.fragment_blog,container,false);
        rcvBlogs = view.findViewById(R.id.rcvBlog);
        rcvBlogs.setLayoutManager(new LinearLayoutManager(view.getContext()));

        homeActivity = (HomeActivity) getActivity();



        nDialog = new ProgressDialog(getContext());
        nDialog.setMessage("Loading..");
        nDialog.setTitle("Get Data");
        nDialog.setIndeterminate(false);
        nDialog.setCancelable(true);
        nDialog.show();

        edtNameBlog=view.findViewById(R.id.edt_nameblog);
        edtContent=view.findViewById(R.id.edt_content);
        btnAddBlog=view.findViewById(R.id.btn_add_blog);
        btnAddBlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameBlog = edtNameBlog.getText().toString();
                String content = edtContent.getText().toString();
                addDataBlog(nameBlog,content,"");
            }
        });



        return view;
    }

    //Sử lý thêm blog
    private void addDataBlog( String name, String content, String img) {
        String token = SharedPreferenceHelper.getSharedPreferenceString(getContext(),"token","");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", name);
            jsonObject.put("content", content);
            jsonObject.put("images", img);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        AndroidNetworking.post(SystemConfig.BASE_URL.concat("/client/blogs"))
                .addJSONObjectBody(jsonObject) // posting json
                .addHeaders("Authorization", String.format("Bearer  %s",token))
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
//                        loadData();
                        Toast.makeText(getContext(),"Bạn đã thêm bài viết thành công",Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onError(ANError error) {
                        log(error.getErrorBody());
                    }
                });

    }

    private void log(String mess){
        Log.d(BlogFragment.class.getName(), mess);
    }

    //Sử lý hiển thị list Blog
    private void loadData() {
        log("da vao day");
        String token = SharedPreferenceHelper.getSharedPreferenceString(getContext(),"token","");
        AndroidNetworking.get(SystemConfig.BASE_URL.concat("/client/blogs?fields=&join=user").concat("&sort=id,DESC"))
                .addHeaders("Authorization", String.format("Bearer  %s",token))
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String json) {
                        nDialog.cancel();
                        List<BlogDto> blogs = new ArrayList<>();
                        BlogDto temp=null;
                        GsonBuilder gson = new GsonBuilder();
                        Type collectionType = new TypeToken<ResponseCommonDto<BlogDto>>(){}.getType();
                        ResponseCommonDto<BlogDto> response = gson.create().fromJson(json, collectionType);

                        for (int i = 0; i < response.getData().size(); i ++){
                            temp = new BlogDto();
                            temp.setContent(response.getData().get(i).getContent());
                            temp.setName(response.getData().get(i).getName());
                            temp.setNameUser(response.getData().get(i).getUser().getFirstName().concat(" ").concat(response.getData().get(i).getUser().getLastName()));
//                            temp.setId(response.getData().get(i).getUser().getFirstName());
//                            temp.setImg(response.getData().get(i).getImg());
                            blogs.add(temp);
                        }
                        blogAdapter = new BlogAdapter(blogs, new IClickItemBlog() {
                            @Override
                            public void onClickItemBlog(BlogDto blogDto) {
                                homeActivity.goToDetailBlog(blogDto);
                            }
                        });
                        rcvBlogs.setAdapter(blogAdapter);
                    }
                    @Override
                    public void onError(ANError anError) {
                        log(anError.getErrorBody());
                    }
                });
    }
}