package com.example.myapplication.ui.foodListing;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FoodListingViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public FoodListingViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is foodListing fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}