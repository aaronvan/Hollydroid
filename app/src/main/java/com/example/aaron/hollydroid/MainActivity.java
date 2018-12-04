package com.example.aaron.hollydroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
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
    private Button searchButton;
    private TextView textViewMovieLabel;
    private TextView textViewYearLabel;
    private TextView textViewGenreLabel;
    private TextView textViewActorsLabel;

    //initialize other variables snd stuff
    String movieTitle, dataSource;
    URLConnection urlCon;
    URL url;
    File movieDataFile = new File("sampledata/files/data.json");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get references to the widgets
        editTextMovieName = (EditText) findViewById(R.id.editTextMovieName);
        searchButton = (Button) findViewById(R.id.buttonSearch);
        textViewMovieLabel = (TextView) findViewById(R.id.textViewMovieLabel);
        textViewYearLabel = (TextView) findViewById(R.id.textViewYearLabel);
        textViewGenreLabel = (TextView) findViewById(R.id.textViewGenreLabel);
        textViewActorsLabel = (TextView) findViewById(R.id.textViewActorsLabel);

        //set the listener
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movieTitle = editTextMovieName.getText().toString();
                dataSource = Movie.getAPISearchString(movieTitle);
                try {
                    url = new URL(dataSource);
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

                Gson gson = new Gson();
                try (FileReader fileReader = new FileReader(movieDataFile);
                     JsonReader jsonReader = new JsonReader(fileReader))
                {
                    Movie movie = gson.fromJson(jsonReader, Movie.class);
                    textViewMovieLabel.setText(movie.getTitle());
                    textViewYearLabel.setText(movie.getYear());
                    textViewActorsLabel.setText(movie.getActors());
                    textViewGenreLabel.setText(movie.getGenre());
                    //movieDataFile.delete();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }


}
