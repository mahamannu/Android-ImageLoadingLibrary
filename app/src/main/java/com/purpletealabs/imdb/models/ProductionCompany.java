package com.purpletealabs.imdb.models;

import org.json.JSONException;
import org.json.JSONObject;

public class ProductionCompany {
    private long id;

    private String name;

    ProductionCompany(JSONObject jsonObject) throws JSONException {
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

    public String getName() {
        return name;
    }
}