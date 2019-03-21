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
    private String januari;
    private String februari;
    private String maret;
    private String april;
    private String mei;
    private String juni;
    private String juli;
    private String agustus;
    private String september;
    private String oktober;
    private String november;
    private String desember;
    private String status;


    public User(String id, String username, String imageUrl, String alamat, String catatan,
                String email, String kelas, String namaayah, String namaibu, String namawali,
                String nis, String noayah, String nohp, String noibu, String nowali, String tempat, String ttl, String status) {
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
        this.status = status;
    }

    public User(String id, String januari, String februari, String maret, String april, String mei, String juni, String juli, String agustus,
                String september, String oktober, String november, String desember, String status) {
        this.id = id;
        this.januari = januari;
        this.februari = februari;
        this.maret = maret;
        this.april = april;
        this.mei = mei;
        this.juni = juni;
        this.juli = juli;
        this.agustus = agustus;
        this.september = september;
        this.oktober = oktober;
        this.november = november;
        this.desember = desember;
        this.status = status;
    }

    public User() {
    }


    public String getFebruari() {
        return februari;
    }

    public void setFebruari(String februari) {
        this.februari = februari;
    }

    public String getMaret() {
        return maret;
    }

    public void setMaret(String maret) {
        this.maret = maret;
    }

    public String getApril() {
        return april;
    }

    public void setApril(String april) {
        this.april = april;
    }

    public String getMei() {
        return mei;
    }

    public void setMei(String mei) {
        this.mei = mei;
    }

    public String getJuni() {
        return juni;
    }

    public void setJuni(String juni) {
        this.juni = juni;
    }

    public String getJuli() {
        return juli;
    }

    public void setJuli(String juli) {
        this.juli = juli;
    }

    public String getAgustus() {
        return agustus;
    }

    public void setAgustus(String agustus) {
        this.agustus = agustus;
    }

    public String getSeptember() {
        return september;
    }

    public void setSeptember(String september) {
        this.september = september;
    }

    public String getOktober() {
        return oktober;
    }

    public void setOktober(String oktober) {
        this.oktober = oktober;
    }

    public String getNovember() {
        return november;
    }

    public void setNovember(String november) {
        this.november = november;
    }

    public String getDesember() {
        return desember;
    }

    public void setDesember(String desember) {
        this.desember = desember;
    }

    public String getJanuari() {
        return januari;
    }

    public void setJanuari(String januari) {
        this.januari = januari;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
