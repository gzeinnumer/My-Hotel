package com.gzeinnumer.tugasbesarzein.DataFasilitas;

public class Makan {
    private String idFMakan;
    private String typeMakanan;
    private String menu;
    private double harga;
    private String keterangan;

    public Makan(String idFMakan, String typeMakanan, String menu, double harga, String keterangan) {
        this.idFMakan = idFMakan;
        this.typeMakanan = typeMakanan;
        this.menu = menu;
        this.harga = harga;
        this.keterangan = keterangan;
    }

    public String getIdFMakan() {
        return idFMakan;
    }

    public String getTypeMakanan() {
        return typeMakanan;
    }

    public String getMenu() {
        return menu;
    }

    public double getHarga() {
        return harga;
    }

    public String getKeterangan() {
        return keterangan;
    }
}
