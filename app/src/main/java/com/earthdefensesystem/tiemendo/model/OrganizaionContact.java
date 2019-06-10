package com.earthdefensesystem.tiemendo.model;

public class OrganizaionContact {
    private long organizatoncontactid;
    private String name;
    private String phone;
    private String email;
    private String position;
    private Organization organization;

    public OrganizaionContact(long organizatoncontactid, String name, String phone, String email, String position, Organization organization) {
        this.organizatoncontactid = organizatoncontactid;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.position = position;
        this.organization = organization;
    }

    public long getOrganizatoncontactid() {
        return organizatoncontactid;
    }

    public void setOrganizatoncontactid(long organizatoncontactid) {
        this.organizatoncontactid = organizatoncontactid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
