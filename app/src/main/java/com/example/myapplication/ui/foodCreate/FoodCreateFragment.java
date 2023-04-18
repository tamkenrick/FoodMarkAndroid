package com.example.myapplication.ui.foodCreate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.ui.foodListing.Food;
import com.example.myapplication.ui.foodListing.FoodApiService;
import com.example.myapplication.ui.foodListing.FoodListingFragment;
import com.example.myapplication.ui.foodListing.FoodListingViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FoodCreateFragment extends Fragment {

    private EditText foodNameEditText;
    private Button addImageButton;
    private RecyclerView nutritionLabelRecyclerView;
    private Button addNutritionItemButton;
    private RadioGroup foodTypeRadioGroup;
    private DatePicker expiryDatePicker;
    private EditText quantityEditText;
    private EditText quantityUnitEditText;
    private Button saveButton;

    private FoodListingViewModel viewModel;

    public FoodCreateFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_food_create, container, false);

        foodNameEditText = view.findViewById(R.id.food_name_edit_text);
        addImageButton = view.findViewById(R.id.add_image_button);
        nutritionLabelRecyclerView = view.findViewById(R.id.nutrition_label_recycler_view);
        addNutritionItemButton = view.findViewById(R.id.add_nutrition_item_button);
        foodTypeRadioGroup = view.findViewById(R.id.food_type_radio_group);
        expiryDatePicker = view.findViewById(R.id.expiry_date_picker);
        quantityEditText = view.findViewById(R.id.quantity_edit_text);
        quantityUnitEditText = view.findViewById(R.id.quantity_unit_edit_text);
        saveButton = view.findViewById(R.id.save_button);

        // Set up click listeners for buttons
        addImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Implement button click behavior
            }
        });

        addNutritionItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Implement button click behavior
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the values from the UI elements
                String foodName = foodNameEditText.getText().toString();
                String foodType = "null";
                Double quantity = Double.parseDouble(quantityEditText.getText().toString());
                String unit = quantityUnitEditText.getText().toString();
                String expiryDate = "2024-04-30";

                // Create the Food objects with the values
                List<Food> foods = new ArrayList<>();
                Food food = new Food();
                food.setName(foodName);
                food.setType("Vegetable");
                food.setQuantity(quantity);
                food.setUnit(unit);
                food.setExpiryDate(expiryDate);

                foods.add(food);

                // Create a Retrofit instance and set the base URL
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://10.0.2.2:8080/foods/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                // Create an instance of the API interface using the Retrofit instance
                FoodApiService service = retrofit.create(FoodApiService.class);

                // Call the API to save the foods
                Call<List<Food>> call = service.saveFoods(foods, 1);
                call.enqueue(new Callback<List<Food>>() {
                    @Override
                    public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                        // Handle the response
                    }

                    @Override
                    public void onFailure(Call<List<Food>> call, Throwable t) {
                        // Handle the error
                    }
                });

                viewModel.needUpdateNetwork.postValue(true);

                FoodListingFragment foodListingFragment = new FoodListingFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .add(R.id.nav_host_fragment_content_main, foodListingFragment)
                        .commit();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(FoodListingViewModel.class);
    }

}