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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeString(name);
        out.writeString(link);
        out.writeString(cover);
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



    public static final Parcelable.Creator<Album> CREATOR = new Parcelable.Creator<Album>() {
        public Album createFromParcel(Parcel in) {
            return new Album(in);
        }

        public Album[] newArray(int size) {
            return new Album[size];
        }
    };

    private Album(Parcel in) {
        name = in.readString();
        link = in.readString();
        cover = in.readString();
    }

    @Generated(hash = 2007464874)
    public Album(Long id, String name, String link, String cover) {
        this.id = id;
        this.name = name;
        this.link = link;
        this.cover = cover;
    }
}
