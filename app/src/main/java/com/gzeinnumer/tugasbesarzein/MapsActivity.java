package com.gzeinnumer.tugasbesarzein;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PointOfInterest;
import com.gzeinnumer.tugasbesarzein.Data.DataHotel;
import com.gzeinnumer.tugasbesarzein.Model.ModelHotel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final int REQUEST_PERMISSION_ACCESS=1;

    DataHotel data = new DataHotel();
    private RequestQueue mQueue;
    final public static String url= "http://androidzein.000webhostapp.com/selectKoordinatsFull.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // custom dialog
                //context hav to declare from main.
                final Dialog dialog = new Dialog(mapFragment.getContext());
                dialog.setContentView(R.layout.alert_dialog_maps);
                dialog.setTitle("Title...");

                Button normal = (Button) dialog.findViewById(R.id.btnNormal);
                Button terrain = (Button) dialog.findViewById(R.id.btnTerrain);
                Button satellite = (Button) dialog.findViewById(R.id.btnSatellite);
                Button hybrid = (Button) dialog.findViewById(R.id.btnHybrid);
                // if button is clicked, close the custom dialog
                normal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        dialog.dismiss();
                    }
                });

                terrain.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                        dialog.dismiss();
                    }
                });

                hybrid.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                        dialog.dismiss();
                    }
                });

                satellite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Intent intent = getIntent();

        String pageIndex = intent.getStringExtra("pageIndex");
        String status = intent.getStringExtra("status");
        if(pageIndex.equals("DetilHotel") && (status.equals("on")||status.equals("off"))){
            String namaTempat = intent.getStringExtra("namaTempat");
            double v0 = intent.getDoubleExtra("v0Index",0);
            double v1 = intent.getDoubleExtra("v1Index",0);

            // Add a marker in Sydney and move the camera
            LatLng marker = new LatLng(v0, v1);
            mMap.addMarker(new MarkerOptions().position(marker).title(namaTempat));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 15));
        } else if (pageIndex.equals("AllHotels") && status.equals("on")){
            Toast.makeText(getApplicationContext(), "Maps Online",Toast.LENGTH_SHORT).show();
            data.listHotel.clear();
            Thread timer = new Thread(){
                @Override
                public void run() {
                    try {
                        getJSON(url);
                        sleep(4111);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                for (int i=0; i<data.listHotel.size(); i++){
                                    ModelHotel modelHotel = data.listHotel.get(i);
                                    LatLng allMarker = new LatLng(modelHotel.getV0(),modelHotel.getV1());
                                    mMap.addMarker(new MarkerOptions().position(allMarker).title(modelHotel.getNama()));
                                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-0.9272514,100.3714457), 13));
                                }
                            }
                        });
                    }
                }
            };
            timer.start();

        } else if(pageIndex.equals("AllHotels") && status.equals("off")){
            data.listHotel.clear();
            data.initDataOffline();
            for (int i=0; i<data.listHotel.size(); i++){
                ModelHotel modelHotel = data.listHotel.get(i);
                LatLng allMarker = new LatLng(modelHotel.getV0(),modelHotel.getV1());
                mMap.addMarker(new MarkerOptions().position(allMarker).title(modelHotel.getNama()));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-0.9272514,100.3714457), 13));
            }
        }
        mQueue = Volley.newRequestQueue(this);

        enableMyLocation();
        setMapLongCLick(mMap);
        setPoiClick(mMap);
    }

    private void enableMyLocation() {
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)==
                PackageManager.PERMISSION_GRANTED){
            mMap.setMyLocationEnabled(true);
        }else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_PERMISSION_ACCESS);
        }

    }



    public void setMapLongCLick(final GoogleMap map){
        map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                String snippet = String.format(Locale.getDefault(),
                        getString(R.string.lat_long_snipet),
                        latLng.latitude,
                        latLng.longitude);
                map.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title(getString(R.string.location))




                        .snippet(snippet)
                        .icon(BitmapDescriptorFactory.defaultMarker
                                (BitmapDescriptorFactory.HUE_BLUE)));
            }
        });
    }

    private void setPoiClick(final GoogleMap map) {
        mMap.setOnPoiClickListener(new GoogleMap.OnPoiClickListener() {
            @Override
            public void onPoiClick(PointOfInterest poi) {
                Marker poiMarker =   mMap.addMarker(new MarkerOptions()
                        .position(poi.latLng)
                        .icon(BitmapDescriptorFactory.defaultMarker
                                (BitmapDescriptorFactory.HUE_ORANGE))
                        .title(poi.name));
                poiMarker.showInfoWindow();
            }
        });

    }




    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.menu_option, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.normal_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                return true;
            case  R.id.terrain_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                return true;
            case R.id.hybrid_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                return true;
            case R.id.satellite_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void onRequestPermissionsResult(int requestCode,
                                           String[]permissions,
                                           int[] grantResults){
        switch (requestCode){
            case REQUEST_PERMISSION_ACCESS:
                if (grantResults.length > 0
                        && grantResults[0]
                        == PackageManager.PERMISSION_GRANTED){
                    enableMyLocation();
                    break;
                }
        }
    }

    public void getJSON(final String mUrl) {
        data.listHotel.clear();
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
