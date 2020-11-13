package com.example.moodybuds;

import android.net.Uri;

import org.parceler.Parcel;

@Parcel
public class ProfileCard {

    private String name;
    private int ratingNumber;
    private String previewText;
    private String UID;
    private String photoURL;
    private String pos;
    private String neg;
    private String grateful;
    private boolean needsHelp;

    // Empty constructor for parcel
    public ProfileCard() {
        name = "NULL";
        ratingNumber = 0;
        previewText = "NULL";
        UID = "";
        photoURL = "";
        pos = "";
        neg = "";
        grateful = "";
        needsHelp = false;
    }

    public ProfileCard(String name, int ratingNumber, String previewText, String UID, Uri photoURL, String pos, String neg, String grateful, boolean needsHelp){
        this.name = name;
        this.ratingNumber = ratingNumber;
        this.previewText = previewText;
        this.UID = UID;
        if(photoURL == null){
            this.photoURL = "";
        } else {
            this.photoURL = photoURL.toString();
        }
        this.pos = pos;
        this.neg = neg;
        this.grateful = grateful;
        this.needsHelp = needsHelp;
    }

    public boolean isNeedsHelp() {
        return needsHelp;
    }

    public String getName() {
        return name;
    }

    public void setNeedsHelp(boolean needsHelp) {
        this.needsHelp = needsHelp;
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

    public String getPos() {
        return pos;
    }

    public String getNeg() {
        return neg;
    }

    public String getGrateful() {
        return grateful;
    }

    public String getUID() {
        return UID;
    }

    public String getPhotoURLString() {
        return photoURL;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRatingNumber(int ratingNumber) {
        this.ratingNumber = ratingNumber;
    }

    public void setPreviewText(String previewText) {
        this.previewText = previewText;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public void setNeg(String neg) {
        this.neg = neg;
    }

    public void setGrateful(String grateful) {
        this.grateful = grateful;
    }
}