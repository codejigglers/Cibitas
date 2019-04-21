package com.example.varda.poolshop;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Endpoints {

    @GET("/")
    Call<List<RequestDeliveryModel>> getAllProfs();
}
