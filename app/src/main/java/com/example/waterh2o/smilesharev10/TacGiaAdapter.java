package com.example.waterh2o.smilesharev10;

public class TacGiaAdapter {
    private long id;
    private String tenTacGia;

    public TacGiaAdapter() {
    }

    public TacGiaAdapter(long id, String tenTacGia) {
        this.id = id;
        this.tenTacGia = tenTacGia;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }
}
