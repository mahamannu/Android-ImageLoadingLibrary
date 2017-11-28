package com.purpletealabs.imdb.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Genre {
    private long id;

    private String name;

    Genre(JSONObject jsonObject) throws JSONException {
        id = jsonObject.getLong(Fields.ID);
        name = jsonObject.getString(Fields.NAME);
    }

    private static final class Fields {
        static final String ID = "id";
        static final String NAME = "name";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}