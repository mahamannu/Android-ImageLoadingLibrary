package com.purpletealabs.imdb.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.purpletealabs.imdb.config.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Movie implements Parcelable {
    private long id;

    private boolean video;

    private double voteAverage;

    private String title;

    private double popularity;

    private String posterPath;

    private String originalLanguage;

    private String originalTitle;

    private String backdropPath;

    private boolean adult;

    private String overview;

    private String releaseDate;

    private ArrayList<Language> spokenLanguages;

    private String revenue;

    private String tagLine;

    private int runtime;

    private ArrayList<Genre> genres;

    private ArrayList<Country> productionCountries;

    private ArrayList<ProductionCompany> productionCompanies;

    private String imdbId;

    private long budget;

    private long voteCount;

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Language> getSpokenLanguages() {
        return spokenLanguages;
    }

    public void setSpokenLanguages(ArrayList<Language> spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    public String getTagLine() {
        return tagLine;
    }

    public void setTagLine(String tagLine) {
        this.tagLine = tagLine;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<Genre> genres) {
        this.genres = genres;
    }

    public ArrayList<Country> getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(ArrayList<Country> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public ArrayList<ProductionCompany> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(ArrayList<ProductionCompany> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public long getBudget() {
        return budget;
    }

    public void setBudget(long budget) {
        this.budget = budget;
    }

    private static final class Fields {
        static final String ID = "id";
        static final String TITLE = "title";
        static final String VIDEO = "video";
        static final String RELEASE_DATE = "release_date";
        static final String POPULARITY = "popularity";
        static final String VOTE_COUNT = "vote_count";
        static final String VOTE_AVERAGE = "vote_average";
        static final String POSTER_PATH = "poster_path";
        static final String ORIGINAL_LANGUAGE = "original_language";
        static final String ORIGINAL_TITLE = "original_title";
        static final String GENRE_IDS = "genre_ids";
        static final String BACKDROP_PATH = "backdrop_path";
        static final String ADULT = "adult";
        static final String OVERVIEW = "overview";
        static final String BUDGET = "budget";
        static final String IMDb_ID = "imdb_id";
        static final String PRODUCTION_COMPANIES = "production_companies";
        static final String PRODUCTION_COUNTRIES = "production_countries";
        static final String REVENUE = "revenue";
        static final String RUNTIME = "runtime";
        static final String TAG_LINE = "tagline";
        static final String GENRES = "genres";
        static final String SPOKEN_LANGUAGES = "spoken_languages";
        static final String STATUS = "status";
    }

    public Movie(JSONObject jsonObject, boolean withDetails) throws JSONException {
        voteCount = jsonObject.getLong(Fields.VOTE_COUNT);
        id = jsonObject.getLong(Fields.ID);
        video = jsonObject.getBoolean(Fields.VIDEO);
        title = jsonObject.getString(Fields.TITLE);
        popularity = jsonObject.getDouble(Fields.POPULARITY);
        posterPath = jsonObject.getString(Fields.POSTER_PATH);
        originalLanguage = jsonObject.getString(Fields.ORIGINAL_LANGUAGE);
        originalTitle = jsonObject.getString(Fields.ORIGINAL_TITLE);
        backdropPath = jsonObject.getString(Fields.BACKDROP_PATH);
        adult = jsonObject.getBoolean(Fields.ADULT);
        overview = jsonObject.getString(Fields.OVERVIEW);
        releaseDate = jsonObject.getString(Fields.RELEASE_DATE);
        voteAverage = jsonObject.getDouble(Fields.VOTE_AVERAGE);

        if (withDetails) {
            budget = jsonObject.getLong(Fields.BUDGET);
            imdbId = jsonObject.getString(Fields.IMDb_ID);
            runtime = jsonObject.getInt(Fields.RUNTIME);
            tagLine = jsonObject.getString(Fields.TAG_LINE);
            revenue = jsonObject.getString(Fields.REVENUE);
            status = jsonObject.getString(Fields.STATUS);

            JSONArray companies = jsonObject.getJSONArray(Fields.PRODUCTION_COMPANIES);
            productionCompanies = new ArrayList<>();
            for (int i = 0; i < companies.length(); i++) {
                productionCompanies.add(new ProductionCompany(companies.getJSONObject(i)));
            }

            JSONArray countries = jsonObject.getJSONArray(Fields.PRODUCTION_COUNTRIES);
            productionCountries = new ArrayList<>();
            for (int i = 0; i < countries.length(); i++) {
                productionCountries.add(new Country(countries.getJSONObject(i)));
            }

            JSONArray genresArray = jsonObject.getJSONArray(Fields.GENRES);
            genres = new ArrayList<>();
            for (int i = 0; i < genresArray.length(); i++) {
                genres.add(new Genre(genresArray.getJSONObject(i)));
            }

            JSONArray languages = jsonObject.getJSONArray(Fields.SPOKEN_LANGUAGES);
            spokenLanguages = new ArrayList<>();
            for (int i = 0; i < languages.length(); i++) {
                spokenLanguages.add(new Language(languages.getJSONObject(i)));
            }
        }
    }

    public long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(long voteCount) {
        this.voteCount = voteCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    private Movie(Parcel in) {
        voteCount = in.readLong();
        id = in.readLong();
        video = in.readByte() != 0x00;
        voteAverage = in.readDouble();
        title = in.readString();
        popularity = in.readDouble();
        posterPath = in.readString();
        originalLanguage = in.readString();
        originalTitle = in.readString();
        backdropPath = in.readString();
        adult = in.readByte() != 0x00;
        overview = in.readString();
        releaseDate = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(voteCount);
        dest.writeLong(id);
        dest.writeByte((byte) (video ? 0x01 : 0x00));
        dest.writeDouble(voteAverage);
        dest.writeString(title);
        dest.writeDouble(popularity);
        dest.writeString(posterPath);
        dest.writeString(originalLanguage);
        dest.writeString(originalTitle);
        dest.writeString(backdropPath);
        dest.writeByte((byte) (adult ? 0x01 : 0x00));
        dest.writeString(overview);
        dest.writeString(releaseDate);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}