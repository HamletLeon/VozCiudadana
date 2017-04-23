package com.juventudrd.hsantana.vozciudadana;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hsantana on 4/22/2017.
 */

public class News implements Parcelable{
    private String mTitle = "";
    private String mDescription = "";
    private String mDate = "";
    private String mImageURL = "";
    private int mVotes = 0;
    private int mAgainst = 0;
    private int mComments = 0;

    public int mExampleImage = 0;

    public News(String title, String description, int votes, int against, int comments, int ExampleImage){
        this.mTitle = title;
        this.mDescription = description;
        this.mVotes = votes;
        this.mAgainst = against;
        this.mComments = comments;
        this.mExampleImage = ExampleImage;
    }

    private News(Parcel in) {
        mTitle = in.readString();
        mDescription = in.readString();
        mDate = in.readString();
        mImageURL = in.readString();
        mVotes = in.readInt();
        mComments = in.readInt();
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
        dest.writeString(mTitle);
        dest.writeString(mDescription);
        dest.writeString(mDate);
        dest.writeString(mImageURL);
        dest.writeInt(mVotes);
        dest.writeInt(mComments);
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }
    public String getTitle() {
        return mTitle;
    }
    public void setComments(int mComments) {
        this.mComments = mComments;
    }
    public int getComments() {
        return mComments;
    }
    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }
    public String getDescription() {
        return mDescription;
    }
    public void setDate(String mDate) {
        this.mDate = mDate;
    }
    public String getDate() {
        return mDate;
    }
    public void setImageURL(String mImageURL) {
        this.mImageURL = mImageURL;
    }
    public String getImageURL() {
        return mImageURL;
    }
    public void setVotes(int mVotes) {
        this.mVotes = mVotes;
    }
    public int getVotes() {
        return mVotes;
    }
    public void setAgainst(int mAgainst) {
        this.mAgainst = mAgainst;
    }
    public int getAgainst() {
        return mAgainst;
    }
}
