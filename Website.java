package com.sp.foodlimits;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class Website extends AppCompatActivity {
     WebView web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        web = findViewById(R.id.webView);
        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        web.setWebViewClient(new WebViewClient());
        web.loadUrl("http://192.168.0.139:8383/MAD/index.html");

    }
    @Override
    public void onBackPressed(){
        if(web.canGoBack()){
            web.goBack();
        }else {
            super.onBackPressed();
        }
    }

}