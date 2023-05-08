package com.example.momkid.bmi;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.momkid.R;

import java.text.DecimalFormat;

public class BmiActivity extends AppCompatActivity {

    EditText editCC, editCN;
    RadioGroup radioGroupGT;
    RadioButton radioButtonNam, radioButtonNu;
    Button btn_DG;
    TextView textViewBMI, textViewDG;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_bmi);

        editCC = (EditText) findViewById(R.id.editCC);
        editCN = (EditText) findViewById(R.id.editCN);
        radioGroupGT = (RadioGroup) findViewById(R.id.radioGroupGT);
        radioButtonNam = (RadioButton) findViewById(R.id.radioButtonNam);
        radioButtonNu = (RadioButton) findViewById(R.id.radioButtonNu);
        btn_DG = (Button) findViewById(R.id.btn_DG);
        textViewDG = (TextView) findViewById(R.id.textViewDG);
        textViewBMI = (TextView) findViewById(R.id.textViewBMI);
        btn_DG.setOnClickListener(view -> {
            textViewBMI.setText("");
            textViewDG.setText("");
            if (editCC.getText().toString().isEmpty() && editCN.getText().toString().isEmpty()) {

                Toast.makeText(this, "Chưa nhập chiều cao, cân nặng", Toast.LENGTH_SHORT).show();
                editCC.requestFocus();
            } else if (editCC.getText().toString().isEmpty() && editCN.getText().toString().isEmpty()) {

                Toast.makeText(this, "Thiếu dữ liệu chiều cao", Toast.LENGTH_SHORT).show();
                editCC.requestFocus();
            } else if (editCN.getText().toString().isEmpty() && editCN.getText().toString().isEmpty()) {

                Toast.makeText(this, "Thiếu dữ liệu cân nặng", Toast.LENGTH_SHORT).show();
                editCN.requestFocus();
            } else if (radioButtonNam.isChecked() == false && radioButtonNu.isChecked() == false) {
                Toast.makeText(this, "Vui lòng chọn giới tính", Toast.LENGTH_SHORT).show();
            } else {
                double CC = Double.parseDouble(editCC.getText() + "");
                double CN = Double.parseDouble(editCN.getText() + "");
                DecimalFormat dcf = new DecimalFormat("0.00"); // Định dạng lấy đến 2 con số
                double BMI = CN / CC*CC;
                if ((CC == 0) || (CN == 0)) {
                    Toast.makeText(this, "Chiều cao, cân nặng phải khác 0", Toast.LENGTH_SHORT).show();
                } else {
                    textViewBMI.setText("Chỉ số BMI của bạn:" + dcf.format(BMI));
                    if (BMI < 18.5)
                        textViewDG.setText("Trẻ có dấu hiệu suy dinh dưỡng,thiếu cân");
                    else if (18.5 <= BMI && BMI < 22.9) textViewDG.setText("Trẻ có thể trạng cân đối,sức khỏe tốt,ít bệnh");
                    else if (23 <= BMI && BMI < 24.9)
                        textViewDG.setText("Trẻ có dấu hiệu thừa cân");
                    else if (25 <= BMI && BMI < 29.9)
                        textViewDG.setText("Trẻ có dấu hiệu gần béo phì");
                    else
                        textViewDG.setText("Trẻ có chỉ đố đang bị béo phì,chỉ số báo động");
                }
            }
        });
    }
}
