package com.example.myapplication.ui.foodCreate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

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
                // TODO: Implement button click behavior
            }
        });

        return view;
    }
}