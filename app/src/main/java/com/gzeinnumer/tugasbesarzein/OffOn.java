package com.gzeinnumer.tugasbesarzein;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

public class OffOn extends AppCompatActivity {

    CircularProgressButton btnOff;
    CircularProgressButton btnOn;
    AsyncTask<String, String, String> demoDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_off_on);

        btnOff= findViewById(R.id.btnOffline);
        btnOn= findViewById(R.id.btnOnline);
        btnOff.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onClick(View v) {
                demoDownload = new AsyncTask<String, String, String>() {
                    @Override
                    protected String doInBackground(String... voids) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return "Done";
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        if(s.equals("Done")){
                            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                            intent.putExtra("status","off");
                            startActivity(intent);
                            btnOff.doneLoadingAnimation(R.color.colorPrimaryDark,BitmapFactory.decodeResource(getResources(), R.drawable.ic_done_white_48dp));
                        }
                    }
                };
                btnOff.startAnimation();
                demoDownload.execute();
            }
        });
        btnOn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("StaticFieldLeak")
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
                        if (s.equals("Done")) {
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            intent.putExtra("status","on");
                            startActivity(intent);
                            btnOn.doneLoadingAnimation(R.color.colorPrimaryDark, BitmapFactory.decodeResource(getResources(), R.drawable.ic_done_white_48dp));
                        }
                    }
                };
                btnOn.startAnimation();
                demoDownload.execute();
            }
        });
    }

}
