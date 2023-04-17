package com.example.myapplication.ui.foodListing;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FoodApiService {
    @GET("user/{userId}")
    Call<List<Food>> getFoodList(@Path("userId") int userId);
}
