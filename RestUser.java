package com.sp.foodlimits;

import androidx.appcompat.app.AppCompatActivity;

public class RestUser {
    String Description,Fooditem,Paymentmode,Price,RestaurantLocation,Contact;
    public RestUser(){}

    public RestUser(String description, String fooditem, String paymentmode, String price, String restaurantLocation, String contact) {
        Description = description;
        Fooditem = fooditem;
        Paymentmode = paymentmode;
        Price = price;
        RestaurantLocation = restaurantLocation;
        Contact = contact;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }


    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public String getFooditem() {
        return Fooditem;
    }

    public void setFooditem(String fooditem) {
        this.Fooditem = fooditem;
    }

    public String getPaymentmode() {
        return Paymentmode;
    }

    public void setPaymentmode(String paymentmodeContactNo) {
        this.Paymentmode = paymentmodeContactNo;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        this.Price = price;
    }

    public String getRestaurantLocation() {
        return RestaurantLocation;
    }

    public void setRestaurantLocation(String restaurantLocation) {
        this.RestaurantLocation = restaurantLocation;
    }
}
