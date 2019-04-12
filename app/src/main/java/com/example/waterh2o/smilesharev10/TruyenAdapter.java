package com.example.waterh2o.smilesharev10;

public class TruyenAdapter {
    private long id;
    private String tenTruyen;

    public TruyenAdapter() {
    }

    public TruyenAdapter(long id, String tenTruyen) {
        this.id = id;
        this.tenTruyen = tenTruyen;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTenTruyen() {
        return tenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        this.tenTruyen = tenTruyen;
    }
}
