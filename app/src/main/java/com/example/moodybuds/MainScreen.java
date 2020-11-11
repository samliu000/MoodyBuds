package com.example.moodybuds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainScreen extends AppCompatActivity {

    List<ProfileCard> listOfPeople;
    FirebaseUser currentFirebaseUser;
    DatabaseReference mRootRef;
    DatabaseReference mUserRef;

    public MainScreen() {

        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        listOfPeople = new ArrayList<>();
        currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        mRootRef = FirebaseDatabase.getInstance().getReference();
        mUserRef = mRootRef.child("users");

//        // populate database with dummy variables
//        ProfileCard Samuel = new ProfileCard("Samuel", 10, "Hi I'm Sam");
//        ProfileCard Kimberly = new ProfileCard("Kimberly", 10, "Hi I'm Kimberly");
//        List<ProfileCard> users = new ArrayList<>();
//        users.add(Samuel);
//        users.add(Kimberly);
//        for(ProfileCard person: users) {
//            String key = mUserRef.push().getKey();
//            mUserRef.child(key).setValue(person);
//        }

    }

    @Override
    protected void onStart() {
        super.onStart();

        // pull information from database whenever data changes and also when listener attached
        // for the first time
        mUserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                // for each person in the database,
                for(DataSnapshot person: snapshot.getChildren()) {
                    listOfPeople.add(person.getValue(ProfileCard.class));
                }
                for(ProfileCard i: listOfPeople) {
                    Log.d("MAINSCREEN", i.toString());
                }
                Log.d("MAINSCREEN", "Num of people: " + listOfPeople.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Log.d("MAINSCREEN", "size of list of people: " + listOfPeople.size());

    }

}