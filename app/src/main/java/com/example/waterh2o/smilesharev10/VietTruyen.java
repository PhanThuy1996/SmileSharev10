package com.example.waterh2o.smilesharev10;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class VietTruyen extends AppCompatActivity {
    Button btnviet;
    EditText edtenTruyen,edNoidung;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viet_truyen);
        edtenTruyen = findViewById(R.id.editTenTruyen);
        edNoidung = findViewById(R.id.editNoiDung);
        btnviet = findViewById(R.id.btnviet);
        btnviet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NhapTruyen();
            }
        });
    }
    public void NhapTruyen(){
        db = openOrCreateDatabase("appTruyen.db",MODE_PRIVATE,null);
        String tenTruyen = edtenTruyen.getText().toString();
        String noiDung = edNoidung.getText().toString();
        if (tenTruyen.isEmpty()&&noiDung.isEmpty()){
            Toast.makeText(this,"Nhập đủ dữ liệu",Toast.LENGTH_LONG);
        }
        else {
            String sql= "INSERT INTO tbTruyen(TenTruyen,MoTa,NoiDung,id_TheLoai,id_TacGia) VALUES("+
                    "'"+ tenTruyen +"'," +
                    "'"+ " " +"'," +
                    "'"+ noiDung +"'," +
                    "'"+ 8 +"'," +
                    "'"+ 1+"'"+")";
            db.execSQL(sql);
            Intent intent = new Intent(this,Truyen.class);
            startActivity(intent);
        }
    }
}