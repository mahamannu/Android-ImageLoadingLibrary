package com.purpletealabs.imdb.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.purpletealabs.imdb.BitMapUtils.ImageLoader;
import com.purpletealabs.imdb.R;
import com.purpletealabs.imdb.models.Video;

import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VH> {
    private final ArrayList<Video> mVideoList;
    private final VideoClickListener mListener;
    private ImageLoader mImageLoader;

    public VideoAdapter(ArrayList<Video> videoList, VideoClickListener listener) {
        mVideoList = videoList;
        mListener = listener;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        mImageLoader = new ImageLoader(parent.getContext().getApplicationContext());
        return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_teaser_or_trailer, parent, false));
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        final Video video = mVideoList.get(position);
        holder.tvVideoTitle.setText(String.format("%s - %s", video.getType(), video.getName()));
        mImageLoader.DisplayImage(String.format("https://img.youtube.com/vi/%s/mqdefault.jpg", video.getKey()), holder.ivVideoThumbnail);
        holder.ivPlayVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onVideoClicked(video);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mVideoList.size();
    }

    class VH extends RecyclerView.ViewHolder {
        private ImageView ivPlayVideo;
        private ImageView ivVideoThumbnail;
        private TextView tvVideoTitle;

        VH(View itemView) {
            super(itemView);
            ivPlayVideo = itemView.findViewById(R.id.iv_play_video);
            ivVideoThumbnail = itemView.findViewById(R.id.iv_video_thumbnail);
            tvVideoTitle = itemView.findViewById(R.id.tv_video_title);
        }
    }

    public interface VideoClickListener {
        void onVideoClicked(Video video);
    }
}
