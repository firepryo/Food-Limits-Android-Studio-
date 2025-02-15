package com.sp.foodlimits;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class HomeAboutUs extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Button insta;
    Button whatsapp;
    Button gmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_about_us);
        drawerLayout = findViewById(R.id.drawer_layout);
        insta = findViewById(R.id.instagram);
        whatsapp = findViewById(R.id.whatsapp);
        gmail = findViewById(R.id.Gmail);
        String num = "+6598292447";

        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://api.whatsapp.com/send?phone="+num);
                Intent whatsapp = new Intent(Intent.ACTION_VIEW, uri);
                whatsapp.setPackage("com.whatsapp");
                try {
                    startActivity(whatsapp);
                }catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://api.whatsapp.com/send?phone="+num)));
                }
            }
        });

        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.instagram.com/firepryo/");
                Intent instagram = new Intent(Intent.ACTION_VIEW, uri);
                instagram.setPackage("com.instagram.android");
                try {
                    startActivity(instagram);
                } catch (ActivityNotFoundException e){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/firepryo/")));
                }
            }
        });

        gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeAboutUs.this,Email.class);
                startActivity(intent);
            }
        });
    }

    public void ClickMenu(View view){HomeMain.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view){
        HomeMain.closeDrawer(drawerLayout);
    }

    public void ClickHome(View view){
        HomeMain.redirectActivity(this,HomeMain.class);
    }

    public void ClickDashboard(View view){
        HomeMain.redirectActivity(this,HomeDashboard.class);
    }

    public void ClickAboutUs(View view){
        recreate();
    }

    public void ClickLogout(View view){
        HomeMain.logout(this);
    }

    public void ClickWeb(View view){HomeMain.redirectActivity(this,Website.class);}



    @Override
    protected void onPause(){
        super.onPause();
        HomeMain.closeDrawer(drawerLayout);
    }
}