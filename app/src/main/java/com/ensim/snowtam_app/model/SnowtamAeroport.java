package com.ensim.snowtam_app.model;

public class SnowtamAeroport {
    private String mId;
    private String mSnowtam;



    public SnowtamAeroport() {
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }


    public String getmSnowtam() {
        return mSnowtam;
    }

    public void setmSnowtam(String mSnowtam) {
        this.mSnowtam = mSnowtam;
    }

    @Override
    public String toString() {
        return  mSnowtam; /*"SnowtamAeroport{" +
                "mId='" + mId + '\'' +
                ", mSnowtam='" + mSnowtam + '\'' +
                '}';*/
    }
}
