package com.sp.foodlimits;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class RestMain extends AppCompatActivity {
   Button verifyemail,upload;
    private TextView verifyemailmsg;
    FirebaseAuth firebaseAuth;
    AlertDialog.Builder reset_alert;
    private LayoutInflater inflater;
    DrawerLayout drawerLayout;


    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_main);
        firebaseAuth = FirebaseAuth.getInstance();
        verifyemail = findViewById(R.id.verify);
        verifyemailmsg = findViewById(R.id.verifyemailmsg);
        reset_alert = new AlertDialog.Builder(this);
        inflater = this.getLayoutInflater();
        drawerLayout = findViewById(R.id.drawer_layout);
        upload = findViewById(R.id.uploadfood);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RestMain.this,RestUploadPage.class);
                startActivity(intent);
            }
        });


        if(!firebaseAuth.getCurrentUser().isEmailVerified()){
            verifyemail.setVisibility(View.VISIBLE);
            verifyemailmsg.setVisibility(View.VISIBLE);
        }

        verifyemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.getCurrentUser().sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(),"Verification email sent", Toast.LENGTH_SHORT).show();
                        verifyemail.setVisibility(View.GONE);
                        verifyemailmsg.setVisibility(View.GONE);
                    }
                });
            }
        });
    }
    public void ClickMenu(View view){
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View view){
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void ClickHome(View view){
        recreate();
    }

    public void ClickDashboard(View view){
        redirectActivity(RestMain.this,RestDashboard.class);
    }

    public void ClickAboutUs(View view){
        redirectActivity(RestMain.this,RestAboutus.class);
    }

    public void ClickLogout(View view){
        logout(this);
    }

    public void ClickWeb(View view) { redirectActivity(this,Website.class);}

    public static void logout(Activity activity) {
        AlertDialog.Builder builder= new AlertDialog.Builder(activity);
        builder.setTitle("Logout");
        builder.setMessage("Are you sure you want to logout?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.finishAffinity();
                System.exit(0);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public static void redirectActivity(Activity activity, Class aClass) {
        Intent intent = new Intent(activity,aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    @Override
    protected void onPause(){
        super.onPause();
        closeDrawer(drawerLayout);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(item.getItemId() == R.id.resetpasswd){
            startActivity(new Intent(getApplicationContext(),PasswordReset.class));
        } else if(item.getItemId() == R.id.updateemail){
            View view = inflater.inflate(R.layout.activity_forget_passwd,null);
            reset_alert.setTitle("Update Email").setMessage("Enter New Email Address").setPositiveButton("Update", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    EditText email = view.findViewById(R.id.newpasswd);
                    if (email.getText().toString().isEmpty()) {
                        email.setError("Required Field");
                        return;
                    }
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    user.updateEmail(email.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(RestMain.this, "Email Updated", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(RestMain.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }).setNegativeButton("Cancel", null).setView(view).create().show();
        } else if(item.getItemId() == R.id.delacct){
            reset_alert.setTitle("Delete Account Permanently ?").setMessage("Are You Sure?").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    user.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(RestMain.this, "Account Deleted", Toast.LENGTH_SHORT).show();
                            firebaseAuth.signOut();
                            startActivity(new Intent(getApplicationContext(),Login.class));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(RestMain.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }).setNegativeButton("Cancel",null).create().show();
        }
        return super.onOptionsItemSelected(item);
    }
}
