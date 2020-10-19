package com.example.prefetch_recyclerview.NetworkCalls;

import com.example.prefetch_recyclerview.DataModel2;
import com.example.prefetch_recyclerview.TiktokDataModel;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Api {

    @Headers("Content-Type: application/json")

    @POST("/?p=showAllVideos")
    Call<DataModel2> getvideos(
           @Body JsonObject jsonObject
    );
}
