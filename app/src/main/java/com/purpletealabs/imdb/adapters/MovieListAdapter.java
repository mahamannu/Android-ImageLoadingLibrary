package com.purpletealabs.imdb.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.purpletealabs.imdb.BitMapUtils.ImageLoader;
import com.purpletealabs.imdb.R;
import com.purpletealabs.imdb.config.Constants;
import com.purpletealabs.imdb.models.Movie;

import java.util.ArrayList;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.VH> {
    private final ArrayList<Movie> mList;
    private ImageLoader mImageLoader;
    private MovieClickListener mListener;

    public MovieListAdapter(ArrayList<Movie> list, MovieClickListener listener) {
        mList = list;
        mListener = listener;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        mImageLoader = new ImageLoader(parent.getContext().getApplicationContext());
        return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_upcoming_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        Movie movie = mList.get(position);
        holder.tvTitle.setText(movie.getTitle());
        holder.tvReleaseDate.setText(String.format("Release Date: %s", movie.getReleaseDate()));
        holder.rbMovieRating.setRating((float) (movie.getVoteAverage() / 2));
        holder.tvRating.setText(String.format("Rated (%s/10) by %s users", movie.getVoteAverage(), movie.getVoteCount()));
        holder.tvAgeGroup.setText(String.format("Age Group: %s", movie.isAdult() ? "A" : "UA"));
        mImageLoader.DisplayImage(Constants.IMAGE_URL_PREFIX_150 + movie.getPosterPath(), holder.posterImage);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class VH extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvReleaseDate;
        private TextView tvRating;
        private RatingBar rbMovieRating;
        private ImageView posterImage;
        private TextView tvAgeGroup;

        VH(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvReleaseDate = itemView.findViewById(R.id.tv_release_date);
            tvRating = itemView.findViewById(R.id.tv_rating);
            rbMovieRating = itemView.findViewById(R.id.rb_row_upcoming_movie_rating);
            posterImage = itemView.findViewById(R.id.iv_row_upcoming_movie_poster);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onMovieClicked(mList.get(getAdapterPosition()));
                }
            });
            tvAgeGroup = itemView.findViewById(R.id.tv_age_group);
        }
    }

    public interface MovieClickListener {
        void onMovieClicked(Movie movie);
    }
}
