package com.earthdefensesystem.tiemendo.network;

import com.earthdefensesystem.tiemendo.model.Farmer;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface FarmerService {

    @FormUrlEncoded
    @POST("createuser")
    Call<Farmer> createFarmer(

    );

    @FormUrlEncoded
    @GET("allfarmers")
    Call<Farmer> getFarmers();
}
