package com.purpletealabs.imdb.tasks;

import android.os.AsyncTask;

import com.purpletealabs.imdb.config.Constants;
import com.purpletealabs.imdb.models.Video;
import com.purpletealabs.imdb.services.IMDBService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class LoadMovieVideosTask extends AsyncTask<Long, Void, ArrayList<Video>> {
    private LoadVideosListener listener;

    public LoadMovieVideosTask(LoadVideosListener listener) {
        this.listener = listener;
    }

    @Override
    protected ArrayList<Video> doInBackground(Long... movieIds) {
        try {
            IMDBService imdbService = new IMDBService();

            String json = imdbService.getVideos(movieIds[0]);
            JSONObject jsonObj = new JSONObject(json);
            JSONArray jsonArray = jsonObj.getJSONArray(Constants.FIELD_RESULTS);
            ArrayList<Video> videos = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                videos.add(new Video(jsonArray.getJSONObject(i)));
            }
            return videos;
        } catch (IOException | JSONException ignored) {
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Video> videos) {
        super.onPostExecute(videos);
        listener.onVideosLoaded(videos);
    }

    public interface LoadVideosListener {
        void onVideosLoaded(ArrayList<Video> videos);
    }
}
