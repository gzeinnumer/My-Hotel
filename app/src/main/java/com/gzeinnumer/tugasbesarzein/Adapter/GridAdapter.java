package com.gzeinnumer.tugasbesarzein.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gzeinnumer.tugasbesarzein.DetilHotel;
import com.gzeinnumer.tugasbesarzein.Model.ModelHotel;
import com.gzeinnumer.tugasbesarzein.R;

import java.util.ArrayList;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.MyHolder>{
    private Context mContext;
    private ArrayList<ModelHotel> mList = new ArrayList<>();

    public GridAdapter(Context context, ArrayList<ModelHotel> list) {
        this.mContext = context;
        this.mList = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_grid,null);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int i) {
        final ModelHotel model = mList.get(i);
        Glide.with(mContext).load(model.getGambar()).into(myHolder.imgListIndex);
        myHolder.nameListIndex.setText(model.getNama());
        myHolder.descListIndex.setText(model.getKeterangan());
        myHolder.alamatListIndex.setText(model.getAlamat());
        myHolder.ratingBarIndex.setRating((float) 4.5);
        myHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetilHotel.class);
                intent.putExtra("idHotel",model.getIdHotel());
                intent.putExtra("nama",model.getNama());
                intent.putExtra("alamat",model.getAlamat());
                intent.putExtra("idFHotel",model.getIdFHotel());
                intent.putExtra("idFMakan",model.getIdFMakan());
                intent.putExtra("idFKamar",model.getIdFKamar());
                intent.putExtra("v0",model.getV0());
                intent.putExtra("v1",model.getV1());
                intent.putExtra("gambar",model.getGambar());
                intent.putExtra("keterangan",model.getKeterangan());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        RelativeLayout relativeLayout;
        ImageView imgListIndex;
        TextView nameListIndex, descListIndex, alamatListIndex;
        RatingBar ratingBarIndex;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            relativeLayout = itemView.findViewById(R.id.parentList);
            imgListIndex = itemView.findViewById(R.id.imgList);
            nameListIndex = itemView.findViewById(R.id.nameList);
            descListIndex = itemView.findViewById(R.id.descList);
            alamatListIndex = itemView.findViewById(R.id.alamatList);
            ratingBarIndex = itemView.findViewById(R.id.ratingList);
        }
    }
}
