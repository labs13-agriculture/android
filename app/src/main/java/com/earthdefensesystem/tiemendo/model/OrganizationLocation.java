package com.earthdefensesystem.tiemendo.model;

public class OrganizationLocation {
    private long organizationlocationid;
    private String address;
    private String district;
    private String region;
    private String landmark;

    public OrganizationLocation(long organizationlocationid, String address, String district, String region, String landmark) {
        this.organizationlocationid = organizationlocationid;
        this.address = address;
        this.district = district;
        this.region = region;
        this.landmark = landmark;
    }

    public long getOrganizationlocationid() {
        return organizationlocationid;
    }

    public void setOrganizationlocationid(long organizationlocationid) {
        this.organizationlocationid = organizationlocationid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }
}
