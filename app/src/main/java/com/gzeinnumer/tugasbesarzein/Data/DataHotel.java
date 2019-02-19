package com.gzeinnumer.tugasbesarzein.Data;

import com.gzeinnumer.tugasbesarzein.DataFasilitas.Hotel;
import com.gzeinnumer.tugasbesarzein.DataFasilitas.Kamar;
import com.gzeinnumer.tugasbesarzein.DataFasilitas.Makan;
import com.gzeinnumer.tugasbesarzein.Model.ModelHotel;
import com.gzeinnumer.tugasbesarzein.Model.ModelUser;

import java.util.ArrayList;

public class DataHotel {
    public ArrayList<ModelHotel> listHotel = new ArrayList<>();
    public ArrayList<Hotel> listFHotel = new ArrayList<>();
    public ArrayList<Makan> listFMakan = new ArrayList<>();
    public ArrayList<Kamar> listFKamar = new ArrayList<>();
    public ArrayList<ModelUser> listUser = new ArrayList<>();
    //ListHotel list =  new ListHotel();

    public DataHotel() {
        initData();
        //list.getJSON(list.url);
    }

    private void initData() {
        listHotel.clear();
        //listHotel.add(new ModelHotel("H1","balbar","alamat","FH1","FM1","FK1",-0.9235464,100.4252822,"http://1.bp.blogspot.com/-NjczTAE-o24/VRKiqdIm4QI/AAAAAAAAABc/Qsgx8Kjz68g/s1600/Bodo_amat%5B1%5D.jpg","rumah"));
        //listFHotel.add(new Hotel("H1","Sport","Lapangan Bola","14.31","Umum"));
        //listFHotel.add(new Hotel("H1","Sport","Lapangan Bola","14.31","Umum"));
        //listFMakan.add(new Makan("H1","Sport","Makan",14,"Umum"));
        //listFKamar.add(new Kamar("H1","Sport","Murah",14,"Umum"));
    }

