package com.juventudrd.hsantana.vozciudadana.infraestructure.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hsantana on 4/22/2017.
 */

public class News implements Parcelable{
    private String mImageURL = "";
    private String mTitle = "";
    private String mDescription = "";
    private String mDate = "";
    private int mVotesQTY = 0;
    private boolean mVoteForIt = false;
    private int mAgainstQTY = 0;
    private boolean mAgainstIt = false;
    private int mCommentsQTY = 0;

    public int mExampleImage = 0;

    public News(String title, String description, int votes, int against, int comments, int ExampleImage){
        this.mTitle = title;
        this.mDescription = description;
        this.mVotesQTY = votes;
        this.mAgainstQTY = against;
        this.mCommentsQTY = comments;
        this.mExampleImage = ExampleImage;
    }

    private News(Parcel in) {
        mImageURL = in.readString();
        mTitle = in.readString();
        mDescription = in.readString();
        mDate = in.readString();
        mVotesQTY = in.readInt();
        mVoteForIt = in.readByte()!=0;
        mAgainstQTY = in.readInt();
        mAgainstIt = in.readByte()!=0;
        mCommentsQTY = in.readInt();

        mExampleImage = in.readInt();
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mImageURL);
        dest.writeString(mTitle);
        dest.writeString(mDescription);
        dest.writeString(mDate);
        dest.writeInt(mVotesQTY);
        dest.writeByte((byte) (mVoteForIt?1:0));
        dest.writeInt(mAgainstQTY);
        dest.writeByte((byte) (mAgainstIt?1:0));
        dest.writeInt(mCommentsQTY);

        dest.writeInt(mExampleImage);
    }

    String getImageURL() {
        return mImageURL;
    }
    public String getTitle() {
        return mTitle;
    }
    public String getDescription() {
        return mDescription;
    }
    String getDate() {
        return mDate;
    }

    public int getCommentsQTY() {
        return mCommentsQTY;
    }
    public int getVotesQTY() {
        return mVotesQTY;
    }
    public int getAgainstQTY() {
        return mAgainstQTY;
    }

    public boolean getVoteForIt(){
        return mVoteForIt;
    }
    public boolean getAgainstIt(){
        return mAgainstIt;
    }

    // Mientras se aclara el flujo de acciones con las noticias
    public void setVoteForIt(boolean mVoteForIt) {
        this.mVoteForIt = mVoteForIt;
    }
    public void setAgainstIt(boolean mAgainstIt) {
        this.mAgainstIt = mAgainstIt;
    }

}
