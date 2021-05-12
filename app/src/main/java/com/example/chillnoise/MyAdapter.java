package com.example.chillnoise;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter {

    RecyclerView mRecyclerView;
    private ArrayList<NoisesData> mNoisesData;
    LinearLayout mLinearLayout;
    Context con;

    private class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private NoisesData mNoisesData;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.item_image);
        }

        public void Bind(NoisesData noise) {
            mNoisesData = noise;
            mImageView.setImageResource(noise.getImages());
        }
    }

    public MyAdapter(ArrayList<NoisesData> mArray, RecyclerView mRv, Context mcontext, LinearLayout mli) {
        mNoisesData = mArray;
        mRecyclerView = mRv;
        con = mcontext;
        mLinearLayout = mli;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_look, parent, false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLinearLayout.setVisibility(View.VISIBLE);

                    int t = mRecyclerView.getChildAdapterPosition(view);
                Vibrator vibrator = (Vibrator) con.getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(50);
                    Intent it = new Intent(con, ServicePlayingAudio.class);
                    it.putExtra("key", t);
                    con.startService(it);




            }
        });
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NoisesData noisesData = mNoisesData.get(position);
        ((MyViewHolder) holder).Bind(noisesData);
    }

    @Override
    public int getItemCount() {
        return mNoisesData.size();
    }
}
