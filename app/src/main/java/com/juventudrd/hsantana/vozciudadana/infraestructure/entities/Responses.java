package com.juventudrd.hsantana.vozciudadana.infraestructure.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hsantana on 4/22/2017.
 */

public class Responses implements Parcelable{
    private String ImageURL = "";
    private String Text = "";
    private int Score = 0;

    public int mExampleImage = 0;

    Responses(String mText, String mImageURL, int mScore){
        this.Text = mText;
        this.ImageURL = mImageURL;
        this.Score = mScore;
    }

    Responses(Parcel in) {
        ImageURL = in.readString();
        Text = in.readString();
        Score = in.readInt();
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
        dest.writeInt(Score);
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

    public int getScore() {
        return Score;
    }
    public void setScore(int score) {
        Score = score;
    }
}
