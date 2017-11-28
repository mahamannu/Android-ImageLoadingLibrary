package com.purpletealabs.imdb.tasks;

import android.os.AsyncTask;

import com.purpletealabs.imdb.models.CastNCrew;
import com.purpletealabs.imdb.services.IMDBService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class LoadMovieCastNCrewTask extends AsyncTask<String, Void, CastNCrew> {
    private LoadCastNCrewListener listener;

    public LoadMovieCastNCrewTask(LoadCastNCrewListener listener) {
        this.listener = listener;
    }

    @Override
    protected CastNCrew doInBackground(String... imdbIds) {
        try {
            IMDBService service = new IMDBService();

            String json = service.getCastNCrew(imdbIds[0]);

            JSONObject object = new JSONObject(json);

            return new CastNCrew(object);

        } catch (JSONException | IOException ignored) {
        }
        return null;
    }

    @Override
    protected void onPostExecute(CastNCrew castNCrew) {
        super.onPostExecute(castNCrew);
        listener.onCastNCrewLoaded(castNCrew);
    }

    public interface LoadCastNCrewListener {
        void onCastNCrewLoaded(CastNCrew castNCrew);
    }
}