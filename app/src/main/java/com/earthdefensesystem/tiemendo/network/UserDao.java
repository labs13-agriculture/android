package com.earthdefensesystem.tiemendo.network;

import com.earthdefensesystem.tiemendo.model.User;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class UserDao {
    public static final String USER_URL = "https://tieme-ndo-backend.herokuapp.com/users";

    public static ArrayList<User> getUserList() {
        ArrayList<User> data = new ArrayList<>();

        try {
            String result = null;
            try {
                result = NetworkAdapter.httpRequest(USER_URL, "GET", null, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
//            JSONObject dataObject = new JSONObject(result);
            JSONArray dataJsonArray = new JSONArray(result);

            for (int i = 0; i < dataJsonArray.length(); ++i) {
                User user = new User(dataJsonArray.getJSONObject(i));
                data.add(user);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }
}
