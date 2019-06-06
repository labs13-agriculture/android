package com.earthdefensesystem.tiemendo.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class User {
    private long userid;
    private String username;
    private String password;
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