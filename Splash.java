package com.sp.foodlimits;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;


public class Splash extends AppCompatActivity{
    private ProgressBar progressBar;
    private Timer timer;
    private int i=0;
    MediaPlayer music;
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        progressBar = findViewById(R.id.progressBar);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Thread thread = new Thread(){
            @Override
            public void run(){
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if(i<100){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                }
                            });progressBar.setProgress(i);

                            i++;
                        } else {
                            timer.cancel();
                            Intent intent = new Intent(Splash.this,Mainpage.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                },0,50);
                try {
                    music= MediaPlayer.create(Splash.this,R.raw.loading);
                    music.start();
                    sleep(5000);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    Intent mainIntent = new Intent(Splash.this,Mainpage.class);
                    startActivity(mainIntent);
                }
            }
        };
        thread.start();
    }
    @Override
    protected void onPause(){
        super.onPause();
        finish();
    }
}