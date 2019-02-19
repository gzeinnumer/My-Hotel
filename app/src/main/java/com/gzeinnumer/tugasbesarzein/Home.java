package com.gzeinnumer.tugasbesarzein;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Home extends Fragment {

    View view;
    String mStatus;


    CardView cardAllHotels;
    CardView cardMe;
    CardView cardAbout;
    CardView cardExit;

    public Home() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_home, container, false);
        cardAllHotels = view.findViewById(R.id.cardAllHotels);
        cardMe = view.findViewById(R.id.cardMe);
        cardAbout = view.findViewById(R.id.cardAbout);
        cardExit = view.findViewById(R.id.cardExit);

        cardAllHotels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(),MapsActivity.class);
                intent.putExtra("pageIndex","AllHotels");
                intent.putExtra("status",LGHotel.status);
                view.getContext().startActivity(intent);
            }
        });
        cardMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(view.getContext());
                dialog.setContentView(R.layout.dialog_me);
                dialog.show();
            }
        });
        cardAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(view.getContext());
                dialog.setContentView(R.layout.dialog_about);
                dialog.show();
            }
        });
        cardExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().moveTaskToBack(true);
                getActivity().finish();
            }
        });
        return view;
    }


}
