package com.purpletealabs.imdb.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.purpletealabs.imdb.BitMapUtils.ImageLoader;
import com.purpletealabs.imdb.R;
import com.purpletealabs.imdb.config.Constants;
import com.purpletealabs.imdb.models.Cast;

import java.util.ArrayList;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.VH> {
    private ArrayList<Cast> mCastList;
    private ImageLoader mImageLoader;
    private Context mContext;

    public CastAdapter(ArrayList<Cast> castList) {
        this.mCastList = castList;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        mImageLoader = new ImageLoader(mContext.getApplicationContext());
        return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_cast_n_crew, parent, false));
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        Cast cast = mCastList.get(position);
        holder.tvName.setText(cast.getName());
        holder.tvCharacter.setText(cast.getCharacter());
        if (TextUtils.isEmpty(cast.getProfilePath())) {
            holder.ivCastPoster.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.loading_image));
        } else {
            mImageLoader.DisplayImage(Constants.IMAGE_URL_PREFIX_150 + cast.getProfilePath(), holder.ivCastPoster);
        }
    }

    @Override
    public int getItemCount() {
        return mCastList.size();
    }

    class VH extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvCharacter;
        private ImageView ivCastPoster;

        VH(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_cast_name);
            tvCharacter = itemView.findViewById(R.id.tv_cast_character);
            ivCastPoster = itemView.findViewById(R.id.iv_cast);
        }
    }
}
