package com.purpletealabs.imdb.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.purpletealabs.imdb.BitMapUtils.ImageLoader;
import com.purpletealabs.imdb.R;
import com.purpletealabs.imdb.config.Constants;
import com.purpletealabs.imdb.models.Image;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.VH> {

    private final ArrayList<Image> mPosters;
    private ImageLoader mImageLoader;

    public ImageAdapter(ArrayList<Image> posters) {
        this.mPosters = posters;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        mImageLoader = new ImageLoader(parent.getContext().getApplicationContext());
        return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_cast_n_crew, parent, false));
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        Image poster = mPosters.get(position);
        mImageLoader.DisplayImage(Constants.IMAGE_URL_PREFIX_150 + poster.getFilePath(), holder.ivPoster);
    }

    @Override
    public int getItemCount() {
        return mPosters.size();
    }

    class VH extends RecyclerView.ViewHolder {
        private ImageView ivPoster;

        VH(View itemView) {
            super(itemView);
            ivPoster = itemView.findViewById(R.id.iv_cast);
            itemView.findViewById(R.id.tv_cast_name).setVisibility(View.GONE);
            itemView.findViewById(R.id.tv_cast_character).setVisibility(View.GONE);
        }
    }
}
