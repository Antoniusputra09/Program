package com.example.asus.program;

public class Nilai {
    String id;
    String nama;
    String nilai;

    public Nilai(String id, String nama, String nilai) {
        this.id = id;
        this.nama = nama;
        this.nilai = nilai;
    }

    public Nilai() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNilai() {
        return nilai;
    }

    public void setNilai(String nilai) {
        this.nilai = nilai;
    }
}
