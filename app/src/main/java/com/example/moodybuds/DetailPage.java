package com.example.moodybuds;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DetailPage extends AppCompatActivity {

    Button updateButton;
    EditText currUserName;
    SeekBar currUserMoodBar;
    EditText currUserPos;
    EditText currUserNeg;
    EditText currUserGrateful;
    ImageView currUserPhoto;
    Context context;
    FirebaseUser currentFirebaseUser;
    DatabaseReference mUserRef;
    ProfileCard userInfo;
    Switch toTalkSwitch;
    ImageView alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_action_bar);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        // attach everything
        currUserName = findViewById(R.id.currUserName);
        currUserGrateful = findViewById(R.id.currUserGrateful);
        currUserMoodBar = findViewById(R.id.userMoodBar);
        currUserPos = findViewById(R.id.currUserPos);
        currUserNeg = findViewById(R.id.currUserNeg);
        updateButton = findViewById(R.id.updateButton);
        currUserPhoto = findViewById(R.id.currUserProfilePic);
        context = this;
        currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        mUserRef = FirebaseDatabase.getInstance().getReference().child("users").child(currentFirebaseUser.getUid());
        //switch
        toTalkSwitch = findViewById(R.id.toTalk);
        alert = findViewById(R.id.alert);

        //switch button
        toTalkSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    alert.setVisibility(View.VISIBLE);
                } else {
                    alert.setVisibility(View.INVISIBLE);
                }
            }
        });

        // on click update button
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userInfo.setName(currUserName.getText().toString());
                userInfo.setGrateful(currUserGrateful.getText().toString());
                userInfo.setRatingNumber(currUserMoodBar.getProgress());
                userInfo.setPos(currUserPos.getText().toString());
                userInfo.setNeg(currUserNeg.getText().toString());
                mUserRef.setValue(userInfo);

                Intent toMain = new Intent(context, MainScreen.class);
                startActivity(toMain);
            }
        });

        currUserPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryIntent, 1000);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        mUserRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userInfo = dataSnapshot.getValue(ProfileCard.class);
                if(userInfo == null) {
                    userInfo = new ProfileCard(currentFirebaseUser.getDisplayName(), 0, "", currentFirebaseUser.getUid(), currentFirebaseUser.getPhotoUrl(), "", "", "");
                    mUserRef.setValue(userInfo);
                }
                currUserName.setText(userInfo.getName());
                currUserMoodBar.setProgress(userInfo.getRatingNumber());
                currUserNeg.setText(userInfo.getNeg());
                currUserPos.setText(userInfo.getPos());
                currUserGrateful.setText(userInfo.getGrateful());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ...
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000 && resultCode == Activity.RESULT_OK) {
            Uri imageUrl = data.getData();
            currUserPhoto.setImageURI(imageUrl);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.miProfile:
                AuthUI.getInstance()
                        .signOut(this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            public void onComplete(@NonNull Task<Void> task) {
                                goMainActivity();
                            }
                        });

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void goMainActivity() {
        Intent toMain = new Intent(this, MainActivity.class);
        startActivity(toMain);
    }


}