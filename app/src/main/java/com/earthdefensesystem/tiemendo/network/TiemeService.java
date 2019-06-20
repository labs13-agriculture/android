package com.earthdefensesystem.tiemendo.network;

import com.earthdefensesystem.tiemendo.model.Client;
import com.earthdefensesystem.tiemendo.model.Organization;
import com.earthdefensesystem.tiemendo.model.Token;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TiemeService {

    @Headers({"Content-Type: application/x-www-form-urlencoded",
            "Authorization: Basic bGFtYmRhLWNsaWVudDpsYW1iZGEtc2VjcmV0"})
    @POST("/oauth/token")
    Call<Token> getAccessToken(@Query("grant_type") String grant_type, @Query("username") String username, @Query("password") String password);

    @GET("/farmers/all")
    Call<List<Client>> getFarmers(@Header("Authorization Bearer") String accessToken);

}
