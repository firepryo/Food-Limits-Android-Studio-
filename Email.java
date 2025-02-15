package com.sp.foodlimits;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Email extends AppCompatActivity {
  EditText emailaddress,emailsubject,emailmessage;
  Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        emailaddress=findViewById(R.id.edit_text_email);
        emailsubject=findViewById(R.id.edit_text_subject);
        emailmessage=findViewById(R.id.edit_text_message);

        send = findViewById(R.id.sendemail);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });

    }

    private void sendMail() {
        String recipientList = emailaddress.getText().toString();
        String[] recipients = recipientList.split(",");
        String subject = emailsubject.getText().toString();
        String message = emailmessage.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL,recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT,subject);
        intent.putExtra(Intent.EXTRA_TEXT,message);
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent,"Choose an email client"));
    }
}