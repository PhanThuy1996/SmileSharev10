package com.example.waterh2o.smilesharev10;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Truyen extends AppCompatActivity {
    private SQLiteDatabase db;
    private ListView lvtruyen;
    private List<TruyenAdapter> truyenList = new ArrayList<>();
    private ArrayAdapter<TruyenAdapter> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truyen);
        // hiển thị listview
        lvtruyen = findViewById(R.id.lvtruyen);
        loadData();

        adapter = new ArrayAdapter<TruyenAdapter>(this,0,truyenList){

            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.truyen,null);
                TextView tentruyen = convertView.findViewById(R.id.tvTruyen);
                ImageView anh = convertView.findViewById(R.id.ivanhTruyen);
                TruyenAdapter tr = truyenList.get(position);
                tentruyen.setText(tr.getTenTruyen());
                anh.setImageResource(R.drawable.cuoi);
                return convertView;
            }
        };
        lvtruyen.setAdapter(adapter);
        lvtruyen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                chuyentrang(position);
            }
        });
    }
    public void chuyentrang(int position){
        Intent intent = new Intent(this,ChiTietTruyen.class);
        intent.putExtra("id",position);
        startActivity(intent);
    }
    private void loadData(){
        db = openOrCreateDatabase("appTruyen.db",MODE_PRIVATE,null);
        String sql= "SELECT * FROM tbTruyen";
        String tenTruyen ="";
        int id;
        Cursor cursor= db.rawQuery(sql,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            tenTruyen= cursor.getString(1);
            id = cursor.getInt(0);
            TruyenAdapter truyen = new TruyenAdapter(id,tenTruyen);
            truyenList.add(truyen);
            cursor.moveToNext();
        }
    }
}
