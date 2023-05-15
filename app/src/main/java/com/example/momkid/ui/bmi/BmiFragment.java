package com.example.momkid.ui.bmi;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.example.momkid.R;
import com.example.momkid.helper.SharedPreferenceHelper;
import com.example.momkid.helper.SystemConfig;
import com.example.momkid.ui.profile.ProflieKidActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.Date;

public class BmiFragment extends Fragment {
    public static final String TAG = BmiFragment.class.getName();
    EditText editCC, editCN;
    Button btn_DG;
    TextView textViewBMI, textViewDG;

    TextClock currentDay;

    BmiDto bmiDto;

    @SuppressLint("SuspiciousIndentation")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bmi,container,false);

        editCC = (EditText) view.findViewById(R.id.editCC);
        editCN = (EditText) view.findViewById(R.id.editCN);
        btn_DG = (Button) view.findViewById(R.id.btn_DG);
        textViewDG = (TextView) view.findViewById(R.id.textViewDG);
        textViewBMI = (TextView) view.findViewById(R.id.textViewBMI);


        // Lấy ngày hiện tại lưu cho startTime
        currentDay=view.findViewById(R.id.currentDay);


        btn_DG.setOnClickListener(v -> {
            textViewBMI.setText("");
            textViewDG.setText("");
            if (editCC.getText().toString().isEmpty() && editCN.getText().toString().isEmpty()) {
                Toast.makeText(getContext(), "Chưa nhập chiều cao, cân nặng", Toast.LENGTH_SHORT).show();
                editCC.requestFocus();
            } else if (editCC.getText().toString().isEmpty() && editCN.getText().toString().isEmpty()) {

                Toast.makeText(getContext(), "Thiếu dữ liệu chiều cao", Toast.LENGTH_SHORT).show();
                editCC.requestFocus();
            } else if (editCN.getText().toString().isEmpty() && editCN.getText().toString().isEmpty()) {

                Toast.makeText(getContext(), "Thiếu dữ liệu cân nặng", Toast.LENGTH_SHORT).show();
                editCN.requestFocus();
            } else {
                bmiDto=new BmiDto();
                double CC = Double.parseDouble(editCC.getText() + "");
                bmiDto.setHeight(String.valueOf(CC));
                double CN = Double.parseDouble(editCN.getText() + "");
                bmiDto.setWeight(String.valueOf(CN));
                DecimalFormat dcf = new DecimalFormat("0.00"); // Định dạng lấy đến 2 con số
                double BMI = CN / CC*CC;
                bmiDto.setBmi(String.valueOf(BMI));
                if ((CC == 0) || (CN == 0)) {
                    Toast.makeText(getContext(), "Chiều cao, cân nặng phải khác 0", Toast.LENGTH_SHORT).show();
                } else {
                    textViewBMI.setText("Chỉ số BMI của bạn:" + dcf.format(BMI));
                    if (BMI < 18.5){
                        textViewDG.setText("Trẻ có dấu hiệu suy dinh dưỡng,thiếu cân");
                        bmiDto.setContent(textViewDG.getText().toString());
                    } else if (18.5 <= BMI && BMI < 22.9){
                        textViewDG.setText("Trẻ có thể trạng cân đối,sức khỏe tốt,ít bệnh");
                        bmiDto.setContent(textViewDG.getText().toString());
                    } else if (23 <= BMI && BMI < 24.9){
                        textViewDG.setText("Trẻ có dấu hiệu thừa cân");
                        bmiDto.setContent(textViewDG.getText().toString());
                    } else if (25 <= BMI && BMI < 29.9){
                        textViewDG.setText("Trẻ có dấu hiệu gần béo phì");
                        bmiDto.setContent(textViewDG.getText().toString());
                    } else
                        textViewDG.setText("Trẻ có chỉ đố đang bị béo phì,chỉ số báo động");
                        bmiDto.setContent(textViewDG.getText().toString());
                }
            }
            bmiDto.setStartTime(currentDay.getTimeZone());
            loadData(bmiDto.getWeight(),bmiDto.getHeight(),bmiDto.getBmi(),bmiDto.getContent(), String.valueOf(bmiDto.getStartTime()));
        });

        return view;
    }

    private void loadData(String weight, String height, String bmi, String content, String startTime) {
        log("Tới đay roi ne");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("weight", weight);
            jsonObject.put("height", height);
            jsonObject.put("bmi",bmi);
            jsonObject.put("content",content);
            jsonObject.put("startTime",startTime);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        String token = SharedPreferenceHelper.getSharedPreferenceString(getContext(),"token","");
        AndroidNetworking.post(SystemConfig.BASE_URL.concat("/client/bmi-kid"))
                .addHeaders("Authorization", String.format("Bearer  %s",token))
                .addJSONObjectBody(jsonObject)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String json) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setMessage("Chỉ số BMI của " + content);
                        builder.setCancelable(false);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // code khi người dùng nhấn nút OK
                            }
                        });
                        AlertDialog alert = builder.create();
                        alert.show();

                    }
                    @Override
                    public void onError(ANError anError) {
                        log(anError.getErrorBody());
                    }
                });
    }

    private void log(String mess){
        Log.d(TAG, mess);
    }
}