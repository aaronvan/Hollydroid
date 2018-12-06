package com.example.aaron.hollydroid;

/**
 * Returns a Movie object that can be parsed and the information
 * displayed to the user.
 * @author avanalstine
 * @version 1.0
 */
public class Movie {
    private String Title;
    private String Year;
    private String Genre;
    private String Actors;
    private String Plot;

    /**
     * This method generates a URL that incorporates the movie title.
     * @param str
     * @return a URL formatted for the API.
     */
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

    public String getActors() {
        return Actors;
    }

    public String getPlot() {
        return Plot;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "Title='" + Title + '\'' +
                ", Year='" + Year + '\'' +
                ", Genre='" + Genre + '\'' +
                ", Actors='" + Actors + '\'' +
                ", Plot='" + Plot + '\'' +
                '}';
    }
}
