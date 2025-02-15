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

import java.util.ArrayList;

public class RestListings extends AppCompatActivity{
    DrawerLayout drawerLayout;
    RecyclerView recyclerView;
    ArrayList<UserHome> UserArrayList;
    AdapterHome adapterHome;
    FirebaseFirestore db;
    ProgressDialog progressDialog;
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rest_home_dashboard);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();
        drawerLayout = findViewById(R.id.drawer_layout);
        recyclerView = findViewById(R.id.recyclerview2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        db = FirebaseFirestore.getInstance();
        UserArrayList = new ArrayList<UserHome>();
        adapterHome = new AdapterHome(RestListings.this,UserArrayList);
        recyclerView.setAdapter(adapterHome);
        back = findViewById(R.id.superlist);
        EventChangeListener();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RestListings.this,HomeDashboard.class);
                startActivity(intent);
            }
        });
    }
    private void EventChangeListener(){
        db.collection("restaurant").orderBy("Description",Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
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
                        UserArrayList.add(dc.getDocument().toObject(UserHome.class));
                    }
                    adapterHome.notifyDataSetChanged();
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