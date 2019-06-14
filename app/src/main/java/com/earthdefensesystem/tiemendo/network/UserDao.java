package com.earthdefensesystem.tiemendo.network;

import android.util.Base64;

import com.earthdefensesystem.tiemendo.model.User;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserDao {
    public static final String USER_URL = "https://tieme-ndo-backend.herokuapp.com/";

    public static Map<String, String> headerProperties;

    public static Map<String, String> logIn(String username, String password){
        String token = "";
        String auth = Base64.encodeToString("lambda-client:lambda-secret".getBytes(), Base64.DEFAULT);

        headerProperties = new HashMap<>();
        headerProperties.put("Authorization", "Basic " + auth);

        String tokenRequest = null;
        tokenRequest = NetworkAdapter.httpRequest(USER_URL +
                "oauth/token?grant_type=password&" +
                "username=" + username + "&password=" + password,
                "POST", null, headerProperties );

    }
}
