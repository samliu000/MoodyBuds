package com.example.moodybuds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import org.parceler.Parcels;

public class UserDetailPageActivity extends AppCompatActivity {

    ProfileCard profile;
    Button updateButton;
    TextView userName;
    SeekBar currUserMoodBar;
    TextView currUserPos;
    TextView currUserNeg;
    TextView currUserGrateful;

    ImageButton backButton;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail_page);
<<<<<<< HEAD
        // unwraps the profile card being called
=======

        //initialize everything
        userName = findViewById(R.id.userName);
        currUserGrateful = findViewById(R.id.userGrateful);
        currUserMoodBar = findViewById(R.id.userMoodBar);
        currUserPos = findViewById(R.id.userPos);
        currUserNeg = findViewById(R.id.userNeg);

        currUserMoodBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        // unwrap profile
>>>>>>> 6fe812855fc02fb0f2964e67f91ef618574a97a5
        profile = (ProfileCard) Parcels.unwrap(getIntent().getParcelableExtra(ProfileCard.class.getName()));

        // populate views
        userName.setText(profile.getName());
        currUserGrateful.setText(profile.getGrateful());
        currUserMoodBar.setProgress(profile.getRatingNumber());
        currUserPos.setText(profile.getPos());
        currUserNeg.setText(profile.getPos());

        // action bar logo
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_action_bar);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
<<<<<<< HEAD

=======
>>>>>>> 6fe812855fc02fb0f2964e67f91ef618574a97a5

        // Back button on click
        backButton = findViewById(R.id.backButton);
        context = this;
        // on click
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toMain = new Intent(context, MainScreen.class);
                startActivity(toMain);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}