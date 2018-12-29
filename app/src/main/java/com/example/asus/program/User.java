package com.example.asus.program;

import android.content.Context;

public class User {

    private String id;
    private String username;
    private String imageUrl;
    private String alamat;
    private String catatan;
    private String email;
    private String kelas;
    private String namaayah;
    private String namaibu;
    private String namawali;
    private String nis;
    private String noayah;
    private String nohp;
    private String noibu;
    private String nowali;
    private String tempat;
    private String ttl;


    public User(String id, String username, String imageUrl, String alamat, String catatan,
                String email, String kelas, String namaayah, String namaibu, String namawali,
                String nis, String noayah, String nohp, String noibu, String nowali, String tempat, String ttl) {
        this.id = id;
        this.username = username;
        this.imageUrl = imageUrl;
        this.alamat = alamat;
        this.catatan = catatan;
        this.email = email;
        this.kelas = kelas;
        this.namaayah = namaayah;
        this.namaibu = namaibu;
        this.namawali = namawali;
        this.nis = nis;
        this.noayah = noayah;
        this.nohp = nohp;
        this.noibu = noibu;
        this.nowali = nowali;
        this.tempat = tempat;
        this.ttl = ttl;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getNamaayah() {
        return namaayah;
    }

    public void setNamaayah(String namaayah) {
        this.namaayah = namaayah;
    }

    public String getNamaibu() {
        return namaibu;
    }

    public void setNamaibu(String namaibu) {
        this.namaibu = namaibu;
    }

    public String getNamawali() {
        return namawali;
    }

    public void setNamawali(String namawali) {
        this.namawali = namawali;
    }

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public String getNoayah() {
        return noayah;
    }

    public void setNoayah(String noayah) {
        this.noayah = noayah;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    public String getNoibu() {
        return noibu;
    }

    public void setNoibu(String noibu) {
        this.noibu = noibu;
    }

    public String getNowali() {
        return nowali;
    }

    public void setNowali(String nowali) {
        this.nowali = nowali;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }
}
