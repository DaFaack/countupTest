package com.example.stopwatchtest;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    TextView punkte;
    Button sound1, sound2, stopsound;
    MediaPlayer mp;
    CountDownTimer timer;
    int seconds, allPoints;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sound1 = findViewById(R.id.sound1);
        sound2 = findViewById(R.id.sound2);
        stopsound = findViewById(R.id.stopbutton);


        punkte =  findViewById(R.id.punkte);


        sound1.setOnClickListener(view -> {
            startChonometer();
            mp = MediaPlayer.create(MainActivity.this, R.raw.sound1);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    allPoints = seconds;
                    timer.cancel();
                }
            });
        });

        sound2.setOnClickListener(view -> {
            startChonometer();
            mp = MediaPlayer.create(MainActivity.this, R.raw.sound2);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    allPoints = seconds;
                    timer.cancel();
                }
            });
        });



        stopsound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allPoints = seconds;
                timer.cancel();

                if(mp!=null){
                    mp.stop();
                    mp.release();// this will clear memory
                    mp = null;
                }
            }
        });
    }

    // the method for when we press the 'start' button
    public void startChonometer() {

        long totalSeconds = 30;
        long intervalSeconds = 1;

        timer = new CountDownTimer(totalSeconds * 1000, intervalSeconds * 1000) {

            public void onTick(long millisUntilFinished) {

                seconds = allPoints + (int) ((totalSeconds * 1000 - millisUntilFinished) / 1000);
                punkte.setText( "Points: " + seconds);
            }

            public void onFinish() {
                Log.d( "done!", "Time's up!");
            }

        };
        timer.start();


    }



}