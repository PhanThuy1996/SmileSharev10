package com.example.waterh2o.smilesharev10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GioiThieu extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gioi_thieu);

        findViewById(R.id.btnbatdaugt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chuyenTrangmain();
            }
        });
        findViewById(R.id.btndangnhapgt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chuyenTrangDangnhap();
            }
        });
    }
    public void chuyenTrangmain(){
        Intent cmain = new Intent(this,MainActivity.class);
        startActivity(cmain);
    }
    public  void  chuyenTrangDangnhap(){
        Intent cdangnhap = new Intent(this,DangNhap.class);
        startActivity(cdangnhap);
    }
}
