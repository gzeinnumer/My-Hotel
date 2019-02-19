package com.gzeinnumer.tugasbesarzein.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gzeinnumer.tugasbesarzein.DataFasilitas.Hotel;
import com.gzeinnumer.tugasbesarzein.R;

import java.util.ArrayList;

public class
AdapterFHotel extends RecyclerView.Adapter<AdapterFHotel.MyHolder>{
    private Context mContext;
    private ArrayList<Hotel> mList = new ArrayList<>();

    public AdapterFHotel(Context mContext, ArrayList<Hotel> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_fasilitas_hotel,viewGroup,false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        Hotel currentItem = mList.get(i);
        myHolder.idFHotel.setText(currentItem.getIdFHotel());
        myHolder.jenisFasilitas.setText(currentItem.getJenisFasilitas());
        myHolder.namaFasilitas.setText(currentItem.getNamaFasilitas());
        myHolder.jamBuka.setText(currentItem.getJamBuka());
        myHolder.keteranganHotel.setText(currentItem.getKeterangan());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        TextView idFHotel,jenisFasilitas, namaFasilitas, jamBuka,keteranganHotel;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            idFHotel = itemView.findViewById(R.id.idFHotel);
            jenisFasilitas = itemView.findViewById(R.id.jenisFasilitas);
            namaFasilitas = itemView.findViewById(R.id.namaFasilitas);
            jamBuka = itemView.findViewById(R.id.jamBuka);
            keteranganHotel = itemView.findViewById(R.id.keteranganHotel);
        }

    }
}
