package com.example.waterh2o.smilesharev10;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DangKy extends AppCompatActivity {

    Button btndangnhap,btndangky;
    private EditText edUser, edPass, edPhone;
    SQLiteDatabase db;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        btndangnhap = findViewById(R.id.btnDangNhapdk);
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chuyenDangnhap();
            }
        });
        initData();
        edUser = findViewById(R.id.editUsername1);
        edPass = findViewById(R.id.editPassword1);
        edPhone = findViewById(R.id.editPhone1);
        btndangky = findViewById(R.id.btnDangKydk);
        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });
    }
    public void chuyenDangnhap(){
        Intent cdnhap = new Intent(this,DangNhap.class);
        startActivity(cdnhap);
    }
    private void initData(){
        db = openOrCreateDatabase("appTruyen.db",MODE_PRIVATE,null);
        String sql2 = " CREATE TABLE IF NOT EXISTS tbTaiKhoanDangKy(id INTEGER PRIMARY KEY AUTOINCREMENT, TenTaiKhoan text,MatKhau text,SoDienThoai text)";
        db.execSQL(sql2);
    }
    public void signup(){
        String user = edUser.getText().toString();
        String pass = edPass.getText().toString();
        String phone = edPhone.getText().toString();
        if (!validate()){
            onSignupFailed();
            return;
        }
        else {
            String sql= "INSERT INTO tbTaiKhoanDangKy(TenTaiKhoan,MatKhau,SoDienThoai) VALUES("+
                    "'"+ user +"'," +
                    "'"+ pass +"'," +
                    "'"+ phone +"'"+")";
            db.execSQL(sql);
            onSignupSuccess();
            Intent intent = new Intent(this,DangNhap.class);
            startActivity(intent);
        }
    }
    public void onSignupSuccess() {
        Toast.makeText(getBaseContext(),"Đăng ký thành công",Toast.LENGTH_LONG).show();
    }
    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Đăng ký thất bại", Toast.LENGTH_LONG).show();
        btndangky.setEnabled(true);
    }
    public boolean validate() {
        boolean valid = true;
        String user = edUser.getText().toString();
        String pass = edPass.getText().toString();
        String phone = edPhone.getText().toString();
        if (user.isEmpty() || user.length() < 6||user.length()>20) {
            edUser.setError("Tên có độ dài ký tự từ 6-20");
            valid = false;
        } else {
            edUser.setError(null);
        }
        if (pass.isEmpty() || pass.length()< 6||pass.length()>20) {
            edPass.setError("Nhập độ dài mật khẩu từ 6-20");
            valid = false;
        } else {
            edPass.setError(null);
        }
        if (phone.isEmpty() || phone.length() != 10) {
            edPhone.setError("Nhập đúng số điện thoại");
            valid = false;
        } else {
            edPhone.setError(null);
        }
        return valid;
    }
}
