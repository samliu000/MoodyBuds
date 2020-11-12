package com.example.moodybuds;

import android.net.Uri;

public class ProfileCard {

    private String name;
    private int ratingNumber;
    private String previewText;
    private String UID;
    private String photoURL;

    public ProfileCard() {
        name = "NULL";
        ratingNumber = 0;
        previewText = "NULL";
        UID = "";
        photoURL = "";
    }

    public ProfileCard(String name, int ratingNumber, String previewText, String UID, Uri photoURL){
        this.name = name;
        this.ratingNumber = ratingNumber;
        this.previewText = previewText;
        this.UID = UID;
        if(photoURL == null){
            this.photoURL = "";
        } else {
            this.photoURL = photoURL.getPath();
        }
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

    public String getPhotoURLString() {
        return photoURL;
    }
}