package com.example.momkid.ui.baby;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.momkid.R;
import com.example.momkid.ui.blog.BlogAdapter;
import com.example.momkid.ui.blog.BlogDto;
import com.example.momkid.ui.blog.BlogFragment;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BabyFragment extends Fragment {
    private RecyclerView rcvKid ;
    private ProgressDialog nDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        loadData();
        View view = inflater.inflate(R.layout.fragment_list_kid,container,false);
        rcvKid = view.findViewById(R.id.rcvKid);
        rcvKid.setLayoutManager(new LinearLayoutManager(view.getContext()));


        nDialog = new ProgressDialog(getContext());
        nDialog.setMessage("Loading..");
        nDialog.setTitle("Get Data");
        nDialog.setIndeterminate(false);
        nDialog.setCancelable(true);
        nDialog.show();
        return view;
    }

    private void log(String mess){
        Log.d(BabyFragment.class.getName(), mess);
    }

    private void loadData() {
        log("da vao day");
        String url = "https://api.ipify.org?format=json";
        AndroidNetworking.get(url).build().getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                nDialog.cancel();
                log(String.valueOf(response));

                List<BabyDto> babys = new ArrayList<>();
                BabyDto temp = null;
                for (int i = 0; i < 3; i++) {
                    temp = new BabyDto();
                    temp.setNameKid(String.format("Tên trẻ %d", i));
                    temp.setBirthDay(String.format("Ngày sinh %d", i));
                    temp.setSexKid(String.format("Giới tính %d", i));
                    babys.add(temp);
                }
                BabyAdapter adapter = new BabyAdapter(babys, getContext());
                rcvKid.setAdapter(adapter);
            }

            @Override
            public void onError(ANError anError) {
                log( String.valueOf(anError));
            }
        });
    }

}
