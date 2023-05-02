package com.example.myapplication.ui.foodCreate;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.NutritionItem;
import com.example.myapplication.R;
import com.example.myapplication.ui.foodListing.Food;
import com.example.myapplication.ui.foodListing.FoodApiService;
import com.example.myapplication.ui.foodListing.FoodListingFragment;
import com.example.myapplication.ui.foodListing.FoodListingViewModel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
    private EditText expiryDatePicker;
    private EditText quantityEditText;
    private EditText quantityUnitEditText;
    private Button saveButton;

    private String selectedFoodType;

    private String base64Image;

    private FoodListingViewModel viewModel;

    private NutritionLabelAdapter nutritionLabelAdapter;

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageUri;

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

        // Set up RecyclerView and adapter
        nutritionLabelAdapter = new NutritionLabelAdapter(new ArrayList<>());
        nutritionLabelRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        nutritionLabelRecyclerView.setAdapter(nutritionLabelAdapter);

        // Set up click listeners for buttons
        addImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to open the image picker or camera application
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);

                // Start an activity to allow the user to select an image from their file or camera
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });


        addNutritionItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NutritionItem nutritionItem = new NutritionItem("","");
                nutritionLabelAdapter.getItems().add(nutritionItem);
                nutritionLabelAdapter.notifyDataSetChanged();
            }
        });


        foodTypeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Update the selected food type variable based on the checked radio button's ID
                switch (checkedId) {
                    case R.id.vegetable_radio_button:
                        selectedFoodType = "Vegetable";
                        break;
                    case R.id.meat_radio_button:
                        selectedFoodType = "Meat";
                        break;
                    case R.id.fruit_radio_button:
                        selectedFoodType = "Fruit";
                        break;
                    default:
                        selectedFoodType = "Vegetable";
                        break;
                }
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the values from the UI elements
                String foodName = foodNameEditText.getText().toString();
                Double quantity = Double.parseDouble(quantityEditText.getText().toString());
                String unit = quantityUnitEditText.getText().toString();
                String expiryDate = expiryDatePicker.getText().toString();
                List<NutritionItem> nutritionItems = nutritionLabelAdapter.getItems();

                // Create the Food objects with the values
                List<Food> foods = new ArrayList<>();
                Food food = new Food();
                food.setName(foodName);
                food.setType(selectedFoodType);
                food.setImage(base64Image);
                food.setQuantity(quantity);
                food.setUnit(unit);
                food.setExpiryDate(expiryDate);
                food.setNutritionLabels(nutritionItems);

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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            // Get the selected image URI and save it to a variable
            imageUri = data.getData();

            base64Image = compressImage(imageUri);
        }
    }

    private String compressImage(Uri imageUri) {
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), imageUri);

            // Calculate the new dimensions of the bitmap
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            float aspectRatio = (float) width / (float) height;
            int newWidth = 250;
            int newHeight = (int) (newWidth / aspectRatio);

            // Create a new bitmap with the new dimensions
            Bitmap newBitmap = Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true);

            // Compress the new bitmap to a JPEG file with a quality of 80%
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            newBitmap.compress(Bitmap.CompressFormat.JPEG, 80, outputStream);

            // Compress the JPEG file to a byte array
            byte[] bytes = outputStream.toByteArray();

            // Check if the compressed file size is within the limit of 64kb
            if (bytes.length > 64 * 1024) {
                throw new Exception("Image file size is too large.");
            }

            // Convert the byte array to a base64 string
            return Base64.encodeToString(bytes, Base64.DEFAULT);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(FoodListingViewModel.class);
    }

}