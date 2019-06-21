package com.earthdefensesystem.tiemendo.network;

import com.earthdefensesystem.tiemendo.FarmerSearchActivity;
import com.earthdefensesystem.tiemendo.model.Client;
import com.earthdefensesystem.tiemendo.model.Installment;
import com.earthdefensesystem.tiemendo.model.ItemType;
import com.earthdefensesystem.tiemendo.model.Organization;
import com.earthdefensesystem.tiemendo.model.Token;
import com.earthdefensesystem.tiemendo.model.Transaction;
import com.earthdefensesystem.tiemendo.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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

    @Headers({"Content-Type: application/json"})
    @POST("/farmers/add")
    Call<Client> addFarmer(@Header("Authorization") String accessToken, @Body Client client);

    @GET("/farmers/all")
    Call<List<Client>> getFarmers(@Header("Authorization") String accessToken);

    @GET("/farmers/farmer/{id}")
    Call<Client> getFarmerWithId(@Path("id") String id,
                                 @Header("Authorization") String accessToken);

    @PUT("/farmers/farmer/{id}")
    Call<Client> updateFarmerWithId(@Path("id") String id,
                                    @Header("Authorization") String accessToken);

    @DELETE("/farmers/farmer/{id}")
    Call<Client> deleteFarmerWithId(@Path("id") String id,
                                    @Header("Authorization") String accessToken);

    @POST("/retailer/new")
    Call<Client> addRetailer(@Header("Authorization: Bearer ") String accessToken);

    @GET("/retailer/retailers")
    Call<List<Client>> getRetailers(@Header("Authorization: Bearer ") String accessToken);

    @GET("/retailer/{id}")
    Call<Client> getRetailerWithId(@Path("id") String id,
                                   @Header("Authorization: Bearer ") String accessToken);

    @PUT("/retailer/update/{id}")
    Call<Client> updateClientWithId(@Path("id") String id,
                                    @Header("Authorization: Bearer ") String accessToken);

    @DELETE("/retailer/delete/{id}")
    Call<Client> deleteRetailerWithId(@Path("id") String id,
                                      @Header("Authorization: Bearer ") String accessToken);

    @GET("/installments/{installmentId}")
    Call<Installment> getInstallmentById(@Path("installmentId") String id,
                                         @Header("Authorization: Bearer ") String accessToken);


    @GET("/installments/installment-list/{clientid}")
    Call<List<Installment>> getInstallmentbyClientId(@Path("clientid") String id,
                                                     @Header("Authorization") String accessToken);

    @POST("/installments/new-installment/{clientid}")
    Call<Installment> newInstallmentbyClientId(@Path("clientid") String id,
                                               @Header("Authorization: Bearer ") String accessToken);

    @POST("/itemtype/add")
    Call<ItemType> addItemType(@Header("Authorization: Bearer ") String accessToken);

    @GET("/itemtype/all")
    Call<List<ItemType>> getItemTypes(@Header("Authorization") String accessToken);

    @PUT("/itemtype/update/{itemtypeid}")
    Call<ItemType> updateItemType(@Header("Authorization: Bearer ") String accessToken,
                                  @Path("itemtypeid") String id);

    @POST("/users/newuser")
    Call<User> newUser(@Header("Authorization: Bearer ") String accessToken);

    @GET("/transaction/client/{id}")
    Call<List<Transaction>> getTransactionById(@Path("id") String id,
                                               @Header("Authorization") String accessToken);
}
