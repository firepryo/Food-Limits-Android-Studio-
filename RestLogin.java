package com.sp.foodlimits;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class RestLogin extends AppCompatActivity {
    public static final int GOOGLE_SIGN_IN_CODE = 10005;
    private Button signup;
    private Button login;
    private Button passwdforget;
    private Button FBsignin;
    SignInButton signin;
    private EditText useremail, userpassword;
    private FirebaseAuth firebaseAuth;
    AlertDialog.Builder reset_alert;
    private LayoutInflater inflater;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_login);
        mAuth = FirebaseAuth.getInstance();

        // Initialize Facebook Login button;

        firebaseAuth = FirebaseAuth.getInstance();
        reset_alert = new AlertDialog.Builder(this);
        inflater = this.getLayoutInflater();
        passwdforget = findViewById(R.id.forgetpasswd);
        signup = findViewById(R.id.register);
        login = findViewById(R.id.HomeLogin);
        useremail = findViewById(R.id.useremail);
        userpassword = findViewById(R.id.userpassword);

        // gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        //signInClient = GoogleSignIn.getClient(this,gso);

        //GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);

        //if(signInAccount!= null){
        //   startActivity(new Intent(this,homeownermain.class));
        // }

        /*signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent sign = signInClient.getSignInIntent();
                 startActivityForResult(sign, GOOGLE_SIGN_IN_CODE);

            }
        });*/

        passwdforget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = inflater.inflate(R.layout.activity_forget_passwd, null);
                reset_alert.setTitle("Forgot password?").setMessage("Enter your email to get Password Reset Link").setPositiveButton("Reset", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText email = view.findViewById(R.id.forgetpasswd);
                        if (email.getText().toString().isEmpty()) {
                            email.setError("Required Field");
                            return;
                        }
                        firebaseAuth.sendPasswordResetEmail(email.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(RestLogin.this, "Email to reset password has been sent", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(RestLogin.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }).setNegativeButton("Cancel", null).setView(view).create().show();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterAcct.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (useremail.getText().toString().isEmpty()) {
                    useremail.setError("Email is Missing");
                    return;
                }

                if (userpassword.getText().toString().isEmpty()) {
                    userpassword.setError("Password is Missing");
                    return;
                }
                firebaseAuth.signInWithEmailAndPassword(useremail.getText().toString(), userpassword.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        startActivity(new Intent(getApplicationContext(), RestMain.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RestLogin.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GOOGLE_SIGN_IN_CODE){
            Task<GoogleSignInAccount> signInTask = GoogleSignIn.getSignedInAccountFromIntent(data);
            Toast.makeText(this, "Successfully Authenticated with Google Account", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,homeownermain.class));
            try {
                GoogleSignInAccount signInAcc = signInTask.getResult(ApiException.class);
            } catch (ApiException e ) {
                e.printStackTrace();
            }

        }
    }*/
}