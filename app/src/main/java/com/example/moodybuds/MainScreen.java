package com.example.moodybuds;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainScreen extends AppCompatActivity {

    List<ProfileCard> listOfPeople;
    FirebaseUser currentFirebaseUser;
    DatabaseReference mRootRef;
    DatabaseReference mUserRef;
    String userUID;
    MainScreenAdapter adapter;

    public MainScreen() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_action_bar);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_main_screen);

        Intent intent = getIntent();

        // initialize list of people and current firebase user
        listOfPeople = new ArrayList<>();
        currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        currentFirebaseUser.getPhotoUrl();

        // create database references
        mRootRef = FirebaseDatabase.getInstance().getReference();
        mUserRef = mRootRef.child("users");

        // get userUID
        userUID = currentFirebaseUser.getUid();

        // add to database
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUserMetadata metadata = auth.getCurrentUser().getMetadata();

        // if new user, add their data to the database
        /*if (metadata.getCreationTimestamp() == metadata.getLastSignInTimestamp()) {
            ProfileCard user = new ProfileCard(currentFirebaseUser.getDisplayName(), 0, "", userUID, currentFirebaseUser.getPhotoUrl(), "", "", "");
            mUserRef.child(userUID).setValue(user);
        }*/

        // Set adapter
        RecyclerView rvProfile = (RecyclerView) findViewById(R.id.rvMain);
        adapter = new MainScreenAdapter(listOfPeople, this);
        rvProfile.setAdapter(adapter);
        rvProfile.setLayoutManager(new LinearLayoutManager(this));
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
                showEditDetailPage();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showEditDetailPage() {
        Intent toDetail = new Intent(this, DetailPage.class);
        startActivity(toDetail);
    }


    @Override
    protected void onStart() {
        super.onStart();

        // pull information from database whenever data changes and also when listener attached
        // for the first time
        mUserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                // populate/update list of people
                listOfPeople.clear();
                for(DataSnapshot person: snapshot.getChildren()) {
                    listOfPeople.add(person.getValue(ProfileCard.class));
                }

                // Debug to print list of people
                for(ProfileCard i: listOfPeople) {
                    Log.d("MAINSCREEN", i.toString());
                }
                Log.d("MAINSCREEN", "Num of people: " + listOfPeople.size());

                // recycler view stuff here
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Log.d("MAINSCREEN", "size of list of people: " + listOfPeople.size());

    }

}