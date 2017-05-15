package com.juventudrd.hsantana.vozciudadana.infraestructure.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by hsantana on 4/22/2017.
 */

public class Users implements Parcelable{
    private String ImageURL = "";
    private String UserName = "";
    private String FullName = "";
    private String NumberOfIdentification = "";
    private String PhoneNumber = "";
    private String MobileNumber = "";
    private String Email = "";
    private Date RegisteredDate = new Date();

    Users(String fullName, String numberOfIdentification, String userName, String email, String imageURL, Date registeredDate){
        this.FullName = fullName;
        this.NumberOfIdentification = numberOfIdentification;
        this.UserName = userName;
        this.Email = email;
        this.ImageURL = imageURL;
        this.RegisteredDate = registeredDate;
    }

    private Users(Parcel in) {
        ImageURL = in.readString();
        UserName = in.readString();
        FullName = in.readString();
        NumberOfIdentification = in.readString();
        PhoneNumber = in.readString();
        MobileNumber = in.readString();
        Email = in.readString();
        RegisteredDate = (Date) in.readSerializable();
    }

    public static final Creator<Users> CREATOR = new Creator<Users>() {
        @Override
        public Users createFromParcel(Parcel in) {
            return new Users(in);
        }

        @Override
        public Users[] newArray(int size) {
            return new Users[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ImageURL);
        dest.writeString(UserName);
        dest.writeString(FullName);
        dest.writeString(NumberOfIdentification);
        dest.writeString(PhoneNumber);
        dest.writeString(MobileNumber);
        dest.writeString(Email);
        dest.writeSerializable(RegisteredDate);
    }

    public String getImageURL() {
        return ImageURL;
    }
    public void setImageURL(String imageURL) {
        this.ImageURL = imageURL;
    }

    public String getUserName() {
        return UserName;
    }
    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getFullName() {
        return FullName;
    }
    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getNumberOfIdentification() {
        return NumberOfIdentification;
    }
    public void setNumberOfIdentification(String numberOfIdentification) {
        NumberOfIdentification = numberOfIdentification;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }
    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        Email = email;
    }

    public void setRegisteredDate(Date registeredDate) {
        RegisteredDate = registeredDate;
    }
    public Date getRegisteredDate() {
        return RegisteredDate;
    }
}
