package com.earthdefensesystem.tiemendo.model;

import java.io.Serializable;
import java.util.List;

public class Farmer implements Serializable {
    private long farmerid;
    private long startyear;
    private FarmerLocation farmerlocation;
    private FarmerContact farmercontact;
    private List<Yield> yieldhistory;

    public Farmer(long farmerid, long startyear, FarmerLocation farmerlocation, FarmerContact farmercontact, List<Yield> yieldhistory) {
        this.farmerid = farmerid;
        this.startyear = startyear;
        this.farmerlocation = farmerlocation;
        this.farmercontact = farmercontact;
        this.yieldhistory = yieldhistory;
    }



    public long getFarmerid() {
        return farmerid;
    }

    public void setFarmerid(long farmerid) {
        this.farmerid = farmerid;
    }

    public long getStartyear() {
        return startyear;
    }

    public void setStartyear(long startyear) {
        this.startyear = startyear;
    }

    public FarmerLocation getFarmerlocation() {
        return farmerlocation;
    }

    public void setFarmerlocation(FarmerLocation farmerlocation) {
        this.farmerlocation = farmerlocation;
    }

    public FarmerContact getFarmercontact() {
        return farmercontact;
    }

    public void setFarmercontact(FarmerContact farmercontact) {
        this.farmercontact = farmercontact;
    }

    public List<Yield> getYieldhistory() {
        return yieldhistory;
    }

    public void setYieldhistory(List<Yield> yieldhistory) {
        this.yieldhistory = yieldhistory;
    }
}
