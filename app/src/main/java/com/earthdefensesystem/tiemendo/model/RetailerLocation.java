package com.earthdefensesystem.tiemendo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

public class RetailerLocation {
    @SerializedName("retailerlocationid")
    @Expose
    private Integer retailerlocationid;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("district")
    @Expose
    private String district;
    @SerializedName("community")
    @Expose
    private String community;
    @SerializedName("landmark")
    @Expose
    private String landmark;

    public Integer getRetailerlocationid() {
        return retailerlocationid;
    }

    public void setRetailerlocationid(Integer retailerlocationid) {
        this.retailerlocationid = retailerlocationid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

}
