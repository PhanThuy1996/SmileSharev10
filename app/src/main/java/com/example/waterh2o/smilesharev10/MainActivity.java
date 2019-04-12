package com.example.waterh2o.smilesharev10;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
         private SQLiteDatabase db;
         private ListView lvtheloai;
         private List<TheLoai> theLoaiList = new ArrayList<>();
         private ArrayAdapter<TheLoai> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        bindView();


    }
    private void bindView(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // xét ảnh đại diện và tài khoản
        View header = navigationView.getHeaderView(0);
        Intent intent = getIntent();
        TextView tenTaiKhoan = header.findViewById(R.id.tenTaiKhoan);
//        TextView soDienThoai = header.findViewById(R.id.soDienThoai);
        tenTaiKhoan.setText(intent.getStringExtra("username"));
        // hiển thị listview
        lvtheloai = findViewById(R.id.lvtheloai);
        loadData();

        adapter = new ArrayAdapter<TheLoai>(this,0,theLoaiList){

            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.theloai,null);
                TextView ten = convertView.findViewById(R.id.tvTenTheLoai);
                ImageView anh = convertView.findViewById(R.id.ivanhTheLoai);
                TheLoai tl = theLoaiList.get(position);
                ten.setText(tl.getTenTheLoai());
                if (tl.id==1){
                    anh.setImageResource(R.drawable.vova);
                }
                if (tl.id==4){
                    anh.setImageResource(R.drawable.dangian);
                }
                if (tl.id==5){
                    anh.setImageResource(R.drawable.danhnhan);
                }
                if (tl.id==6){
                    anh.setImageResource(R.drawable.loaivat);
                }
                if (tl.id==7){
                    anh.setImageResource(R.drawable.vnvodoi);
                }
                if (tl.id==8){
                    anh.setImageResource(R.drawable.trangquynh);
                }
                if (tl.id==2){
                    anh.setImageResource(R.drawable.tonghop);
                }
                if (tl.id==3){
                    anh.setImageResource(R.drawable.tuche);
                }

                return convertView;
            }
        };
        lvtheloai.setAdapter(adapter);
       lvtheloai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               chuyenTruyenNho(id);
           }
       });
    }

    private void chuyenTruyenNho(long id){
        Intent ctruyennho = new Intent(this,TruyenNho.class);
        ctruyennho.putExtra("id",id);
        startActivity(ctruyennho);
    }
    private void initData(){
        db = openOrCreateDatabase("appTruyen.db",MODE_PRIVATE,null);
        String sql = " CREATE TABLE IF NOT EXISTS tbTheLoai(id INTEGER PRIMARY KEY AUTOINCREMENT, TenTheLoai text)";
        db.execSQL(sql);


//        String sql8 = "INSERT INTO tbTheLoai(TenTheLoai) VALUES ("+
//                "'"+ "Truyện cười Tổng hợp"+"'"+")";
//        db.execSQL(sql8);

        String sql2 = " CREATE TABLE IF NOT EXISTS tbTaiKhoan(id INTEGER PRIMARY KEY AUTOINCREMENT, TenTaiKhoan text,SoDienThoai text)";
        db.execSQL(sql2);
//        String sql3= "INSERT INTO tbTruyen(TenTruyen,MoTa,NoiDung,id_TheLoai,id_TacGia) VALUES("+
//                "'"+ "Chạy nước gì" +"'," +
//                "'"+ " " +"'," +
//                "'"+ "Natasha đi học vể hớn hở bảo mẹ, mẹ ơi, hôm nay con kiếm được 2 rúp.\n" +
//                "- Ở đâu ra thế?\n" +
//                "- Thằng Vova đố con trèo lên cây, mỗi lần nó cho 10 kopec.\n" +
//                "- Mày bị nó lừa rồi, nó làm thế để nhìn underwear của mày đấy.\n" +
//                "- Hihi.., con biết rồi, con đâu phải đứa ngu? Cho nên hôm nay con đâu có mặc underwear!!!" +"'," +
//                "'"+ 2 +"'," +
//                "'"+ 1+"'"+")";
//        db.execSQL(sql3);
        String sql1 = " CREATE TABLE IF NOT EXISTS tbTruyen(id INTEGER PRIMARY KEY AUTOINCREMENT, TenTruyen text, MoTa text, NoiDung text, id_TheLoai integer, id_TacGia integer)";
        db.execSQL(sql1);
    }
    private void loadData(){
        db = openOrCreateDatabase("appTruyen.db",MODE_PRIVATE,null);
        String sql= "SELECT * FROM tbTheLoai";
        String ten ="";
        int id;
        Cursor cursor= db.rawQuery(sql,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            ten= cursor.getString(1);
            id = cursor.getInt(0);
           TheLoai theLoai = new TheLoai(id,ten);
           theLoaiList.add(theLoai);
           cursor.moveToNext();
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_theloai) {
            chuyenTheLoai();
        }
        else if (id == R.id.nav_truyen) {
           chuyenTruyen();
        }else if (id == R.id.nav_tacgia) {
            chuyenTacGia();
        } else if (id == R.id.nav_viettruyen) {
            chuyenVietTruyen();
        } else if (id == R.id.nav_dangnhap) {
            chuyendangNhap();
        } else if (id == R.id.nav_thoat) {
            chuyengioithieu();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void chuyengioithieu(){
        Intent intent = new Intent(this,GioiThieu.class);
        startActivity(intent);
    }
    public void chuyenVietTruyen(){
        Intent intent = new Intent(this,VietTruyen.class);
        startActivity(intent);
    }
    public void chuyenTruyen(){
        Intent intent = new Intent(this,Truyen.class);
        startActivity(intent);
    }
    public void chuyenTacGia(){
        Intent ctacgia = new Intent(this,TacGia.class);
        startActivity(ctacgia);
    }
    public void chuyendangNhap(){
        Intent cdangnhap = new Intent(this,DangNhap.class);
        startActivity(cdangnhap);
    }
    public void chuyenTheLoai(){
        Intent ctheloai = new Intent(this,MainActivity.class);
        startActivity(ctheloai);
    }
}
