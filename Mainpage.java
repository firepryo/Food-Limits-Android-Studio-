package com.sp.foodlimits;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.content.Intent;
import android.provider.Settings;
import android.view.View;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;
import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Mainpage extends AppCompatActivity {
    private Button homeowner;
    private Button restaurant;
    private Button supermarket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        homeowner = findViewById(R.id.homeowner);
        restaurant = findViewById(R.id.restaurant);
        supermarket = findViewById(R.id.supermarket);

        homeowner.setOnClickListener(this::onClick);
        restaurant.setOnClickListener(this::onClick);
        supermarket.setOnClickListener(this::onClick);
    }
    public void onClick(View v){
        if(v.getId() == R.id.homeowner){
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        } else if(v.getId() == R.id.supermarket){
            Intent intent = new Intent(this, SuperLogin.class);
            startActivity(intent);
        } else if(v.getId() == R.id.restaurant) {
            Intent intent = new Intent(this, RestLogin.class);
            startActivity(intent);
        }
    }
}