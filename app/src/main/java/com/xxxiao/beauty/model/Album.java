package com.xxxiao.beauty.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

/**
 * Created by Administrator on 2016/11/16.
 */

@Entity
public class Album implements Parcelable {

    @Id
    public Long id;
    public String name;
    @Unique
    public String link;
    public String cover;

    //public Long timestamp;

    public Album() {

    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCover() {
        return this.cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    


    @Generated(hash = 2007464874)
    public Album(Long id, String name, String link, String cover) {
        this.id = id;
        this.name = name;
        this.link = link;
        this.cover = cover;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeString(this.link);
        dest.writeString(this.cover);
    }

    protected Album(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.name = in.readString();
        this.link = in.readString();
        this.cover = in.readString();
    }

    public static final Creator<Album> CREATOR = new Creator<Album>() {
        @Override
        public Album createFromParcel(Parcel source) {
            return new Album(source);
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size];
        }
    };
}
