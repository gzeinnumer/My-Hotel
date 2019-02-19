package com.gzeinnumer.tugasbesarzein.Model;

public class ModelHotel {
    private String idHotel;
    private String nama;
    private String alamat;
    private String idFHotel;
    private String idFMakan;
    private String idFKamar;
    private double v0;
    private double v1;
    private String gambar;
    private String keterangan;

    public ModelHotel() {
    }

    public ModelHotel(String idHotel, String nama, String alamat, String idFHotel, String idFMakan, String idFKamar, double v0, double v1, String gambar, String keterangan) {
        this.idHotel = idHotel;
        this.nama = nama;
        this.alamat = alamat;
        this.idFHotel = idFHotel;
        this.idFMakan = idFMakan;
        this.idFKamar = idFKamar;
        this.v0 = v0;
        this.v1 = v1;
        this.gambar = gambar;
        this.keterangan = keterangan;
    }

    public String getIdHotel() {
        return idHotel;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getIdFHotel() {
        return idFHotel;
    }

    public String getIdFMakan() {
        return idFMakan;
    }

    public String getIdFKamar() {
        return idFKamar;
    }

    public double getV0() {
        return v0;
    }

    public double getV1() {
        return v1;
    }

    public String getGambar() {
        return gambar;
    }

    public String getKeterangan() {
        return keterangan;
    }
}
