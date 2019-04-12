package com.example.waterh2o.smilesharev10;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DangNhap extends AppCompatActivity {
    private Button btndangky,btndangnhap;
    private EditText eduser,edpass;
    private SQLiteDatabase db;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        btndangky = findViewById(R.id.btnDangKydn);
        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chuyenDangky();
            }
        });
        eduser = findViewById(R.id.editUsername);
        edpass = findViewById(R.id.editPassword);
        btndangnhap = findViewById(R.id.btnDangNhapdn);
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (User()&&Pass()){
                    chuyenMain();
                }
//                else {
//                    Toast.makeText(this,"Thất bại",Toast.LENGTH_LONG).show();
//                }
            }
        });
    }
    public  void chuyenDangky(){
        Intent cdky = new Intent(this,DangKy.class);
        startActivity(cdky);
    }
    public void chuyenMain() {
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("username",eduser.getText().toString());
//        intent.putExtra("password",edpass.getText().toString());
        startActivity(intent);
    }
    public boolean User(){
        String ten= eduser.getText().toString();
        String user="";
        boolean valid=false;
        db = openOrCreateDatabase("appTruyen.db",MODE_PRIVATE,null);
        String sql= "SELECT * FROM tbTaiKhoanDangKy";
        Cursor cursor= db.rawQuery(sql,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            user= cursor.getString(1);
            if(user.equals(ten))
                valid=true;
            cursor.moveToNext();
        }
        return valid;
    }
    public boolean Pass(){
        String pass="";
        boolean valid=false;
        String mk= edpass.getText().toString();
        db = openOrCreateDatabase("appTruyen.db",MODE_PRIVATE,null);
        String sql= "SELECT * FROM tbTaiKhoanDangKy";
        Cursor cursor= db.rawQuery(sql,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            pass= cursor.getString(2);
            if (pass.equals(mk))
                valid=true;
            cursor.moveToNext();

        }
        return valid;
    }
}