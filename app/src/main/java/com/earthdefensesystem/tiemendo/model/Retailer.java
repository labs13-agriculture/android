package com.earthdefensesystem.tiemendo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Retailer {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private Object type;
    @SerializedName("transactions")
    @Expose
    private List<Object> transactions = null;
    @SerializedName("installments")
    @Expose
    private List<Object> installments = null;
    @SerializedName("startyear")
    @Expose
    private Integer startyear;
    @SerializedName("retailerlocation")
    @Expose
    private RetailerLocation retailerlocation;
    @SerializedName("retailercontact")
    @Expose
    private RetailerContact retailercontact;
    @SerializedName("startYearUnchanged")
    @Expose
    private Integer startYearUnchanged;
    @SerializedName("lead")
    @Expose
    private Boolean lead;

    public Retailer(Integer id, String name, Object type, List<Object> transactions,
                    List<Object> installments, Integer startyear, RetailerLocation retailerlocation,
                    RetailerContact retailercontact, Integer startYearUnchanged, Boolean lead) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.transactions = transactions;
        this.installments = installments;
        this.startyear = startyear;
        this.retailerlocation = retailerlocation;
        this.retailercontact = retailercontact;
        this.startYearUnchanged = startYearUnchanged;
        this.lead = lead;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public List<Object> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Object> transactions) {
        this.transactions = transactions;
    }

    public List<Object> getInstallments() {
        return installments;
    }

    public void setInstallments(List<Object> installments) {
        this.installments = installments;
    }

    public Integer getStartyear() {
        return startyear;
    }

    public void setStartyear(Integer startyear) {
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

    public Integer getStartYearUnchanged() {
        return startYearUnchanged;
    }

    public void setStartYearUnchanged(Integer startYearUnchanged) {
        this.startYearUnchanged = startYearUnchanged;
    }

    public Boolean getLead() {
        return lead;
    }

    public void setLead(Boolean lead) {
        this.lead = lead;
    }

}
