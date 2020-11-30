package com.ensim.snowtam_app.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class InfoAeroport implements Serializable {
    private String mId;
    private String mName;
    private String mType;
    private String mCoordLat;
    private String mCoordLon;

    public InfoAeroport(String mId) {
        this.mId = mId;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String Id) {
        this.mId = Id;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public String getmCoordLat() {
        return mCoordLat;
    }

    public void setmCoordLat(String mCoordLat) {
        this.mCoordLat = mCoordLat;
    }

    public String getmCoordLon() {
        return mCoordLon;
    }

    public void setmCoordLon(String mCoordLon) {
        this.mCoordLon = mCoordLon;
    }

   /* @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mId);
        dest.writeString(mName);
        dest.writeString(mType);
        dest.writeString(mCoordLat);
        dest.writeString(mCoordLon);
    }*/
}
