package com.example.moodybuds;

import android.os.Bundle;
import android.provider.ContactsContract;

import androidx.appcompat.app.AppCompatActivity;

import org.parceler.Parcel;

@Parcel
public class ProfileCard {

    private String name;
    private int ratingNumber;
    private String previewText;
    private String UID;

    // Empty constructor for parcel
    public ProfileCard() {
        name = "NULL";
        ratingNumber = 0;
        previewText = "NULL";
        UID = "";
    }

    public ProfileCard(String name, int ratingNumber, String previewText, String UID){
        this.name = name;
        this.ratingNumber = ratingNumber;
        this.previewText = previewText;
        this.UID = UID;
    }

    public String getName() {
        return name;
    }

    public int getRatingNumber() {
        return ratingNumber;
    }

    public String getPreviewText() {
        return previewText;
    }

    public String toString() {
        return "(ProfileCard) Name: " + name + "RatingNumber: " + ratingNumber + "PreviewText: " + previewText;
    }
    public String getUID() {
        return UID;
    }

}