package com.earthdefensesystem.tiemendo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Organization implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("headquarters")
    @Expose
    private String headquarters;
    @SerializedName("beneficiaries")
    @Expose
    private Integer beneficiaries;
    @SerializedName("lead")
    @Expose
    private Boolean lead;

    public Organization(Integer id, String name, String headquarters, Integer beneficiaries,
                        Boolean lead) {
        this.id = id;
        this.name = name;
        this.headquarters = headquarters;
        this.beneficiaries = beneficiaries;
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

    public String getHeadquarters() {
        return headquarters;
    }

    public void setHeadquarters(String headquarters) {
        this.headquarters = headquarters;
    }

    public Integer getBeneficiaries() {
        return beneficiaries;
    }

    public void setBeneficiaries(Integer beneficiaries) {
        this.beneficiaries = beneficiaries;
    }

    public Boolean getLead() {
        return lead;
    }

    public void setLead(Boolean lead) {
        this.lead = lead;
    }
}
