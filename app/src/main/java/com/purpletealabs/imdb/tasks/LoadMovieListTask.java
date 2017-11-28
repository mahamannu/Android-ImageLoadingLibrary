package com.purpletealabs.imdb.tasks;

import android.os.AsyncTask;

import com.purpletealabs.imdb.config.Constants;
import com.purpletealabs.imdb.models.Movie;
import com.purpletealabs.imdb.services.IMDBService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadMovieListTask extends AsyncTask<Void, Void, List<Movie>> {
    private final LoadMoviesListListener listener;

    public LoadMovieListTask(LoadMoviesListListener listener) {
        this.listener = listener;
    }

    @Override
    protected List<Movie> doInBackground(Void... voids) {
        try {
            IMDBService imdbService = new IMDBService();

            String json = imdbService.getUpcomingMovies();

            JSONObject jsonObj = new JSONObject(json);
            JSONArray moviesJsonArray = jsonObj.getJSONArray(Constants.FIELD_RESULTS);
            List<Movie> movies = new ArrayList<>();
            for (int i = 0; i < moviesJsonArray.length(); i++) {
                movies.add(new Movie(moviesJsonArray.getJSONObject(i), false));
            }
            return movies;
        } catch (IOException | JSONException ignored) {
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Movie> movies) {
        super.onPostExecute(movies);
        listener.onLoadMovieListLoaded(movies);
    }

    public interface LoadMoviesListListener {
        void onLoadMovieListLoaded(List<Movie> movies);
    }
}