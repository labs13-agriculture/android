package com.earthdefensesystem.tiemendo.network;

import com.earthdefensesystem.tiemendo.model.Farmer;
import com.earthdefensesystem.tiemendo.model.OrganizaionContact;
import com.earthdefensesystem.tiemendo.model.Organization;
import com.earthdefensesystem.tiemendo.model.OrganizationLocation;
import com.earthdefensesystem.tiemendo.model.Retailer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface TiemeService {

    @GET("/farmers/all")
    Call<List<Farmer>> getFarmers(@Header("Authorization Bearer") String accessToken);
    @POST("/farmers/add")
    Call<Farmer> addFarmer(@Header("Authorization Bearer") String accessToken);

    @GET("/retailer/retailers")
    Call<List<Retailer>> getRetailers(@Header("Authorization") String accessToken);
    @POST("/retailer/new")
    Call<Retailer> addRetailer(@Header("Authorization Bearer") String accessToken);

    @GET("/organizations/contacts-list")
    Call<List<OrganizaionContact>> getOrganizationContacts(@Header("Authorization Bearer") String accessToken);
    @GET("/organizations/organizations-list")
    Call<List<Organization>> getOrganizations(@Header("Authorization Bearer") String accessToken);
    @GET("/organizations/locations-list")
    Call<List<OrganizationLocation>> getOrganizationLocations(@Header("Authorization Bearer") String accessToken);
    @POST("/organizations/new-organization")
    Call<Organization> addOrganization(@Header("Authorization Bearer") String accessToken);

}
