package com.earthdefensesystem.tiemendo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.earthdefensesystem.tiemendo.model.Token;
import com.earthdefensesystem.tiemendo.network.NetworkAdapter;
import com.earthdefensesystem.tiemendo.network.RetrofitClientInstance;
import com.earthdefensesystem.tiemendo.network.TiemeService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "Golly";

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
        scrollView = findViewById(R.id.scroll_view_inventory);
        context = this;

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = emailText.getText().toString();
                final String password = passwordText.getText().toString();

                if (validateLogin(email, password)) {
                    TiemeService service = RetrofitClientInstance.getRetrofitInstance()
                            .create(TiemeService.class);
                    Call<Token> call = service.getAccessToken("password", email, password);
                    call.enqueue(new Callback<Token>() {
                        @Override
                        public void onResponse(Call<Token> call, Response<Token> response) {
                            if (response.isSuccessful()) {
                                Token token = response.body();
                                String accessToken = token.getAccessToken();
                                Log.e(TAG, accessToken);

                                SharedPreferences settings = getSharedPreferences(
                                        "mysettings", MODE_PRIVATE);
                                SharedPreferences.Editor editor = settings.edit();
                                editor.putString(
                                        "mystring", token.getAccessToken());
                                editor.apply();
                                Intent i = new Intent(
                                        LoginActivity.this, DashboardActivity.class);
                                startActivity(i);
                            } else {
                                Toast toast = Toast.makeText(
                                        context, "Username or Password is incorrect",
                                        Toast.LENGTH_SHORT);
                                toast.show();
                            }


                        }

                        @Override
                        public void onFailure(Call<Token> call, Throwable t) {
                            Log.e(TAG, "Something went wrong");

                        }
                    });
                }
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

}
