package com.sp.foodlimits;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PasswordReset extends AppCompatActivity {
    private TextView newpasswd, confpasswd;
    private Button resetpasswd;
    FirebaseUser user;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);

        newpasswd = findViewById(R.id.newpasswd);
        confpasswd = findViewById(R.id.confnewpasswd);
        resetpasswd = findViewById(R.id.resetpasswd);
        user = FirebaseAuth.getInstance().getCurrentUser();

        resetpasswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(newpasswd.getText().toString().isEmpty()){
                    newpasswd.setError("Required Field");
                    return;
                }

                if(confpasswd.getText().toString().isEmpty()){
                    confpasswd.setError("Required Field");
                    return;
                }

                if(!newpasswd.getText().toString().equals(confpasswd.getText().toString())){
                    newpasswd.setError("Passwords do not match");
                    return;
                }

                user.updatePassword(newpasswd.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(PasswordReset.this, "Password updated", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),HomeMain.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(PasswordReset.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
