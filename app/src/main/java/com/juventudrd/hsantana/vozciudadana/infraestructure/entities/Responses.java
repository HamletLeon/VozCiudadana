package com.juventudrd.hsantana.vozciudadana.infraestructure.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by hsantana on 4/22/2017.
 */

public class Responses implements Parcelable{
    private String ImageURL = "";
    private String Text = "";
    private int[] Score = new int[2];
    private boolean[] ScoreAboutUser = new boolean[2];
    private Date CreationDate = Calendar.getInstance().getTime();

    public int mExampleImage = 0;

    Responses(String mText, String mImageURL, int[] mScore, Date CreationDate){
        this.Text = mText;
        this.ImageURL = mImageURL;
        this.Score = mScore;
        this.CreationDate = CreationDate;
    }

    Responses(Parcel in) {
        ImageURL = in.readString();
        Text = in.readString();
        in.readIntArray(Score);
        in.readBooleanArray(ScoreAboutUser);
    }

    public static final Creator<Responses> CREATOR = new Creator<Responses>() {
        @Override
        public Responses createFromParcel(Parcel in) {
            return new Responses(in);
        }

        @Override
        public Responses[] newArray(int size) {
            return new Responses[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Text);
        dest.writeString(ImageURL);
        dest.writeIntArray(Score);
        dest.writeBooleanArray(ScoreAboutUser);
    }

    public String getText() {
        return Text;
    }
    public void setText(String text) {
        this.Text = text;
    }

    public String getImageURL() {
        return ImageURL;
    }
    public void setImageURL(String imageURL) {
        this.ImageURL = imageURL;
    }

    public int getInFavor() {
        return Score[0];
    }
    public int getAgainst(){
        return Score[1];
    }

    public boolean getInFavorAboutUser(){
        return ScoreAboutUser[0];
    }
    public void setInFavorAboutUser(boolean infavor){
        ScoreAboutUser[0] = infavor;
    }

    public boolean getAgainstAboutUser(){
        return ScoreAboutUser[1];
    }
    public void setAgainstAboutUser(boolean against){
        ScoreAboutUser[1] = against;
    }

    public Date getCreationDate() {
        return CreationDate;
    }
    public void setCreationDate(Date creationDate) {
        CreationDate = creationDate;
    }
}
