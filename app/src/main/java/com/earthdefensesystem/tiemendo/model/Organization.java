package com.earthdefensesystem.tiemendo.model;

public class Organization {
    private long id;
    private String name;
    private String headquaters;
    private int beneficiaries;
    private OrganizaionContact organizaioncontact;
    private boolean lead;

    public Organization(long id, String name, String headquaters, int beneficiaries, OrganizaionContact organizaioncontact, boolean lead) {
        this.id = id;
        this.name = name;
        this.headquaters = headquaters;
        this.beneficiaries = beneficiaries;
        this.organizaioncontact = organizaioncontact;
        this.lead = lead;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isLead() {
        return lead;
    }

    public void setLead(boolean lead) {
        this.lead = lead;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadquaters() {
        return headquaters;
    }

    public void setHeadquaters(String headquaters) {
        this.headquaters = headquaters;
    }

    public int getBeneficiaries() {
        return beneficiaries;
    }

    public void setBeneficiaries(int beneficiaries) {
        this.beneficiaries = beneficiaries;
    }

    public OrganizaionContact getOrganizaioncontact() {
        return organizaioncontact;
    }

    public void setOrganizaioncontact(OrganizaionContact organizaioncontact) {
        this.organizaioncontact = organizaioncontact;
    }
}
