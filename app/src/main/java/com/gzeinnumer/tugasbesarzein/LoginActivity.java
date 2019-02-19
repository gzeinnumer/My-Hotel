package com.gzeinnumer.tugasbesarzein;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.gzeinnumer.tugasbesarzein.Data.DataHotel;
import com.gzeinnumer.tugasbesarzein.Model.ModelUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText username, passwordLogin;
    String status;
    private RequestQueue mQueue;
    final public static String url= "";
    DataHotel data = new DataHotel();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btnLogin);
        username = findViewById(R.id.email);
        passwordLogin = findViewById(R.id.password);
        mQueue = Volley.newRequestQueue(this);


        final Intent intent = getIntent();
        status = intent.getStringExtra("status");
        if (status.equals("off")) {
            setTitle("Login Offline");
            username.setText("admin");
            passwordLogin.setText("admin");
        }
        else if (status.equals("on")){
            setTitle("Login Online");
            Toast.makeText(getApplicationContext(),"Masukan saja username dan password sembarangan",Toast.LENGTH_LONG).show();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status.equals("off")&&username.getText().toString().equals("admin")&& passwordLogin.getText().toString().equals("admin")){
                    Intent intent1 = new Intent(getApplicationContext(), LGHotel.class);
                    intent1.putExtra("status",status);
                    startActivity(intent1);
                } else if(!username.getText().toString().equals("")||!passwordLogin.getText().toString().equals("")){
                    Intent intent1 = new Intent(getApplicationContext(), LGHotel.class);
                    intent1.putExtra("status",status);
                    startActivity(intent1);
                    //getJSON(url);
                } else {
                    Toast.makeText(getApplicationContext(),"Masukan Username dan Password",Toast.LENGTH_SHORT).show();
                }
            }
        });
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
                //Toast.makeText(context,s,Toast.LENGTH_SHORT).show();

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                        url,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONArray jsonArray = response.getJSONArray("users");
                                    for(int i =0; i<jsonArray.length(); i++){
                                        JSONObject user = jsonArray.getJSONObject(i);
                                        String email = user.getString("email");
                                        String password = user.getString("password");
                                        if (email.equals(username.getText().toString()) && password.equals(passwordLogin.getText().toString())){
                                            Intent intent1 = new Intent(getApplicationContext(), LGHotel.class);
                                            intent1.putExtra("status",status);
                                            startActivity(intent1);
                                        } else {
                                            Toast.makeText(getApplicationContext(),"Username dan Password salah!!",Toast.LENGTH_SHORT).show();
                                        }
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
