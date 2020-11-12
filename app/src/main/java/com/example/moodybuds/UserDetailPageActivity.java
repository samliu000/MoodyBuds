package com.example.moodybuds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import org.parceler.Parcel;
import org.parceler.Parcels;

public class UserDetailPageActivity extends AppCompatActivity {
    ProfileCard profile;

    ImageButton backButton;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // unwraps the profile card being called
        profile = (ProfileCard) Parcels.unwrap(getIntent().getParcelableExtra(ProfileCard.class.getName()));
        Log.d("ProfileDetails", String.format("Showing details for ", profile.getName()));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_action_bar);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_user_detail_page);

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
}