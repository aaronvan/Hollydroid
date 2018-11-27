package com.example.aaron.hollydroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    //define variables for the widgets
    private EditText editTextMovieName;
    private Button   buttonSearch;
    private TextView textViewMovieLabel;
    private TextView textViewYearLabel;
    private TextView textViewGenreLabel;
    private TextView textViewActorsLabel;

    //instance variable for the movie title
    String movieTitle, searchString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get references to the widgets
        editTextMovieName = (EditText) findViewById(R.id.editTextMovieName);
        buttonSearch = (Button) findViewById(R.id.buttonSearch);
        textViewMovieLabel = (TextView) findViewById(R.id.textViewMovieLabel);
        textViewYearLabel = (TextView) findViewById(R.id.textViewYearLabel);
        textViewGenreLabel = (TextView) findViewById(R.id.textViewGenreLabel);
        textViewActorsLabel = (TextView) findViewById(R.id.textViewActorsLabel);

        //set the listener
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movieTitle = editTextMovieName.getText().toString();
                searchString = Movie.getAPISearchString(movieTitle);
                //textViewMovieLabel.setText(searchString);
            }
        });
    }


}