    public void initDataOffline(){
        listHotel.clear();
        listFKamar.clear();
        listFMakan.clear();
        listFHotel.clear();

        listFHotel.add(new Hotel("H1","Sport","Lapangan Bola","14.31","Umum"));
        listFHotel.add(new Hotel("H1","Sport","Lapangan Bola","14.31","Umum"));
        listFMakan.add(new Makan("H1","Sport","Makan",14,"Umum"));
        listFKamar.add(new Kamar("H1","Sport","Murah",14,"Umum"));


        listHotel.add(new ModelHotel("H1","Rocky Plaza Hotel Padang","Jalan Permindo No. 40 Po Box 380 PD, Padang Barat, Padang, Sumatera Barat, Indonesia, 25111","FH1","FM1","FK1",-0.9466927,100.3570093,"http://him.nusatrip.net/ID/50/1042153550/26f11701020edb0c2788e2e26a26899d_ctb.jpg","Lumayan"));
        listHotel.add(new ModelHotel("H2","Daima Hotel","Jl. Jend. Sudirman No 17, Padang Barat, Padang, Sumatera Barat, Indonesia, 25112","FH2","FM2","FK2",-0.944012,100.3614978,"https://r-ak.bstatic.com/images/hotel/max1024x768/112/11250613.jpg","Lumayan"));
        listHotel.add(new ModelHotel("H3","Grand Zuri Hotel Padang","Jl. MH Thamrin No. 27 , Padang Selatan, Padang, Sumatera Barat, Indonesia, 25133","FH3","FM3","FK3",-0.9544880,100.3641730,"https://i.ibb.co/qs1JqTj/20181227-205450.jpg","Lumayan"));
        listHotel.add(new ModelHotel("H4","Fave Hotel","Jalan Belakang Olo No.46, Padang Barat, Padang, Sumatera Barat, Indonesia, 25116","FH4","FM4","FK4",-0.945478,100.3554118,"https://i.ibb.co/CV6TtdV/20181227-214223.jpg","Lumayan"));
        listHotel.add(new ModelHotel("H5","Amaris Hotel","Jalan Jendral sudirman no.19 Kota, Padang Barat, Padang, Sumatera Barat, Indonesia","FH5","FM5","FK5",-0.943568,100.3612768,"https://r-ak.bstatic.com/images/hotel/max500/102/102960522.jpg","Lumayan"));
        listHotel.add(new ModelHotel("H6","Grand Inna Padang","Jln. Gereja No. 34, Padang Barat, Padang, Sumatera Barat, Indonesia, 25118","FH6","FM6","FK6",-0.9567880,100.3570040,"https://i.ibb.co/bFsZM7q/20181227-210925.jpg","Lumayan"));
        listHotel.add(new ModelHotel("H7","Pangeran City Hotel","Jl. Dobi No.3-5, Padang Barat, Padang, Sumatera Barat, Indonesia, 25115","FH7","FM7","FK7",-0.9552430,100.3595530,"https://i.ibb.co/1XSVCPf/20181227-205923.jpg","Lumayan"));
        listHotel.add(new ModelHotel("H8","Yani Homestay","Jalan Nipah no. 1 Padang 25118 Indonesia","FH8","FM8","FK8",-0.9610430,100.3541640,"https://i.ibb.co/kMTsyjw/20181227-212459.jpg","Lumayan"));
        listHotel.add(new ModelHotel("H9","Pangeran Beach Hotel","Jalan Ir. Haji Juanda No 79, Padang Utara, Padang, Sumatera Barat, Indonesia, 25115","FH9","FM9","FK9",-0.9240120,100.3506900,"https://i.ibb.co/JsKzGW4/20181227-215140.jpg","Lumayan"));
        listHotel.add(new ModelHotel("H10","Ibis Padang Hotel","Jalan Taman Siswa No 1A, Padang Utara, Padang, Sumatera Barat, Indonesia, 25139","FH10","FM10","FK10",-0.9293320,100.3629870	,"https://i.ibb.co/3Ywhvvv/20181227-215916.jpg","Lumayan"));

        listFHotel.add(new Hotel("H2","Sport","Lapangan Bola","14.31","Umum"));
        listFHotel.add(new Hotel("H2","Sport","Lapangan Bola","14.31","Umum"));
        listFMakan.add(new Makan("H2","Sport","Makan",14,"Umum"));
        listFKamar.add(new Kamar("H2","Sport","Murah",14,"Umum"));

        listFHotel.add(new Hotel("H3","Sport","Lapangan Bola","14.31","Umum"));
        listFHotel.add(new Hotel("H3","Sport","Lapangan Bola","14.31","Umum"));
        listFMakan.add(new Makan("H3","Sport","Makan",14,"Umum"));
        listFKamar.add(new Kamar("H3","Sport","Murah",14,"Umum"));


        listFHotel.add(new Hotel("H4","Sport","Lapangan Bola","14.31","Umum"));
        listFHotel.add(new Hotel("H4","Sport","Lapangan Bola","14.31","Umum"));
        listFMakan.add(new Makan("H4","Sport","Makan",14,"Umum"));
        listFKamar.add(new Kamar("H4","Sport","Murah",14,"Umum"));

        listFHotel.add(new Hotel("H5","Sport","Lapangan Bola","14.31","Umum"));
        listFHotel.add(new Hotel("H5","Sport","Lapangan Bola","14.31","Umum"));
        listFMakan.add(new Makan("H5","Sport","Makan",14,"Umum"));
        listFKamar.add(new Kamar("H5","Sport","Murah",14,"Umum"));

        listFHotel.add(new Hotel("H6","Sport","Lapangan Bola","14.31","Umum"));
        listFHotel.add(new Hotel("H6","Sport","Lapangan Bola","14.31","Umum"));
        listFMakan.add(new Makan("H6","Sport","Makan",14,"Umum"));
        listFKamar.add(new Kamar("H6","Sport","Murah",14,"Umum"));

        listFHotel.add(new Hotel("H7","Sport","Lapangan Bola","14.31","Umum"));
        listFHotel.add(new Hotel("H7","Sport","Lapangan Bola","14.31","Umum"));
        listFMakan.add(new Makan("H7","Sport","Makan",14,"Umum"));
        listFKamar.add(new Kamar("H7","Sport","Murah",14,"Umum"));

        listFHotel.add(new Hotel("H8","Sport","Lapangan Bola","14.31","Umum"));
        listFHotel.add(new Hotel("H8","Sport","Lapangan Bola","14.31","Umum"));
        listFMakan.add(new Makan("H8","Sport","Makan",14,"Umum"));
        listFKamar.add(new Kamar("H8","Sport","Murah",14,"Umum"));

        listFHotel.add(new Hotel("H9","Sport","Lapangan Bola","14.31","Umum"));
        listFHotel.add(new Hotel("H9","Sport","Lapangan Bola","14.31","Umum"));
        listFMakan.add(new Makan("H9","Sport","Makan",14,"Umum"));
        listFKamar.add(new Kamar("H9","Sport","Murah",14,"Umum"));

        listFHotel.add(new Hotel("H10","Sport","Lapangan Bola","14.31","Umum"));
        listFHotel.add(new Hotel("H10","Sport","Lapangan Bola","14.31","Umum"));
        listFMakan.add(new Makan("H10","Sport","Makan",14,"Umum"));
        listFKamar.add(new Kamar("H10","Sport","Murah",14,"Umum"));
    }
}
