package com.gzeinnumer.tugasbesarzein.DataFasilitas;

public class Kamar {
    private String idFKamar;
    private String typeKamar;
    private String fasilitasKamar;
    private double harga;
    private String keterangan;

    public Kamar(String idFKamar, String typeKamar, String fasilitasKamar, double harga, String keterangan) {
        this.idFKamar = idFKamar;
        this.typeKamar = typeKamar;
        this.fasilitasKamar = fasilitasKamar;
        this.harga = harga;
        this.keterangan = keterangan;
    }

    public String getIdFKamar() {
        return idFKamar;
    }

    public String getTypeKamar() {
        return typeKamar;
    }

    public String getFasilitasKamar() {
        return fasilitasKamar;
    }

    public double getHarga() {
        return harga;
    }

    public String getKeterangan() {
        return keterangan;
    }
}
