package com.example.waterh2o.smilesharev10;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TacGia extends AppCompatActivity {
    private SQLiteDatabase db;
    private ListView lvtacgia;
    private List<TacGiaAdapter> tacgiaList = new ArrayList<>();
    private ArrayAdapter<TacGiaAdapter> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tac_gia);
        // hiển thị listview
        lvtacgia = findViewById(R.id.lvtacgia);
        loadData();

        adapter = new ArrayAdapter<TacGiaAdapter>(this,0,tacgiaList){

            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.tacgia,null);
                TextView tentruyen = convertView.findViewById(R.id.tvTruyen);
                ImageView anh = convertView.findViewById(R.id.ivanhTruyen);
                TacGiaAdapter tr = tacgiaList.get(position);
                tentruyen.setText(tr.getTenTacGia());
                anh.setImageResource(R.drawable.canhan);
                return convertView;
            }
        };
        lvtacgia.setAdapter(adapter);
    }

    private void loadData(){
        db = openOrCreateDatabase("appTruyen.db",MODE_PRIVATE,null);
        String sql= "SELECT * FROM tbTaiKhoanDangKy";
        String tenTacgia ="";
        int id;
        Cursor cursor= db.rawQuery(sql,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            tenTacgia= cursor.getString(1);
            id = cursor.getInt(0);
            TacGiaAdapter truyen = new TacGiaAdapter(id,tenTacgia);
            tacgiaList.add(truyen);
            cursor.moveToNext();
        }
    }
}
