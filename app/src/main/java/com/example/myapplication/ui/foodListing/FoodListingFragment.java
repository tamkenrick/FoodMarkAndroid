package com.example.myapplication.ui.foodListing;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FoodListingFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Food> foodList;
    private FoodListingAdapter adapter;
    private int userId = 1; // Change this to the user ID you want to get the food list for
    private String apiUrl = "http://10.0.2.2:8080/foods/";
    private ImageView imageView;
    private FloatingActionButton addButton;
    private View view;

    private static final Logger logger = Logger.getLogger(FoodListingFragment.class.getName());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_food_listing, container, false);

        imageView = view.findViewById(R.id.food_image);
        addButton = view.findViewById(R.id.fab);

        // Set up RecyclerView
        recyclerView = view.findViewById(R.id.food_list_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        // Initialize foodList
        foodList = new ArrayList<>();

        // Set up adapter
        adapter = new FoodListingAdapter(foodList);
        recyclerView.setAdapter(adapter);

        // Set up Retrofit client
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Set up API service
        FoodApiService service = retrofit.create(FoodApiService.class);

        // Make API call to get food list
        Call<List<Food>> call = service.getFoodList(userId);
        call.enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                if (response.isSuccessful()) {
                    foodList.clear();
                    foodList.addAll(response.body());
                    System.out.println("Response:");
                    for(Food food : foodList){
                        System.out.println(food.getExpiryDate());
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace with code to open AddFoodFragment
            }
        });

        return view;
    }

    
}