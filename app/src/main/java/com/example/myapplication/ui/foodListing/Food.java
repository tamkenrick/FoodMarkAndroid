package com.example.myapplication.ui.foodListing;

import com.example.myapplication.NutritionItem;

import java.util.Date;
import java.util.List;

public class Food {

    private Long id;

    private Double quantity;

    private String type;

    private String unit;
    private String name;
    private String expiryDate;

    private List<NutritionItem> nutritionLabels;
    private String image;

    public Food(String name, String expiryDate) {
        this.name = name;
        this.expiryDate = expiryDate;
    }

    public Food() {

    }

    public List<NutritionItem> getNutritionLabels() {
        return nutritionLabels;
    }

    public void setNutritionLabels(List<NutritionItem> nutritionLabels) {
        this.nutritionLabels = nutritionLabels;
    }

    public String getName() {
        return name;
    }

    public String getExpiryDate() {
        return expiryDate;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}