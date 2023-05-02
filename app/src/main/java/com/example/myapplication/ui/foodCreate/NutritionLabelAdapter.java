package com.example.myapplication.ui.foodCreate;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.NutritionItem;
import com.example.myapplication.R;

import java.util.List;

public class NutritionLabelAdapter extends RecyclerView.Adapter<NutritionLabelAdapter.ViewHolder> {

    private List<NutritionItem> items;

    public NutritionLabelAdapter(List<NutritionItem> items) {
        this.items = items;
    }

    public void addItem(NutritionItem item) {
        items.add(item);
    }

    public List<NutritionItem> getItems() {
        return items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.nutrition_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NutritionItem item = items.get(position);
        holder.nutritionNameEditText.setText(item.getKey());
        holder.nutritionValueEditText.setText(item.getValue());
        holder.nutritionNameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    item.setKey(holder.nutritionNameEditText.getText().toString());
                }
            }
        });
        holder.nutritionValueEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    item.setValue(holder.nutritionValueEditText.getText().toString());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public EditText nutritionNameEditText;
        public EditText nutritionValueEditText;

        public ViewHolder(View itemView) {
            super(itemView);
            nutritionNameEditText = itemView.findViewById(R.id.nutrition_label_edit_text);
            nutritionValueEditText = itemView.findViewById(R.id.nutrition_value_edit_text);
        }
    }
}