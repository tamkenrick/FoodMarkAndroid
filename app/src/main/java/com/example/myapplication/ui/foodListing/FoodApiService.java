package com.example.myapplication.ui.foodListing;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FoodApiService {
    @GET("user/{userId}")
    Call<List<Food>> getFoodList(@Path("userId") int userId);

    @POST("user/{userId}")
    Call<List<Food>> saveFoods(@Body List<Food> foods, @Path("userId") int userId);
}
