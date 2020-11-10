package com.example.moodybuds;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.List;

public class MainScreen extends AppCompatActivity {

    List<ProfileCard> listOfPeople;
        public MainScreen() {

        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        // pull information from database
        // transform that info into ProfileCard objects
        listOfPeople.add(blahblahblah);
    }

}