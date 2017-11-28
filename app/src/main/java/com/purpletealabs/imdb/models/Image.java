package com.purpletealabs.imdb.models;

import com.purpletealabs.imdb.config.Constants;

import org.json.JSONException;
import org.json.JSONObject;

public class Image {

    public Image(JSONObject jsonObject) throws JSONException {
        aspectRatio = jsonObject.getDouble(Fields.ASPECT_RATIO);
        filePath = jsonObject.getString(Fields.FILE_PATH);
        height = jsonObject.getInt(Fields.HEIGHT);
        width = jsonObject.getInt(Fields.WIDTH);
    }

    private static final class Fields {
        static final String ASPECT_RATIO = "aspect_ratio";
        static final String FILE_PATH = "file_path";
        static final String HEIGHT = "height";
        static final String WIDTH = "width";
    }

    private double aspectRatio;

    private String filePath;

    private int height;

    private int width;

    public double getAspectRatio() {
        return aspectRatio;
    }

    public String getFilePath() {
        return filePath;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
