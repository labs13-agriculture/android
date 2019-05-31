package com.earthdefensesystem.tiemendo.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Users {
    private long userid;
    private String username, password;

    public Users(){

    }

    public Users(JSONObject json){
        try {
            this.userid = json.getLong("userid");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.username = json.getString("username");
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        try {
//            this.password = json.getString("password");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
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
