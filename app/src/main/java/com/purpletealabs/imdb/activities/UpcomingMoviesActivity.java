package com.purpletealabs.imdb.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.purpletealabs.imdb.R;
import com.purpletealabs.imdb.adapters.MovieListAdapter;
import com.purpletealabs.imdb.config.Constants;
import com.purpletealabs.imdb.models.Movie;
import com.purpletealabs.imdb.tasks.LoadMovieListTask;

import java.util.ArrayList;
import java.util.List;

public class UpcomingMoviesActivity extends AppCompatActivity
        implements LoadMovieListTask.LoadMoviesListListener,
        MovieListAdapter.MovieClickListener {

    private static final String EXTRA_MOVIES = "movieList";

    private View mPbLoadingMovieList;
    private RecyclerView mRvUpcomingMovieList;
    private ArrayList<Movie> mMovieList = new ArrayList<>();
    private MovieListAdapter mMovieListAdapter;
    private LoadMovieListTask mLoadMovieListTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_movie_list);

        initViews();

        if (savedInstanceState == null) {
            loadMovieListFromNetwork();
        } else {
            mPbLoadingMovieList.setVisibility(View.GONE);
            ArrayList<Movie> fromParcel = savedInstanceState.getParcelableArrayList(EXTRA_MOVIES);
            mMovieList.clear();
            mMovieList.addAll(fromParcel);
            mMovieListAdapter.notifyDataSetChanged();
        }
    }

    private void initViews() {
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(getString(R.string.upcoming_movies));
        }
        mPbLoadingMovieList = findViewById(R.id.pb_loading_movie_list);
        mRvUpcomingMovieList = findViewById(R.id.rv_upcoming_movie_list);
        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            mRvUpcomingMovieList.setLayoutManager(new LinearLayoutManager(this));
        } else {
            mRvUpcomingMovieList.setLayoutManager(new GridLayoutManager(this, 2));
        }
        mMovieListAdapter = new MovieListAdapter(mMovieList, this);
        mRvUpcomingMovieList.setAdapter(mMovieListAdapter);
    }

    private void loadMovieListFromNetwork() {
        mMovieList.clear();
        mMovieListAdapter.notifyDataSetChanged();
        mRvUpcomingMovieList.setVisibility(View.GONE);
        mPbLoadingMovieList.setVisibility(View.VISIBLE);
        mLoadMovieListTask = new LoadMovieListTask(this);
        mLoadMovieListTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onLoadMovieListLoaded(List<Movie> movies) {
        mPbLoadingMovieList.setVisibility(View.GONE);
        mRvUpcomingMovieList.setVisibility(View.VISIBLE);
        if (movies == null) {
            Toast.makeText(this, "Oops, Something went wrong! Please try again.", Toast.LENGTH_SHORT).show();
        } else {
            mMovieList.clear();
            mMovieList.addAll(movies);
            mMovieListAdapter.notifyDataSetChanged();
            if (!mMovieList.isEmpty()) {
                mRvUpcomingMovieList.scrollToPosition(0);
            }
        }
    }

    @Override
    protected void onDestroy() {
        if (mLoadMovieListTask != null) {
            mLoadMovieListTask.cancel(true);
        }
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(EXTRA_MOVIES, mMovieList);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onMovieClicked(Movie movie) {
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra(Constants.EXTRA_MOVIE, movie);
        startActivity(intent);
    }
}