package com.gzeinnumer.tugasbesarzein.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gzeinnumer.tugasbesarzein.DataFasilitas.Makan;
import com.gzeinnumer.tugasbesarzein.R;

import java.util.ArrayList;

public class AdapterFMakan extends RecyclerView.Adapter<AdapterFMakan.MyHolder> {
    private Context mContext;
    private ArrayList<Makan> mList = new ArrayList<>();

    public AdapterFMakan(Context mContext, ArrayList<Makan> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_fasilitas_makan,viewGroup,false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        Makan currentItem = mList.get(i);
        myHolder.idFMakan.setText(currentItem.getIdFMakan());
        myHolder.typeMakanan.setText(currentItem.getTypeMakanan());
        myHolder.menu.setText(currentItem.getMenu());
        myHolder.hargaMakan.setText("Rp."+String.valueOf(currentItem.getHarga()));
        myHolder.keteranganMakan.setText("Keterangan : "+currentItem.getKeterangan());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        TextView idFMakan, typeMakanan, menu, hargaMakan, keteranganMakan;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            idFMakan = itemView.findViewById(R.id.idFMakan);
            typeMakanan = itemView.findViewById(R.id.typeMakanan);
            menu = itemView.findViewById(R.id.menu);
            hargaMakan = itemView.findViewById(R.id.hargaMakan);
            keteranganMakan = itemView.findViewById(R.id.keteranganMakan);
        }

    }
}
