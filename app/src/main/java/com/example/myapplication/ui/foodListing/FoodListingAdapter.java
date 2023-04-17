package com.example.myapplication.ui.foodListing;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FoodListingAdapter extends RecyclerView.Adapter<FoodListingAdapter.ViewHolder> {

    private List<Food> mFoodList;

    public FoodListingAdapter(List<Food> foodList) {
        mFoodList = foodList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView expiryDayTextView;

        public TextView expiryDateTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.food_name);
            expiryDayTextView = itemView.findViewById(R.id.food_expiry_day);
            expiryDateTextView = itemView.findViewById(R.id.food_expiry_date);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_list_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Food food = mFoodList.get(position);
        holder.nameTextView.setText(food.getName());
        Date today = new Date();
        SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
        holder.expiryDateTextView.setText(dmyFormat.format(food.getExpiryDate()));

        long diffInMillies = Math.abs(food.getExpiryDate().getTime() - today.getTime());
        long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        holder.expiryDayTextView.setText(diffInDays + " day(s)");
    }

    @Override
    public int getItemCount() {
        return mFoodList.size();
    }
}