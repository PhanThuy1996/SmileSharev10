package com.example.waterh2o.smilesharev10;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ChiTietTruyen extends AppCompatActivity {
    TextView tenTruyen,noiDung,tacGia;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_truyen);
        tenTruyen = findViewById(R.id.tvtenTruyen);
        noiDung = findViewById(R.id.tvnoiDung);
        tacGia = findViewById(R.id.tvtacgia);

        Intent intent = getIntent();
        int i = intent.getIntExtra("id",0);
        db = openOrCreateDatabase("appTruyen.db",MODE_PRIVATE,null);
        String sql1= "SELECT TenTruyen FROM tbTruyen where id = i";
        String sql2= "SELECT NoiDung FROM tbTruyen where id = i";
        String sql3= "SELECT id_TacGia FROM tbTruyen where id = i";
        db.execSQL(sql1);
        db.execSQL(sql2);
        db.execSQL(sql3);
        tenTruyen.setText(sql1);
        noiDung.setText(sql2);
        tacGia.setText(sql3);

    }
}
