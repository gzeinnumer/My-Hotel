package com.gzeinnumer.tugasbesarzein.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gzeinnumer.tugasbesarzein.DataFasilitas.Hotel;
import com.gzeinnumer.tugasbesarzein.DataFasilitas.Kamar;
import com.gzeinnumer.tugasbesarzein.R;

import java.util.ArrayList;

public class AdapterFKamar extends RecyclerView.Adapter<AdapterFKamar.MyHolder>{
    private Context mContext;
    private ArrayList<Kamar> mList = new ArrayList<>();

    public AdapterFKamar(Context mContext, ArrayList<Kamar> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_fasilitas_kamar,viewGroup,false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        Kamar currentItem = mList.get(i);
        myHolder.idFKamar.setText(currentItem.getIdFKamar());
        myHolder.typeKamar.setText(currentItem.getTypeKamar());
        myHolder.fasilitasKamar.setText(currentItem.getFasilitasKamar());
        myHolder.hargaKamar.setText(String.valueOf(currentItem.getHarga()));
        myHolder.keteranganKamar.setText("Keterangan : "+currentItem.getKeterangan());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        TextView idFKamar,typeKamar, fasilitasKamar, hargaKamar,keteranganKamar;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            idFKamar = itemView.findViewById(R.id.idFKamar);
            typeKamar = itemView.findViewById(R.id.typeKamar);
            fasilitasKamar = itemView.findViewById(R.id.fasilitasKamar);
            hargaKamar = itemView.findViewById(R.id.hargaKamar);
            keteranganKamar = itemView.findViewById(R.id.keteranganKamar);
        }

    }
}
