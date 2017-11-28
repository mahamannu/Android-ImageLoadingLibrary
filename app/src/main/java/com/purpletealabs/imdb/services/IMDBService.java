package com.purpletealabs.imdb.services;

import com.purpletealabs.imdb.ServiceHandler;

import java.io.IOException;

public class IMDBService {
    private static final String API_KEY = "0d5d6821cfc67d0b3f2988ccdab7ef11";
    private static final String API_BASE_URL = "http://api.themoviedb.org/3/movie/";

    private final ServiceHandler sh;

    public IMDBService() {
        this.sh = new ServiceHandler();
    }

    public String getUpcomingMovies() throws IOException {
        String url = String.format("%supcoming?api_key=%s", API_BASE_URL, API_KEY);
        return sh.makeServiceCall(url, ServiceHandler.GET);
    }

    public String getMovieDetails(long movieId) throws IOException {
        String url = String.format("%s%s?api_key=%s", API_BASE_URL, movieId, API_KEY);
        return sh.makeServiceCall(url, ServiceHandler.GET);
    }

    public String getVideos(long movieId) throws IOException {
        String url = String.format("%s%s/videos?api_key=%s", API_BASE_URL, movieId, API_KEY);
        return sh.makeServiceCall(url, ServiceHandler.GET);
    }

    public String getImages(long movieId) throws IOException {
        String url = String.format("%s%s/images?api_key=%s", API_BASE_URL, movieId, API_KEY);
        return sh.makeServiceCall(url, ServiceHandler.GET);
    }

    public String getCastNCrew(String imdbId) throws IOException {
        String url = String.format("%s%s/credits?api_key=%s", API_BASE_URL, imdbId, API_KEY);
        return sh.makeServiceCall(url, ServiceHandler.GET);
    }
}