package com.purpletealabs.imdb.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Crew {
    Crew(JSONObject jsonObject) throws JSONException {
        department = jsonObject.getString(Fields.DEPARTMENT);
        job = jsonObject.getString(Fields.JOB);
        name = jsonObject.getString(Fields.NAME);
        profilePath = jsonObject.getString(Fields.PROFILE_PATH);
    }

    private static final class Fields {
        static final String DEPARTMENT = "department";
        static final String NAME = "name";
        static final String PROFILE_PATH = "profile_path";
        static final String JOB = "job";
    }

    private String department;

    private String job;

    private String name;

    private String profilePath;

    public String getDepartment() {
        return department;
    }

    public String getJob() {
        return job;
    }

    public String getName() {
        return name;
    }

    public String getProfilePath() {
        return profilePath;
    }
}
