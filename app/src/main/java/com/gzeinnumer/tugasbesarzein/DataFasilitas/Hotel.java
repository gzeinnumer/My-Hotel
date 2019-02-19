package com.gzeinnumer.tugasbesarzein.DataFasilitas;

public class Hotel {
    private String idFHotel;
    private String jenisFasilitas;
    private String namaFasilitas;
    private String jamBuka;
    private String keterangan;

    public Hotel(String idFHotel, String jenisFasilitas, String namaFasilitas, String jamBuka, String keterangan) {
        this.idFHotel = idFHotel;
        this.jenisFasilitas = jenisFasilitas;
        this.namaFasilitas = namaFasilitas;
        this.jamBuka = jamBuka;
        this.keterangan = keterangan;
    }

    public String getIdFHotel() {
        return idFHotel;
    }

    public String getJenisFasilitas() {
        return jenisFasilitas;
    }

    public String getNamaFasilitas() {
        return namaFasilitas;
    }

    public String getJamBuka() {
        return jamBuka;
    }

    public String getKeterangan() {
        return keterangan;
    }
}
