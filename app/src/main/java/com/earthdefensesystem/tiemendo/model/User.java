package com.earthdefensesystem.tiemendo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    @Expose
    @SerializedName("userid")
    private long userid;
    @Expose
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;
    @SerializedName("userRoles")
    @Expose
    private List<UserRoles> userroles;

    public User(long userid, String username, String password, List<UserRoles> userroles) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.userroles = userroles;
    }

    public List<UserRoles> getUserroles() {
        return userroles;
    }

    public void setUserroles(List<UserRoles> userroles) {
        this.userroles = userroles;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}