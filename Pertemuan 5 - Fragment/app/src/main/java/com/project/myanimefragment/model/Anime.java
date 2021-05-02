package com.project.myanimefragment.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Anime implements Parcelable {
    private String Judul;
    private String TanggalRilis;
    private String Studio;
    private String Rating;
    private String Photo;
    private String Desc;
    private String Link;

    public Anime() {
    }

    protected Anime(Parcel in) {
        Judul = in.readString();
        TanggalRilis = in.readString();
        Studio = in.readString();
        Rating = in.readString();
        Photo = in.readString();
        Desc = in.readString();
        Link = in.readString();
    }

    public static final Creator<Anime> CREATOR = new Creator<Anime>() {
        @Override
        public Anime createFromParcel(Parcel in) {
            return new Anime(in);
        }

        @Override
        public Anime[] newArray(int size) {
            return new Anime[size];
        }
    };

    public String getJudul() {
        return Judul;
    }

    public void setJudul(String judul) {
        Judul = judul;
    }

    public String getTanggalRilis() {
        return TanggalRilis;
    }

    public void setTanggalRilis(String tanggalRilis) {
        TanggalRilis = tanggalRilis;
    }

    public String getStudio() {
        return Studio;
    }

    public void setStudio(String studio) {
        Studio = studio;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Judul);
        dest.writeString(TanggalRilis);
        dest.writeString(Studio);
        dest.writeString(Rating);
        dest.writeString(Photo);
        dest.writeString(Desc);
        dest.writeString(Link);
    }
}
