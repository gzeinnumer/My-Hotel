package com.gzeinnumer.tugasbesarzein;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.gzeinnumer.tugasbesarzein.Adapter.LinearAdapter;
import com.gzeinnumer.tugasbesarzein.Data.DataHotel;
import com.gzeinnumer.tugasbesarzein.Model.ModelHotel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ListLinear extends Fragment {

    View view;

    RecyclerView recyclerViewLinear;
    static LinearAdapter adapterLinear;
    Context context;
    DataHotel data = new DataHotel();
    private RequestQueue mQueue;
    SwipeRefreshLayout swipe;
    final public static String url= "http://androidzein.000webhostapp.com/selectKoordinatsFull.php";

    public ListLinear() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_list_linear, container, false);
        context = view.getContext();
        swipe = view.findViewById(R.id.swipeLinear);

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                data.listHotel.clear();
                if(LGHotel.status.equals("on")){
                    getJSON(url);
                }else if(LGHotel.status.equals("off")){
                    data.initDataOffline();
                }
                initRecyclerView();
                swipe.setRefreshing(false);
            }
        });
        swipe.post(new Runnable() {
            @Override
            public void run() {
                swipe.setRefreshing(true);
                data.listHotel.clear();
                if(LGHotel.status.equals("on")){
                    getJSON(url);
                }else if(LGHotel.status.equals("off")){
                    data.initDataOffline();
                }
                initRecyclerView();
                swipe.setRefreshing(false);
            }
        });
        mQueue = Volley.newRequestQueue(context);
        return view;
    }

    private void initRecyclerView() {
        recyclerViewLinear = view.findViewById(R.id.recyclerViewLinear);
        adapterLinear =new LinearAdapter(view.getContext(),data.listHotel);
        recyclerViewLinear.setAdapter(adapterLinear);
        adapterLinear.notifyDataSetChanged();
        recyclerViewLinear.setLayoutManager(new LinearLayoutManager(context));
    }


    public void getJSON(final String mUrl) {
        data.listHotel.clear();
        data.listFHotel.clear();
        data.listFMakan.clear();
        data.listFKamar.clear();
        class GetJSON extends AsyncTask<Void, Void,String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //Toast.makeText(context,s,Toast.LENGTH_SHORT).show();

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                        url,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONArray jsonArray = response.getJSONArray("hotels");
                                    for(int i =0; i<jsonArray.length(); i++){
                                        JSONObject hotel = jsonArray.getJSONObject(i);
                                        String idHotel = hotel.getString("idHotel");
                                        String nama = hotel.getString("nama");
                                        String alamat= hotel.getString("alamat");
                                        String idFHotel = hotel.getString("idFHotel");
                                        String idFMakan = hotel.getString("idFMakan");
                                        String idFKamar = hotel.getString("idFKamar");
                                        double v0 = hotel.getDouble("v0");
                                        double v1 = hotel.getDouble("v1"); //description
                                        String gambar = hotel.getString("gambar");
                                        String keterangan = hotel.getString("keterangan");
                                        data.listHotel.add(new ModelHotel(idHotel,nama,alamat,idFHotel,idFMakan,idFKamar,v0,v1,gambar,keterangan));
                                        adapterLinear.notifyDataSetChanged();
                                        initRecyclerView();
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
