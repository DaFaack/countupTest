package com.example.stopwatchtest;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private long timeWhenStopped = 0;
    private Chronometer chronometer;
    TextView punkte;
    Button sound1, sound2, stopsound;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sound1 = findViewById(R.id.sound1);
        sound2 = findViewById(R.id.sound2);
        stopsound = findViewById(R.id.stopbutton);


        chronometer =  findViewById(R.id.chronometer);
        punkte =  findViewById(R.id.punkte);


        sound1.setOnClickListener(view -> {
            startChonometer();
            mp = MediaPlayer.create(MainActivity.this, R.raw.sound1);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {

                    chronometer.stop();
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

                    chronometer.stop();
                }
            });
        });



        stopsound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometer.stop();

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
        chronometer.setOnChronometerTickListener(chronometer -> {

            timeWhenStopped = chronometer.getBase() - SystemClock.elapsedRealtime();
            int seconds =  (int) timeWhenStopped / 1000;
            punkte.setText( "Points: " + Math.abs(seconds));
        });

        chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
        chronometer.start();




    }


}