package com.purpletealabs.imdb.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Video {
    public Video(JSONObject jsonObject) throws JSONException {
        key = jsonObject.getString(Fields.KEY);
        name = jsonObject.getString(Fields.NAME);
        site = jsonObject.getString(Fields.SITE);
        type = jsonObject.getString(Fields.TYPE);
    }

    private static final class Fields {
        static final String KEY = "key";
        static final String NAME = "name";
        static final String SITE = "site";
        static final String TYPE = "type";
    }

    private String key;

    private String name;

    private String site;

    private String type;

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getSite() {
        return site;
    }

    public String getType() {
        return type;
    }
}
