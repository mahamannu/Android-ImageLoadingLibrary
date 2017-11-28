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
import com.purpletealabs.imdb.models.Crew;

import java.util.ArrayList;

public class CrewAdapter extends RecyclerView.Adapter<CrewAdapter.VH> {
    private final ArrayList<Crew> mCrewList;
    private Context mContext;
    private ImageLoader mImageLoader;

    public CrewAdapter(ArrayList<Crew> crewList) {
        this.mCrewList = crewList;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        mImageLoader = new ImageLoader(mContext.getApplicationContext());
        return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_cast_n_crew, parent, false));
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        Crew crew = mCrewList.get(position);
        holder.tvName.setText(crew.getName());
        holder.tvJob.setText(crew.getJob());
        if (TextUtils.isEmpty(crew.getProfilePath())) {
            holder.ivCastPoster.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.loading_image));
        } else {
            mImageLoader.DisplayImage(Constants.IMAGE_URL_PREFIX_150 + crew.getProfilePath(), holder.ivCastPoster);
        }
    }

    @Override
    public int getItemCount() {
        return mCrewList.size();
    }

    class VH extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvJob;
        private ImageView ivCastPoster;

        VH(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_cast_name);
            tvJob = itemView.findViewById(R.id.tv_cast_character);
            ivCastPoster = itemView.findViewById(R.id.iv_cast);
        }
    }
}
