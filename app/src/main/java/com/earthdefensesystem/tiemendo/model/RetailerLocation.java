package com.earthdefensesystem.tiemendo.model;

import org.json.JSONException;
import org.json.JSONObject;

public class RetailerLocation {
    private String address, region, district, community, landmark;
    private Retailer retailer;

    public RetailerLocation(String address, String region, String district, String community, String landmark, Retailer retailer) {
        this.address = address;
        this.region = region;
        this.district = district;
        this.community = community;
        this.landmark = landmark;
        this.retailer = retailer;
    }

}
