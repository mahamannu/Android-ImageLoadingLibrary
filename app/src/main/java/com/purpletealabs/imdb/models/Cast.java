package com.purpletealabs.imdb.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Cast {
    Cast(JSONObject jsonObject) throws JSONException {
        name = jsonObject.getString(Fields.NAME);
        character = jsonObject.getString(Fields.CHARACTER);
        profilePath = jsonObject.getString(Fields.PROFILE_PATH);
    }

    private static final class Fields {
        static final String NAME = "name";
        static final String CHARACTER = "character";
        static final String PROFILE_PATH = "profile_path";
    }

    private String character;

    private String name;

    private String profilePath;

    public String getCharacter() {
        return character;
    }

    public String getName() {
        return name;
    }

    public String getProfilePath() {
        return profilePath;
    }
}
