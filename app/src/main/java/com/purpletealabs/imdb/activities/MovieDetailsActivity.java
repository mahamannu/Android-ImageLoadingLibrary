package com.purpletealabs.imdb.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.purpletealabs.imdb.BitMapUtils.ImageLoader;
import com.purpletealabs.imdb.R;
import com.purpletealabs.imdb.adapters.CastAdapter;
import com.purpletealabs.imdb.adapters.CrewAdapter;
import com.purpletealabs.imdb.adapters.ImageAdapter;
import com.purpletealabs.imdb.adapters.VideoAdapter;
import com.purpletealabs.imdb.config.Constants;
import com.purpletealabs.imdb.models.Cast;
import com.purpletealabs.imdb.models.CastNCrew;
import com.purpletealabs.imdb.models.Crew;
import com.purpletealabs.imdb.models.Image;
import com.purpletealabs.imdb.models.Movie;
import com.purpletealabs.imdb.models.Video;
import com.purpletealabs.imdb.tasks.LoadMovieCastNCrewTask;
import com.purpletealabs.imdb.tasks.LoadMovieDetailsTask;
import com.purpletealabs.imdb.tasks.LoadMoviePostersTask;
import com.purpletealabs.imdb.tasks.LoadMovieVideosTask;

import java.util.ArrayList;

