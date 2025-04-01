package com.example.tuan1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText Ten, MS, Lop, KeHoach,SDT;
    Button btGui;
    RadioGroup Nam;
    CheckBox Nhung, Mang, DienTu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Nam = findViewById(R.id.radioGroupYears);
        Ten = findViewById(R.id.Name);
        MS = findViewById(R.id.ID);
        Lop = findViewById(R.id.Lop);
        KeHoach = findViewById(R.id.KeHoach);
        btGui = findViewById(R.id.btgui);
        Nhung = findViewById(R.id.checkBoxMT);
        Mang = findViewById(R.id.checkBoxM);
        DienTu = findViewById(R.id.checkBoxDT);
        SDT=findViewById(R.id.sdt);

        btGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = Ten.getText().toString();
                String lop = Lop.getText().toString();
                String ms = MS.getText().toString();
                String kh = KeHoach.getText().toString();
                String sdt=SDT.getText().toString();
                int id_radio = Nam.getCheckedRadioButtonId();
                String namhoc = "Chưa chọn";
                if(id_radio!=0) {
                    RadioButton selectedRadioButton = findViewById(id_radio);
                    namhoc = selectedRadioButton.getText().toString();
                }
                String chuyenNganh = "Chưa chọn";
                if (Nhung.isChecked()) {
                    chuyenNganh = "Hệ thống nhúng";
                }
                if (Mang.isChecked()) {
                    chuyenNganh = "Viễn thông - Mạng";
                }
                if (DienTu.isChecked()) {
                    chuyenNganh = "Điện tử";
                }

                String thongTin = "Tên: " + ten + "\n"
                        + "MSSV: " + ms + "\n"
                        + "Lớp: " + lop + "\n"
                        + "SDT: " + sdt+"\n"
                        + "Năm học: " + namhoc + "\n"
                        + "Chuyên ngành: " + chuyenNganh + "\n"
                        + "Kế hoạch: " + kh;

                Intent myintent = new Intent(MainActivity.this, MainActivity2.class);
                myintent.putExtra("thongtin", thongTin);
                startActivity(myintent);
            }
        });
    }
}
