package com.earthdefensesystem.tiemendo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.earthdefensesystem.tiemendo.model.User;
import com.earthdefensesystem.tiemendo.network.NetworkAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.earthdefensesystem.tiemendo.network.UserDao.USER_URL;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "Agriculture test";


    LinearLayout linearView;
    ScrollView scrollView;
    Context context;
    Button loginButton,backendButton;
    EditText emailText, passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.login_button);
        backendButton = findViewById(R.id.backend_button);

        emailText = findViewById(R.id.email_edittext);
        passwordText = findViewById(R.id.password_edittext);

        linearView = findViewById(R.id.linear_view);
        scrollView = findViewById(R.id.scroll_view);
        context = this;


        final ArrayList<User> data = new ArrayList<>();

        backendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = emailText.getText().toString();
                final String password = passwordText.getText().toString();

                if(validateLogin(email,password)){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String auth = Base64.encodeToString("lambda-client:lambda-secret".getBytes(), Base64.DEFAULT);

                            Map<String, String> headerProperties = new HashMap<>();
                            headerProperties.put("Authorization", "Basic " + auth);

                            String tokenUrl= "https://tieme-ndo-backend.herokuapp.com/oauth/token?grant_type=password&username="
                                    +email+"&password="
                                    +password+"&scope=";

                            String tokenRequest = null;
                            try {
                                tokenRequest = NetworkAdapter.httpRequest(
                                        tokenUrl, "POST", null, headerProperties);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            Log.i(TAG, tokenRequest);
                            try {
                                String token = new JSONObject(tokenRequest).getString("access_token");

                                headerProperties.clear();
                                headerProperties.put("Authorization", "Bearer " + token);
                                try {
                                    String result = null;
                                    try {
                                        result = NetworkAdapter.httpRequest(USER_URL, "GET", null, headerProperties);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    JSONArray dataJsonArray = new JSONArray(result);

                                    for (int i = 0; i < dataJsonArray.length(); ++i) {
                                        User user = new User(dataJsonArray.getJSONObject(i));
                                        data.add(user);
                                    }
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            for(int i = 0; i < data.size(); i++) {
                                                TextView textView = new TextView(context);
                                                final User getUser = data.get(i);


                                                textView.setText(String.format("%s",
                                                        getUser.));
                                                textView.setTextSize(20);
                                                linearView.addView(textView);
                                            }
                                        }
                                    });
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }

            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, DashboardActivity.class);
                startActivity(i);
            }
        });
    }

    private boolean validateLogin(String email, String password) {
        if (email == null || email.trim().length() == 0) {
            Toast.makeText(this, "E-mail is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password == null || password.trim().length() == 0) {
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        String result = null;
//                        try {
//                            result = NetworkAdapter.httpRequest(USER_URL, "GET", null,null);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        JSONArray dataJsonArray = null;
//                        try {
//                            dataJsonArray = new JSONArray(result);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                        for (int i = 0; i <dataJsonArray.length(); ++i){
//                            User user = null;
//                            try {
//                                user = new User(dataJsonArray.getJSONObject(i));
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                            data.add(user);
//                        }
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                for(int i = 0; i < data.size(); i++){
//                                    TextView textView = new TextView(context);
//                                    final User getUsers = data.get(i);
//
//                                    textView.setText(String.format("%s", getUsers.getUsername()));
//                                    textView.setTextSize(20);
//                                    linearView.addView(textView);
//                                }
//                            }
//                        });
//                    }
//                }).start();
//            }
//        });
//    }
}
