package com.earthdefensesystem.tiemendo.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Retailer {
    private long id;
    private String name;
    private long startyear;
    private RetailerLocation retailerlocation;
    private RetailerContact retailercontact;

    public Retailer(long id, String name, long startyear, RetailerLocation retailerlocation, RetailerContact retailercontact) {
        this.id = id;
        this.name = name;
        this.startyear = startyear;
        this.retailerlocation = retailerlocation;
        this.retailercontact = retailercontact;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getStartyear() {
        return startyear;
    }

    public void setStartyear(long startyear) {
        this.startyear = startyear;
    }

    public RetailerLocation getRetailerlocation() {
        return retailerlocation;
    }

    public void setRetailerlocation(RetailerLocation retailerlocation) {
        this.retailerlocation = retailerlocation;
    }

    public RetailerContact getRetailercontact() {
        return retailercontact;
    }

    public void setRetailercontact(RetailerContact retailercontact) {
        this.retailercontact = retailercontact;
    }
}
