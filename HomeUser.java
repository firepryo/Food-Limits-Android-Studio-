package com.sp.foodlimits;

import androidx.appcompat.app.AppCompatActivity;

public class HomeUser {
    String Expirydate,Fooditem,Paymentmode,Price,SupermarketLocation,Contact;
    public HomeUser(){}

    public HomeUser(String expirydate, String fooditem, String paymentmode, String price, String supermarketLocation, String contact, String image) {
        Expirydate = expirydate;
        Fooditem = fooditem;
        Paymentmode = paymentmode;
        Price = price;
        SupermarketLocation = supermarketLocation;
        Contact = contact;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }


    public String getExpirydate() {
        return Expirydate;
    }

    public void setExpirydate(String expirydate) {
        this.Expirydate = expirydate;
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

    public String getSupermarketLocation() {
        return SupermarketLocation;
    }

    public void setSupermarketLocation(String supermarketLocation) {
        this.SupermarketLocation = supermarketLocation;
    }
}
