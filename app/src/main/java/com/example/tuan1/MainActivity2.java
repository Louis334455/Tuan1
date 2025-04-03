package com.example.tuan1;

import static android.provider.MediaStore.ACTION_IMAGE_CAPTURE;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.ByteArrayOutputStream;

public class MainActivity2 extends AppCompatActivity {
TextView thongtin;
Button btback, butCap;
ImageView anhSV;
Bitmap currentBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
      thongtin=findViewById(R.id.thongtinnhan);
      butCap = findViewById(R.id.butcap);
      anhSV = findViewById(R.id.imageView);
      if (savedInstanceState != null) {
            byte[] imageBytes = savedInstanceState.getByteArray("img");
            if (imageBytes != null) {
                currentBitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                anhSV.setImageBitmap(currentBitmap);
            }
        }
      butCap.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent pic_intent=new Intent(ACTION_IMAGE_CAPTURE);
              if (ActivityCompat.checkSelfPermission(MainActivity2.this,
                      android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
              {
                  ActivityCompat.requestPermissions(MainActivity2.this,new
                          String[]{android.Manifest.permission.CAMERA}, 1);
                  return;
              }
              startActivityForResult(pic_intent,11);
          }
      });
      btback=findViewById(R.id.btback);
    String data=getIntent().getStringExtra("thongtin");
    thongtin.setText(data);
    btback.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==11 && resultCode== MainActivity2.RESULT_OK)
        {
            Bitmap pic =(Bitmap) data.getExtras().get("data");
            anhSV.setImageBitmap(pic);
            currentBitmap = pic;
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (currentBitmap != null) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            currentBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            outState.putByteArray("img", stream.toByteArray());
        }
    }
}