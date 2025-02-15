package com.sp.foodlimits;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.HashMap;


public class HomePayment extends AppCompatActivity{
    Button scan,payment;
    EditText card,Exp,csv,name,Email;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepay);
        scan = findViewById(R.id.QRscan);
        payment = findViewById(R.id.Homepay);
        card = findViewById(R.id.cardNo);
        Exp = findViewById(R.id.Cardexp);
        csv = findViewById(R.id.CSV);
        name = findViewById(R.id.Cardname);
        Email = findViewById(R.id.Cardemail);
        db = FirebaseFirestore.getInstance();

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Card = card.getText().toString();
                String exp = Exp.getText().toString();
                String Name = name.getText().toString();
                String CSV = csv.getText().toString();
                String email = Email.getText().toString();


                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("CardNumber", Card);
                hashMap.put("CardExpiryDate",exp);
                hashMap.put("NameCard",Name);
                hashMap.put("CSV", CSV);
                hashMap.put("CardEmail", email);

                db.collection("creditinfo").add(hashMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(HomePayment.this, "Credit card info saved, check email for follow up actions", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(HomePayment.this, "Please try again", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePayment.this,scanner.class);
                startActivity(intent);
            }
        });
    }
}