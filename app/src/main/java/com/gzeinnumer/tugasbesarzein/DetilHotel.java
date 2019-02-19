package com.gzeinnumer.tugasbesarzein;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.gzeinnumer.tugasbesarzein.Adapter.AdapterFHotel;
import com.gzeinnumer.tugasbesarzein.Adapter.AdapterFKamar;
import com.gzeinnumer.tugasbesarzein.Adapter.AdapterFMakan;
import com.gzeinnumer.tugasbesarzein.Data.DataHotel;
import com.gzeinnumer.tugasbesarzein.DataFasilitas.Hotel;
import com.gzeinnumer.tugasbesarzein.DataFasilitas.Kamar;
import com.gzeinnumer.tugasbesarzein.DataFasilitas.Makan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

public class DetilHotel extends AppCompatActivity {

    ImageView imgDet;
    TextView namaDet, alamatDet,descDet, temp1, temp2;
    CircularProgressButton btnMaps;

    String idHotelIndex;
    String namaIndex;
    String alamatIndex;
    String idFHotelIndex;
    String idFMakanIndex;
    String idFKamarIndex;
    double v0Index;
    double v1Index;
    String gambarIndex;
    String keteranganIndex;

    AdapterFHotel adapterFHotel;
    AdapterFMakan adapterFMakan;
    AdapterFKamar adapterFKamar;

    DataHotel data = new DataHotel();

    private RequestQueue mQueue;

    final public static String urlHotel= "https://androidzein.000webhostapp.com/selectKoordinatsFull_Hotel.php";
    final public static String urlMakan= "https://androidzein.000webhostapp.com/selectKoordinatsFull_Makan.php";
    final public static String urlKamar= "https://androidzein.000webhostapp.com/selectKoordinatsFull_Kamar.php";

