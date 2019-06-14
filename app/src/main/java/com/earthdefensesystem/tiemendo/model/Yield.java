package com.earthdefensesystem.tiemendo.model;

import java.io.Serializable;

public class Yield implements Serializable {
    private long yieldid;
    private int numbags;
    private int goal;
    private int farmacres;
    private CropType croptype;
    private String season;
    private Farmer farmer;

    public Yield(long yieldid, int numbags, int goal, int farmacres, CropType croptype, String season, Farmer farmer) {
        this.yieldid = yieldid;
        this.numbags = numbags;
        this.goal = goal;
        this.farmacres = farmacres;
        this.croptype = croptype;
        this.season = season;
        this.farmer = farmer;
    }

    public long getYieldid() {
        return yieldid;
    }

    public void setYieldid(long yieldid) {
        this.yieldid = yieldid;
    }

    public int getNumbags() {
        return numbags;
    }

    public void setNumbags(int numbags) {
        this.numbags = numbags;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public int getFarmacres() {
        return farmacres;
    }

    public void setFarmacres(int farmacres) {
        this.farmacres = farmacres;
    }

    public CropType getCroptype() {
        return croptype;
    }

    public void setCroptype(CropType croptype) {
        this.croptype = croptype;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public Farmer getFarmer() {
        return farmer;
    }

    public void setFarmer(Farmer farmer) {
        this.farmer = farmer;
    }
}
