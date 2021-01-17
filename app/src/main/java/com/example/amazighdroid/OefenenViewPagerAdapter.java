package com.example.amazighdroid;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;

public class OefenenViewPagerAdapter extends RecyclerView.Adapter<OefenenViewPagerAdapter.ViewHolder> {

    private List<String> mDataAM;
    private List<String> mDataNL;
    private List<String> mDataIMG;
    private List<String> mDataMP3;
    private LayoutInflater mInflater;
    private ViewPager2 viewPager2;

    private int[] colorArray = new int[]{android.R.color.black, android.R.color.holo_blue_dark, android.R.color.holo_green_dark, android.R.color.holo_red_dark};

    OefenenViewPagerAdapter(Context context, List<String> listNL, List<String> listAM, List<String> listIMG, List<String> listMP3, ViewPager2 viewPager2) {
        this.mInflater = LayoutInflater.from(context);
        this.mDataAM = listAM;
        this.mDataNL = listNL;
        this.mDataIMG = listIMG;
        this.mDataMP3 = listMP3;
        this.viewPager2 = viewPager2;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.oefenen_viewpager, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String am = mDataAM.get(position);
        String nl = mDataNL.get(position);
        String img = mDataIMG.get(position);
        String mp3 = mDataMP3.get(position);
        holder.tvAM.setText(am);
        holder.tvNL.setText(nl);
        holder.relativeLayout.setBackgroundResource(colorArray[position]);
        Glide.with(holder.imageView.getContext())
                .load(img)
                .into(holder.imageView);

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mp = new MediaPlayer();
                try {
                    mp.setDataSource(mp3);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    mp.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mp.start();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataAM.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvAM;
        TextView tvNL;
        RelativeLayout relativeLayout;
        Button button;
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            tvAM = itemView.findViewById(R.id.tvAM);
            tvNL = itemView.findViewById(R.id.tvNL);
            relativeLayout = itemView.findViewById(R.id.container);
            button = itemView.findViewById(R.id.btnToggle);
            imageView = itemView.findViewById(R.id.imageView2);
        }
    }
}