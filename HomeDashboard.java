package com.sp.foodlimits;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class HomeDashboard extends AppCompatActivity{
    DrawerLayout drawerLayout;
    RecyclerView recyclerView;
    ArrayList<HomeUser> HomeUserArrayList;
    StorageReference storageReference;
    HomeAdapter homeAdapter;
    FirebaseFirestore db;
    ProgressDialog progressDialog;
    Button rest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_dashboard);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();
        drawerLayout = findViewById(R.id.drawer_layout);
        recyclerView = findViewById(R.id.recyclerview1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        db = FirebaseFirestore.getInstance();
        HomeUserArrayList = new ArrayList<HomeUser>();
        homeAdapter = new HomeAdapter(HomeDashboard.this, HomeUserArrayList);
        recyclerView.setAdapter(homeAdapter);
        EventChangeListener();
        rest = findViewById(R.id.restlist);

        rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeDashboard.this,RestListings.class);
                startActivity(intent);
            }
        });
    }
    private void EventChangeListener(){
        db.collection("supermarket").orderBy("Expirydate",Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot documentSnapshots, @Nullable FirebaseFirestoreException error) {
                if (error != null){
                    if(progressDialog.isShowing())
                        progressDialog.dismiss();
                    Log.e("Firestore Error",error.getMessage());
                    return;
                }
                for (DocumentChange dc : documentSnapshots.getDocumentChanges()){
                    if(dc.getType() == DocumentChange.Type.ADDED){
                        HomeUserArrayList.add(dc.getDocument().toObject(HomeUser.class));
                    }
                    homeAdapter.notifyDataSetChanged();
                    if(progressDialog.isShowing())
                        progressDialog.dismiss();
                }
            }
        });
    }


    public void ClickMenu(View view){
        HomeMain.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view){
        HomeMain.closeDrawer(drawerLayout);
    }

    public void ClickHome(View view){
        HomeMain.redirectActivity(this,HomeMain.class);
    }

    public void ClickDashboard(View view){
        recreate();
    }

    public void ClickAboutUs(View view){
        HomeMain.redirectActivity(this,HomeAboutUs.class);
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