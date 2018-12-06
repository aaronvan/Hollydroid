package com.example.aaron.hollydroid;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends Activity {

    //define variables for the widgets
    private EditText editTextMovieName;
    private TextView textViewMovieLabel;
    private TextView textViewYearLabel;
    private TextView textViewGenreLabel;
    private TextView textViewActorsLabel;
    private TextView textViewPlotLabel;

    //initialize variables and stuff
    String movieTitle, dataSource;
    URLConnection urlCon;
    URL url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // permit the app to get network access
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //get references to the widgets
        editTextMovieName = (EditText) findViewById(R.id.editTextMovieName);
        final Button searchButton = (Button) findViewById(R.id.buttonSearch);
        textViewMovieLabel = (TextView) findViewById(R.id.textViewMovieLabel);
        textViewYearLabel = (TextView) findViewById(R.id.textViewYearLabel);
        textViewGenreLabel = (TextView) findViewById(R.id.textViewGenreLabel);
        textViewActorsLabel = (TextView) findViewById(R.id.textViewActorsLabel);
        textViewPlotLabel = (TextView) findViewById(R.id.textViewPlotLabel);

        //set the OnClickListener interface
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hides the keyboard after the user clicks
                // the search button
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);

                // Creates a temp file for the movie data
                File movieDataFile = null;
                try {
                    movieDataFile = File.createTempFile("data", "json");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                movieTitle = editTextMovieName.getText().toString();
                dataSource = Movie.getAPISearchString(movieTitle);
                editTextMovieName.setText(""); // clear for the next search

                // Get an Internet connection to the database
                try {
                    // Creates a URL object from the dataSource string
                    url = new URL(dataSource);
                    // Creates an instance of a URLConnection that represents
                    // a connection to the URL url object
                    urlCon = url.openConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
                     FileWriter fileWriter = new FileWriter(movieDataFile))
                {
                    while (true) {
                        String line = bufferedReader.readLine();
                        if (line == null)
                            break;
                        fileWriter.write(line + "\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //deserialize the JSON data and display it for the user
                Gson gson = new Gson();
                try (FileReader fileReader = new FileReader(movieDataFile);
                     JsonReader jsonReader = new JsonReader(fileReader))
                {
                    Movie movie = gson.fromJson(jsonReader, Movie.class);
                    textViewMovieLabel.setText(movie.getTitle());
                    textViewYearLabel.setText(movie.getYear());
                    textViewActorsLabel.setText(movie.getActors());
                    textViewGenreLabel.setText(movie.getGenre());
                    textViewPlotLabel.setText(movie.getPlot());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
