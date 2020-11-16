package com.ensim.snowtam_app.model;

public class InfoAeroport {
    private String mId;
    private String mName;
    private String mType;
    private String mCoordLat;
    private String mCoordLon;

    public InfoAeroport(String mId) {
        this.mId = mId;
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
}
