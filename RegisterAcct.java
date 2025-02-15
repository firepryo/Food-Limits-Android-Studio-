package com.sp.foodlimits;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterAcct extends AppCompatActivity {
    private EditText registerFullName, registerEmail, registerPassword, confPassword;
    private Button RegisterAcct, backtologin;
    FirebaseAuth fAuth;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_acct);

        registerFullName = findViewById(R.id.registerFullName);
        registerEmail = findViewById(R.id.registerEmail);
        registerPassword = findViewById(R.id.registerPassword);
        confPassword = findViewById(R.id.confPassword);
        RegisterAcct = findViewById(R.id.Confregisterr);
        backtologin = findViewById(R.id.backtologin);
        fAuth = FirebaseAuth.getInstance();

        backtologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
                finish();
            }
        });

        RegisterAcct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //extract data from form
                String fullname = registerFullName.getText().toString();
                String email = registerEmail.getText().toString();
                String password = registerPassword.getText().toString();
                String confpassword = confPassword.getText().toString();

                if(fullname.isEmpty()){
                    registerFullName.setError("Full name is required");
                    return;
                }

                if(email.isEmpty()){
                    registerEmail.setError("Email is required");
                    return;
                }

                if(password.isEmpty()){
                    registerPassword.setError("Password is required");
                    return;
                }
                if(!password.equals(confpassword)){
                    confPassword.setError("Passwords do not match");
                    return;
                }

                Toast.makeText(RegisterAcct.this,"Data Validated", Toast.LENGTH_SHORT).show();

                fAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        //send user to next page
                        startActivity(new Intent(getApplicationContext(),HomeMain.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterAcct.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
