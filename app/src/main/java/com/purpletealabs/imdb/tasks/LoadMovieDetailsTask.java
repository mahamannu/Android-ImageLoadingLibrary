package com.purpletealabs.imdb.tasks;

import android.os.AsyncTask;

import com.purpletealabs.imdb.models.Movie;
import com.purpletealabs.imdb.services.IMDBService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class LoadMovieDetailsTask extends AsyncTask<Long, Void, Movie> {
    private final LoadDetailsListener listener;

    public LoadMovieDetailsTask(LoadDetailsListener listener) {
        this.listener = listener;
    }

    @Override
    protected Movie doInBackground(Long... longs) {
        try {
            IMDBService service = new IMDBService();

            String json = service.getMovieDetails(longs[0]);

            JSONObject object = new JSONObject(json);

            return new Movie(object, true);
            
        } catch (JSONException | IOException ignored) {
            ignored.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Movie movie) {
        super.onPostExecute(movie);
        listener.onMovieDetailsLoaded(movie);
    }

    public interface LoadDetailsListener {
        void onMovieDetailsLoaded(Movie movie);
    }
}