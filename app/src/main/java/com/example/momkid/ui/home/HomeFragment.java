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

import com.example.momkid.R;
import com.example.momkid.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private Button btn_updateData,btn_cancelForm;
    private ImageButton btn_age_update,btn_height_update,btn_weight_update;
    private EditText edt_updateData;
    private View view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home,container,false);

        btn_age_update= view.findViewById(R.id.btn_age_update);
        btn_age_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUpdateDialog(Gravity.CENTER);
            }
        });

        return view;

    }

    private void openUpdateDialog(int gravity){
        final Dialog dialog = null;
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_update_data);

        Window window = dialog.getWindow();
        if(window == null){
            return;
        }

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        edt_updateData = view.findViewById(R.id.edt_updateData);
        btn_updateData = view.findViewById(R.id.btn_updateData);
        btn_cancelForm = view.findViewById(R.id.btn_cancelForm);



    }


}