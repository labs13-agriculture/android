package com.earthdefensesystem.tiemendo.network;

import com.earthdefensesystem.tiemendo.FarmerSearchActivity;
import com.earthdefensesystem.tiemendo.model.Client;
import com.earthdefensesystem.tiemendo.model.Installment;
import com.earthdefensesystem.tiemendo.model.Organization;
import com.earthdefensesystem.tiemendo.model.Token;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TiemeService {

    @Headers({"Content-Type: application/x-www-form-urlencoded",
            "Authorization: Basic bGFtYmRhLWNsaWVudDpsYW1iZGEtc2VjcmV0"})
    @POST("/oauth/token")
    Call<Token> getAccessToken(@Query("grant_type") String grant_type, @Query("username") String username, @Query("password") String password);

    @POST("/farmers/add")
    Call<Client> addFarmer(@Query("address") String address, @Query("community") String community,
                           @Query("dateofbirth") String dateofbirth, @Query("district") String district,
                           @Query("educationlevel") String educationlevel, @Query("email") String email,
                           @Query("firstName") String firstName, @Query("gender") String gender,
                           @Query("landmark") String landmark, @Query("lead") Boolean lead,
                           @Query("nationality") String nationality, @Query("phone") String phone,
                           @Query("position") String position, @Query("region") String region,
                           @Query("secondName") String secondName, @Query("startyear") int startyear,
                           @Query("title") String title, @Query("type") String type);

    @GET("/farmers/all")
    Call<List<Client>> getFarmers(@Header("Authorization: Bearer ") String accessToken);

    @GET("/farmers/farmer/{id}")
    Call<Client> getFarmerWithId(@Path("id") String id,
                                 @Header("Authorization: Bearer ") String accessToken);

    @PUT("/farmers/farmer/{id}")
    Call<Client> updateFarmerWithId(@Path("id") String id,
                                    @Header("Authorization: Bearer ") String accessToken,
                                    @Query("address") String address, @Query("community") String community,
                                    @Query("dateofbirth") String dateofbirth, @Query("district") String district,
                                    @Query("educationlevel") String educationlevel, @Query("email") String email,
                                    @Query("firstName") String firstName, @Query("gender") String gender,
                                    @Query("landmark") String landmark, @Query("lead") Boolean lead,
                                    @Query("nationality") String nationality, @Query("phone") String phone,
                                    @Query("position") String position, @Query("region") String region,
                                    @Query("secondName") String secondName, @Query("startyear") int startyear,
                                    @Query("title") String title, @Query("type") String type);

    @DELETE("/farmers/farmer/{id}")
    Call<Client> deleteFarmerWithId(@Path("id") String id,
                                    @Header("Authorization: Bearer ") String accessToken);

    @GET("/installments/{installmentId}")
    Call<Installment> getInstallmentById(@Path("installmentId") String id,
                                         @Header("Authorization: Bearer ") String accessToken);


    @GET("/installments/installment-list/{clientid}")
    Call<List<Installment>> getInstallmentbyClientId(@Path("clientid") String id,
                                                     @Header("Authorization: Bearer ") String accessToken);

    @POST("/installments/new-installment/{clientid}")
    Call<Installment> newInstallmentbyClientId(@Path("clientid") String id,
                                               @Header("Authorization: Bearer ") String accessToken,
                                               @Query("amountPaid") double amountPaid,
                                               @Query("datepaid") String datepaid,
                                               @Query("mode") String mode,
                                               @Query("officer") String officer);
}
