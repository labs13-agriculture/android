package com.earthdefensesystem.tiemendo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Client implements Serializable {
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("community")
    @Expose
    private String community;
    @SerializedName("dateofbirth")
    @Expose
    private String dateofbirth;
    @SerializedName("district")
    @Expose
    private String district;
    @SerializedName("educationlevel")
    @Expose
    private String educationlevel;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("landmark")
    @Expose
    private String landmark;
    @SerializedName("lead")
    @Expose
    private Boolean lead;
    @SerializedName("nationality")
    @Expose
    private String nationality;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("secondName")
    @Expose
    private String secondName;
    @SerializedName("startyear")
    @Expose
    private Integer startyear;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("type")
    @Expose
    private String type;

    public Client(String address, String community, String dateofbirth, String district,
                  String educationlevel, String email, String firstName, String gender, Integer id,
                  String landmark, Boolean lead, String nationality, String phone, String position,
                  String region, String secondName, Integer startyear, String title, String type) {
        this.address = address;
        this.community = community;
        this.dateofbirth = dateofbirth;
        this.district = district;
        this.educationlevel = educationlevel;
        this.email = email;
        this.firstName = firstName;
        this.gender = gender;
        this.id = id;
        this.landmark = landmark;
        this.lead = lead;
        this.nationality = nationality;
        this.phone = phone;
        this.position = position;
        this.region = region;
        this.secondName = secondName;
        this.startyear = startyear;
        this.title = title;
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getEducationlevel() {
        return educationlevel;
    }

    public void setEducationlevel(String educationlevel) {
        this.educationlevel = educationlevel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public Boolean getLead() {
        return lead;
    }

    public void setLead(Boolean lead) {
        this.lead = lead;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Integer getStartyear() {
        return startyear;
    }

    public void setStartyear(Integer startyear) {
        this.startyear = startyear;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
