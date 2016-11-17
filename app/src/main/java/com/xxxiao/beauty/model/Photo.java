package com.xxxiao.beauty.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/11/16.
 */

public class Photo implements Parcelable {

    public Photo() {

    }

    public String url;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeString(url);
    }

    public static final Parcelable.Creator<Photo> CREATOR = new Parcelable.Creator<Photo>() {
        public Photo createFromParcel(Parcel in) {
            return new Photo(in);
        }

        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };

    private Photo(Parcel in) {
        url = in.readString();
    }

}
