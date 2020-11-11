package com.example.moodybuds;

import android.os.Bundle;
import android.provider.ContactsContract;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileCard {

    public String name;
    public int ratingNumber;
    public String previewText;

    public ProfileCard() {
        name = "NULL";
        ratingNumber = 0;
        previewText = "NULL";
    }

    public ProfileCard(String name, int ratingNumber, String previewText){
        this.name = name;
        this.ratingNumber = ratingNumber;
        this.previewText = previewText;
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
}