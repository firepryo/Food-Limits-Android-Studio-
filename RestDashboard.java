package com.sp.foodlimits;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import static com.sp.foodlimits.RestMain.redirectActivity;

public class RestDashboard extends AppCompatActivity{
    DrawerLayout drawerLayout;
    RecyclerView recyclerView;
    ArrayList<RestUser> RestUserArrayList;
    RestAdapter restAdapter;
    FirebaseFirestore db;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_dashboard);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();
        drawerLayout = findViewById(R.id.drawer_layout);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        db = FirebaseFirestore.getInstance();
        RestUserArrayList = new ArrayList<RestUser>();
        restAdapter = new RestAdapter(RestDashboard.this,RestUserArrayList);
        recyclerView.setAdapter(restAdapter);
        EventChangeListener();
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
                        RestUserArrayList.add(dc.getDocument().toObject(RestUser.class));
                    }
                    restAdapter.notifyDataSetChanged();
                    if(progressDialog.isShowing())
                        progressDialog.dismiss();
                }
            }
        });
    }

    public void ClickMenu(View view){
        RestMain.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view){
        RestMain.closeDrawer(drawerLayout);
    }

    public void ClickHome(View view){
        redirectActivity(this,RestMain.class);
    }

    public void ClickDashboard(View view){
        recreate();
    }

    public void ClickAboutUs(View view){
        SuperMain.redirectActivity(this,RestAboutus.class);
    }

    public void ClickLogout(View view){
        RestMain.logout(this);
    }

    public void ClickWeb(View view) { redirectActivity(this,Website.class);}


    @Override
    protected void onPause(){
        super.onPause();
       RestMain.closeDrawer(drawerLayout);
    }

}