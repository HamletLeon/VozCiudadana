package com.juventudrd.hsantana.vozciudadana.infraestructure.entities;

import android.os.Parcel;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by hsantana on 4/22/2017.
 */

public class Comments extends Responses{
    private int ResponsesQTY = 0;
    private List<Responses> ResponsesList;

    public int mExampleImage = 0;

    public Comments(String text, String imageURL, int[] score, int responsesquantity, Date CreationDate){
        super(text, imageURL, score, CreationDate);
        this.ResponsesQTY = responsesquantity;
    }

    private Comments(Parcel in) {
        super(in);
        ResponsesQTY = in.readInt();
        in.readList(ResponsesList, Responses.class.getClassLoader());
    }

    public static final Creator<Comments> CREATOR = new Creator<Comments>() {
        @Override
        public Comments createFromParcel(Parcel in) {
            return new Comments(in);
        }

        @Override
        public Comments[] newArray(int size) {
            return new Comments[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(ResponsesQTY);
        dest.writeList(ResponsesList);
    }

    public int getResponsesQTY() {
        return ResponsesQTY;
    }
    public void setResponsesQTY(int responsesQTY) {
        ResponsesQTY = responsesQTY;
    }

    public List<Responses> getResponsesList() {
        return ResponsesList;
    }
    public void setResponsesList(List<Responses> responsesList) {
        ResponsesList = responsesList;
    }
}