    RecyclerView recyclerFHotel, recyclerFMakan,recyclerFKamar;
    AsyncTask<String, String, String> demoDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detil_hotel);

        imgDet = findViewById(R.id.imgDet);
        namaDet = findViewById(R.id.namaDet);
        alamatDet = findViewById(R.id.alamatDet);
        descDet = findViewById(R.id.descDet);
        btnMaps = findViewById(R.id.btnMaps);
        temp1 = findViewById(R.id.v1);
        temp2 = findViewById(R.id.v2);
        recyclerFHotel = findViewById(R.id.recyclerFHotel);
        recyclerFMakan = findViewById(R.id.recyclerFMakan);
        recyclerFKamar = findViewById(R.id.recyclerFKamar);

        Intent intent = getIntent();

        idHotelIndex = intent.getStringExtra("idHotel");
        namaIndex = intent.getStringExtra("nama");
        alamatIndex = intent.getStringExtra("alamat");
        idFHotelIndex = intent.getStringExtra("idFHotel");
        idFMakanIndex = intent.getStringExtra("idFMakan");
        idFKamarIndex = intent.getStringExtra("idFKamar");
        v0Index = intent.getDoubleExtra("v0",0);
        v1Index = intent.getDoubleExtra("v1",0);
        gambarIndex = intent.getStringExtra("gambar");
        keteranganIndex = intent.getStringExtra("keterangan");

        Glide.with(this).load(gambarIndex).into(imgDet);
        namaDet.setText(namaIndex);
        alamatDet.setText(alamatIndex);
        descDet.setText(keteranganIndex);
        temp1.setText(String.valueOf(v0Index));
        temp2.setText(String.valueOf(v1Index));

        initRecycler();

        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                demoDownload = new AsyncTask<String, String, String>() {
                    @Override
                    protected String doInBackground(String... voids) {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return "Done";
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        if(s.equals("Done")){
                            Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                            intent.putExtra("namaTempat", namaIndex);
                            intent.putExtra("v0Index", v0Index);
                            intent.putExtra("v1Index", v1Index);
                            intent.putExtra("pageIndex","DetilHotel");
                            intent.putExtra("status",LGHotel.status);
                            startActivity(intent);
                            btnMaps.doneLoadingAnimation(R.color.colorPrimaryDark,BitmapFactory.decodeResource(getResources(), R.drawable.ic_done_white_48dp));
                        }
                    }
                };
                btnMaps.startAnimation();
                demoDownload.execute();
            }
        });

        getJSON(urlHotel);
        //getJSON("http://androidzein.000webhostapp.com/selectKoordinatsFull.php");
        getJSON(urlMakan);
        getJSON(urlKamar);

        mQueue = Volley.newRequestQueue(this);
    }


    private void initRecycler() {
        adapterFHotel = new AdapterFHotel(this, data.listFHotel);
        recyclerFHotel.setAdapter(adapterFHotel);
        recyclerFHotel.setLayoutManager(new LinearLayoutManager(this));

        adapterFMakan = new AdapterFMakan(this, data.listFMakan);
        recyclerFMakan.setAdapter(adapterFMakan);
        recyclerFMakan.setLayoutManager(new LinearLayoutManager(this));

        adapterFKamar = new AdapterFKamar(this, data.listFKamar);
        recyclerFKamar.setAdapter(adapterFKamar);
        recyclerFKamar.setLayoutManager(new LinearLayoutManager(this));
    }


    public void getJSON(final String mUrl) {
        class GetJSON extends AsyncTask<Void, Void,String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
                JsonObjectRequest request = null;
                switch (mUrl){
                    case "https://androidzein.000webhostapp.com/selectKoordinatsFull_Hotel.php":
                        request = new JsonObjectRequest(Request.Method.POST,
                                mUrl,
                                null,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        try {
                                            JSONArray jsonArray = response.getJSONArray("fasilitasHotels");
                                            for(int i =0; i<jsonArray.length(); i++){
                                                JSONObject hotel = jsonArray.getJSONObject(i);
                                                if(hotel.getString("idFHotel").equals(idFHotelIndex)){
                                                    String idFHotel = hotel.getString("idFHotel");
                                                    String jenisFasilitas = hotel.getString("jenisFasilitas");
                                                    String namaFasilitas= hotel.getString("namaFasilitas");
                                                    String jamBuka = hotel.getString("jamBuka");
                                                    String keterangan = hotel.getString("keterangan");
                                                    data.listFHotel.add(new Hotel(idFHotel,jenisFasilitas,namaFasilitas,jamBuka,keterangan));
                                                }

                                                adapterFHotel.notifyDataSetChanged();
                                                initRecycler();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });
                        break;
                    case "https://androidzein.000webhostapp.com/selectKoordinatsFull_Makan.php":
                        request = new JsonObjectRequest(Request.Method.POST,
                                mUrl,
                                null,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        try {
                                            JSONArray jsonArray = response.getJSONArray("fasilitasMakan");
                                            for(int i =0; i<jsonArray.length(); i++){
                                                JSONObject makan = jsonArray.getJSONObject(i);
                                                if(makan.getString("idFMakan").equals(idFMakanIndex)) {
                                                    String idFMakan = makan.getString("idFMakan");
                                                    String typeMakanan = makan.getString("typeMakanan");
                                                    String menu = makan.getString("menu");
                                                    double harga = makan.getDouble("harga");
                                                    String keterangan = makan.getString("keterangan");
                                                    data.listFMakan.add(new Makan(idFMakan, typeMakanan, menu, harga, keterangan));
                                                }
                                                adapterFMakan.notifyDataSetChanged();
                                                initRecycler();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });
                        break;
                    case "https://androidzein.000webhostapp.com/selectKoordinatsFull_Kamar.php":
                        request = new JsonObjectRequest(Request.Method.POST,
                                mUrl,
                                null,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        try {
                                            JSONArray jsonArray = response.getJSONArray("fasilitasKamar");
                                            for(int i =0; i<jsonArray.length(); i++){
                                                JSONObject kamar = jsonArray.getJSONObject(i);
                                                if(kamar.getString("idFKamar").equals(idFKamarIndex)) {
                                                    String idFKamar = kamar.getString("idFKamar");
                                                    String typeKamar = kamar.getString("typeKamar");
                                                    String fasilitasKamar = kamar.getString("fasilitasKamar");
                                                    double harga = kamar.getDouble("harga");
                                                    String keterangan = kamar.getString("keterangan");
                                                    data.listFKamar.add(new Kamar(idFKamar, typeKamar, fasilitasKamar, harga, keterangan));
                                                }
                                                adapterFKamar.notifyDataSetChanged();
                                                initRecycler();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });
                        break;
                }
                mQueue.add(request);
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(mUrl);

                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    StringBuilder sb = new StringBuilder();

                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    String json;

                    while ((json = bufferedReader.readLine())!=null){
                        sb.append(json + "\n");
                    }

                    return sb.toString().trim();
                }  catch (Exception e){
                    return null;
                }
            }
        }

        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }
}
