package com.example.aaron.hollydroid;

public class Movie {
    private String Title;
    private String Year;
    private String Genre;
    private String Director;
    private String Actors;

    public static String getAPISearchString(String str) {
        return "https://www.omdbapi.com/?t=" + str.replaceAll("\\s", "+") + "&apikey=e88d2fb1";
    }

    public String getTitle() {
        return Title;
    }

    public String getYear() {
        return Year;
    }

    public String getGenre() {
        return Genre;
    }

    public String getDirector() {
        return Director;
    }

    public String getActors() {
        return Actors;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "Title='" + Title + '\'' +
                ", Year='" + Year + '\'' +
                ", Genre='" + Genre + '\'' +
                ", Director='" + Director + '\'' +
                ", Actors='" + Actors + '\'' +
                '}';
    }
}
