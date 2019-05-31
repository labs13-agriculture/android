package com.earthdefensesystem.tiemendo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.earthdefensesystem.tiemendo.model.Users;
import com.earthdefensesystem.tiemendo.network.NetworkAdapter;
import com.earthdefensesystem.tiemendo.network.UserDao;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import static com.earthdefensesystem.tiemendo.network.UserDao.USER_URL;

public class LoginActivity extends AppCompatActivity {

    LinearLayout linearView;
    ScrollView scrollView;
    Context context;
    Button loginButton;
    EditText emailText, passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.login_button);
        emailText = findViewById(R.id.email_edittext);
        passwordText = findViewById(R.id.password_edittext);

        linearView = findViewById(R.id.linear_view);
        scrollView = findViewById(R.id.scroll_view);
        context = this;


        final ArrayList<Users> data = new ArrayList<>();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String result = null;
                        try {
                            result = NetworkAdapter.httpRequest(USER_URL, "GET", null,null);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        JSONArray dataJsonArray = null;
                        try {
                            dataJsonArray = new JSONArray(result);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        for (int i = 0; i <dataJsonArray.length(); ++i){
                            Users user = null;
                            try {
                                user = new Users(dataJsonArray.getJSONObject(i));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            data.add(user);
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                for(int i = 0; i < data.size(); i++){
                                    TextView textView = new TextView(context);
                                    final Users getUsers = data.get(i);

                                    textView.setText(String.format("%s", getUsers.getUsername()));
                                    textView.setTextSize(20);
                                    linearView.addView(textView);
                                }
                            }
                        });
                    }
                }).start();
            }
        });
    }
}
