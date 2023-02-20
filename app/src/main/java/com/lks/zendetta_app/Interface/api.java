package com.lks.zendetta_app.Interface;

import com.lks.zendetta_app.Model.home;
import com.lks.zendetta_app.Model.patientModel;
import com.lks.zendetta_app.Model.requestModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface api {

    String BASE_URL = "https://1cf3-103-213-129-16.ap.ngrok.io/api/";

    @GET("home")
    Call<List<home>> getHome();

    @GET("visit")
    Call<List<home>> getRequest();

    @GET("home")
    Call<List<requestModel>> getFirst();

    @GET("patient")
    Call<List<patientModel>> getPatient();

    @FormUrlEncoded
    @POST("visit")
    Call<requestModel> getVisit(@Field("id") String id);

    @FormUrlEncoded
    @POST("visit")
    Call<List<requestModel>> getVisit2(@Field("id") String id);
}