public class MovieDetailsActivity extends AppCompatActivity implements
        LoadMovieDetailsTask.LoadDetailsListener, LoadMovieCastNCrewTask.LoadCastNCrewListener,
        LoadMoviePostersTask.LoadMoviePostersListener, LoadMovieVideosTask.LoadVideosListener, VideoAdapter.VideoClickListener {

    private Movie mMovie;

    private AsyncTask<Long, Void, Movie> mLoadMovieDetailsTask;

    private LoadMovieCastNCrewTask mLoadCastNCrew;

    private RecyclerView mRvCast;

    private RecyclerView mRvCrew;

    private RecyclerView mRvVideoList;

    private CastAdapter mCastAdapter;

    private ArrayList<Cast> mCastList = new ArrayList<>();

    private ArrayList<Crew> mCrewList = new ArrayList<>();

    private CrewAdapter mCrewAdapter;

    private ArrayList<Video> mVideoList = new ArrayList<>();

    private VideoAdapter mVideoAdapter;

    private LoadMovieVideosTask mLoadVideosTask;

    private RecyclerView mRvPosters;

    private ImageAdapter mPosterAdapter;

    private ArrayList<Image> mPosters = new ArrayList<>();

    private LoadMoviePostersTask mLoadMoviePostersTask;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        mMovie = getIntent().getParcelableExtra(Constants.EXTRA_MOVIE);

        setToolbar();

        loadMovieDetails();
    }

    private void setToolbar() {
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setTitle(mMovie.getTitle());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadMovieDetails() {
        findViewById(R.id.ll_movie_root).setVisibility(View.GONE);
        findViewById(R.id.pb_loading_movie_details).setVisibility(View.VISIBLE);
        mLoadMovieDetailsTask = new LoadMovieDetailsTask(this);
        mLoadMovieDetailsTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mMovie.getId());
    }

    @Override
    public void onMovieDetailsLoaded(Movie movie) {
        findViewById(R.id.pb_loading_movie_details).setVisibility(View.GONE);
        if (movie == null) {
            Toast.makeText(this, "Oops, Something went wrong! Please try again.", Toast.LENGTH_SHORT).show();
        } else {
            findViewById(R.id.ll_movie_root).setVisibility(View.VISIBLE);
            mMovie = movie;
            initViews();
        }
    }

    private void initViews() {
        ImageLoader imageLoader = new ImageLoader(getApplicationContext());
        ImageView ivMainPoster = findViewById(R.id.iv_main_poster);
        imageLoader.DisplayImage(Constants.IMAGE_URL_PREFIX_300 + mMovie.getPosterPath(), ivMainPoster);
        TextView tvTitle = findViewById(R.id.tv_title);
        tvTitle.setText(mMovie.getTitle());
        TextView tvTagLine = findViewById(R.id.tv_tag_line);
        if (TextUtils.isEmpty(mMovie.getTagLine())) {
            tvTagLine.setVisibility(View.GONE);
        } else {
            tvTagLine.setText(mMovie.getTagLine());
        }
        RatingBar rbRating = findViewById(R.id.rb_movie_rating);
        rbRating.setRating((float) (mMovie.getVoteAverage() / 2));
        TextView tvReleaseDate = findViewById(R.id.tv_release_date);
        tvReleaseDate.setText(String.format("Release Date: %s", mMovie.getReleaseDate()));
        TextView tvRating = findViewById(R.id.tv_rating);
        tvRating.setText(String.format("Rated (%s/10) by %s users", mMovie.getVoteAverage(), mMovie.getVoteCount()));
        TextView tvAgeGroup = findViewById(R.id.tv_age_group);
        tvAgeGroup.setText(String.format("Age Group: %s", mMovie.isAdult() ? "A" : "UA"));
        TextView tvOverView = findViewById(R.id.tv_overview);
        tvOverView.setText(mMovie.getOverview());
        mRvCast = findViewById(R.id.rv_cast);
        mRvCrew = findViewById(R.id.rv_crew);
        mRvVideoList = findViewById(R.id.rv_videos);
        mRvPosters = findViewById(R.id.rv_posters);
        mRvPosters.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRvCast.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRvCrew.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRvVideoList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mPosterAdapter = new ImageAdapter(mPosters);
        mRvPosters.setAdapter(mPosterAdapter);
        mCastAdapter = new CastAdapter(mCastList);
        mCrewAdapter = new CrewAdapter(mCrewList);
        mVideoAdapter = new VideoAdapter(mVideoList, this);
        mRvVideoList.setAdapter(mVideoAdapter);
        mRvCrew.setAdapter(mCrewAdapter);
        mRvCast.setAdapter(mCastAdapter);
        loadPostersOfMovie();
        loadCastAndCrew();
        loadTeasersAndTrailers();
    }

    private void loadPostersOfMovie() {
        mRvPosters.setVisibility(View.GONE);
        findViewById(R.id.pb_loading_posters).setVisibility(View.VISIBLE);
        mLoadMoviePostersTask = new LoadMoviePostersTask(this);
        mLoadMoviePostersTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mMovie.getId());
    }

    @Override
    public void onPostersLoaded(ArrayList<Image> posters) {
        findViewById(R.id.pb_loading_posters).setVisibility(View.GONE);
        if (posters == null) {
            Toast.makeText(this, "Oops, Something went wrong! Please try again.", Toast.LENGTH_SHORT).show();
        } else {
            mRvPosters.setVisibility(View.VISIBLE);
            mPosters.clear();
            mPosters.addAll(posters);
            mPosterAdapter.notifyDataSetChanged();
        }
    }

    private void loadTeasersAndTrailers() {
        mRvVideoList.setVisibility(View.GONE);
        findViewById(R.id.pb_loading_videos).setVisibility(View.VISIBLE);
        mLoadVideosTask = new LoadMovieVideosTask(this);
        mLoadVideosTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mMovie.getId());
    }

    @Override
    public void onVideosLoaded(ArrayList<Video> videos) {
        findViewById(R.id.pb_loading_videos).setVisibility(View.GONE);
        if (videos == null) {
            Toast.makeText(this, "Oops, Something went wrong! Please try again.", Toast.LENGTH_SHORT).show();
        } else {
            mRvVideoList.setVisibility(View.VISIBLE);
            mVideoList.clear();
            mVideoList.addAll(videos);
            mVideoAdapter.notifyDataSetChanged();
        }
    }

    private void loadCastAndCrew() {
        mRvCast.setVisibility(View.GONE);
        mRvCrew.setVisibility(View.GONE);
        findViewById(R.id.pb_loading_cast).setVisibility(View.VISIBLE);
        findViewById(R.id.pb_loading_crew).setVisibility(View.VISIBLE);
        mLoadCastNCrew = new LoadMovieCastNCrewTask(this);
        mLoadCastNCrew.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mMovie.getImdbId());
    }

    @Override
    public void onCastNCrewLoaded(CastNCrew castNCrew) {
        findViewById(R.id.pb_loading_cast).setVisibility(View.GONE);
        findViewById(R.id.pb_loading_crew).setVisibility(View.GONE);
        if (castNCrew == null) {
            Toast.makeText(this, "Oops, Something went wrong! Please try again.", Toast.LENGTH_SHORT).show();
        } else {
            mRvCast.setVisibility(View.VISIBLE);
            mCastList.clear();
            mCastList.addAll(castNCrew.getCast());
            mCastAdapter.notifyDataSetChanged();

            mRvCrew.setVisibility(View.VISIBLE);
            mCrewList.clear();
            mCrewList.addAll(castNCrew.getCrew());
            mCrewAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onDestroy() {
        if (mLoadMovieDetailsTask != null)
            mLoadMovieDetailsTask.cancel(true);
        if (mLoadCastNCrew != null)
            mLoadCastNCrew.cancel(true);
        if (mLoadVideosTask != null)
            mLoadVideosTask.cancel(true);
        if (mLoadMoviePostersTask != null)
            mLoadMoviePostersTask.cancel(true);
        super.onDestroy();
    }

    @Override
    public void onVideoClicked(Video video) {
        Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format("https://www.youtube.com/watch?v=%s", video.getKey())));
        startActivity(webIntent);
    }
}
