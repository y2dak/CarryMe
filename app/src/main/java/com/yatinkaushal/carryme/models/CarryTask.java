package com.yatinkaushal.carryme.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Date;

/**
 * Created by yatinkaushal on 2/3/17.
 */

@IgnoreExtraProperties
public class CarryTask implements Parcelable{
    public String name;
    public String userId;
    public String username;
    public String userPhotoUrl;
    public String description;
    public String id;
    public Date date;

    public CarryTask() {}

    public CarryTask(String name, String userId, String username, String userPhotoUrl, String description) {
        this.name = name;
        this.userId = userId;
        this.username = username;
        this.userPhotoUrl = userPhotoUrl;
        this.description = description;
        this.date = new Date();
    }

    protected CarryTask(Parcel in) {
        name = in.readString();
        userId = in.readString();
        username = in.readString();
        userPhotoUrl = in.readString();
        description = in.readString();
        id = in.readString();
        date = new Date(in.readLong());
    }

    public static final Creator<CarryTask> CREATOR = new Creator<CarryTask>() {
        @Override
        public CarryTask createFromParcel(Parcel in) {
            return new CarryTask(in);
        }

        @Override
        public CarryTask[] newArray(int size) {
            return new CarryTask[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(userId);
        parcel.writeString(username);
        parcel.writeString(userPhotoUrl);
        parcel.writeString(description);
        parcel.writeString(id);
        if (date != null) parcel.writeLong(date.getTime());
    }
}
