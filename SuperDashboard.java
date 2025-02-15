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

public class SuperDashboard extends AppCompatActivity{
    DrawerLayout drawerLayout;
    RecyclerView recyclerView;
    ArrayList<SuperUser> SuperUserArrayList;
    SuperAdapter superAdapter;
    FirebaseFirestore db;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_dashboard);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();
        drawerLayout = findViewById(R.id.drawer_layout);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        db = FirebaseFirestore.getInstance();
        SuperUserArrayList = new ArrayList<SuperUser>();
        superAdapter= new SuperAdapter(SuperDashboard.this,SuperUserArrayList);
        recyclerView.setAdapter(superAdapter);
        EventChangeListener();
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
                        SuperUserArrayList.add(dc.getDocument().toObject(SuperUser.class));
                    }
                    superAdapter.notifyDataSetChanged();
                    if(progressDialog.isShowing())
                        progressDialog.dismiss();
                }
            }
        });
    }

    public void ClickMenu(View view){
        SuperMain.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view){
        SuperMain.closeDrawer(drawerLayout);
    }

    public void ClickHome(View view){
        SuperMain.redirectActivity(this,SuperMain.class);
    }

    public void ClickDashboard(View view){
        recreate();
    }

    public void ClickAboutUs(View view){
        SuperMain.redirectActivity(this,SuperAboutUs.class);
    }

    public void ClickLogout(View view){
        SuperMain.logout(this);
    }


    public void ClickWeb(View view) { redirectActivity(this,Website.class);}


    @Override
    protected void onPause(){
        super.onPause();
        SuperMain.closeDrawer(drawerLayout);
    }

}