package com.purpletealabs.imdb.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CastNCrew {
    private static final class Fields {
        static final String CAST = "cast";
        static final String CREW = "crew";
        static final String ID = "id";
    }

    private long id;

    private ArrayList<Cast> cast;

    private ArrayList<Crew> crew;

    public CastNCrew(JSONObject cnc) throws JSONException {
        id = cnc.getLong(Fields.ID);

        JSONArray castArray = cnc.getJSONArray(Fields.CAST);
        cast = new ArrayList<>();
        for (int i = 0; i < castArray.length(); i++) {
            cast.add(new Cast(castArray.getJSONObject(i)));
        }

        JSONArray crewArray = cnc.getJSONArray(Fields.CREW);
        crew = new ArrayList<>();
        for (int i = 0; i < crewArray.length(); i++) {
            crew.add(new Crew(crewArray.getJSONObject(i)));
        }
    }

    public long getId() {
        return id;
    }

    public ArrayList<Cast> getCast() {
        return cast;
    }

    public ArrayList<Crew> getCrew() {
        return crew;
    }
}
