package com.purpletealabs.imdb.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Language {
    private String name;

    Language(JSONObject jsonObject) throws JSONException {
        name = jsonObject.getString(Fields.NAME);
    }

    private static final class Fields {
        static final String NAME = "name";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
