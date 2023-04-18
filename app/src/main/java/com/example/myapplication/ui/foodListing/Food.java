package com.example.myapplication.ui.foodListing;

import java.util.Date;

public class Food {

    private Long id;

    private Double quantity;

    private String unit;
    private String name;
    private Date expiryDate;

    private String image;

    public Food(String name, Date expiryDate) {
        this.name = name;
        this.expiryDate = expiryDate;
    }

    public String getName() {
        return name;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}