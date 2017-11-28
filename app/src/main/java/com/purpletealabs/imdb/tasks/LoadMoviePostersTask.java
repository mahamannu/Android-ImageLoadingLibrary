package com.purpletealabs.imdb.tasks;

import android.os.AsyncTask;

import com.purpletealabs.imdb.config.Constants;
import com.purpletealabs.imdb.models.Image;
import com.purpletealabs.imdb.services.IMDBService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;


public class LoadMoviePostersTask extends AsyncTask<Long, Void, ArrayList<Image>> {
    private LoadMoviePostersListener listener;

    public LoadMoviePostersTask(LoadMoviePostersListener listener) {
        this.listener = listener;
    }

    @Override
    protected ArrayList<Image> doInBackground(Long... ids) {
        try {
            IMDBService service = new IMDBService();

            String json = service.getImages(ids[0]);

            JSONObject object = new JSONObject(json);

            JSONArray jsonArray = object.getJSONArray(Constants.FIELD_POSTERS);

            ArrayList<Image> images = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                images.add(new Image(jsonArray.getJSONObject(i)));
            }

            return images;

        } catch (JSONException | IOException ignored) {
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Image> images) {
        super.onPostExecute(images);
        listener.onPostersLoaded(images);
    }

    public interface LoadMoviePostersListener {
        void onPostersLoaded(ArrayList<Image> posters);
    }
}
