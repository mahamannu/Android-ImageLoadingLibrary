package com.purpletealabs.imdb.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Country {
    private String name;

    Country(JSONObject jsonObject) throws JSONException {
        name = jsonObject.getString(Country.Fields.NAME);
    }

    private static final class Fields {
        static final String NAME = "name";
    }

    public String getName() {
        return name;
    }
}
