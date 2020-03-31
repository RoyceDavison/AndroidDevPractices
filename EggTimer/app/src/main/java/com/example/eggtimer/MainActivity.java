package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private SeekBar timerSeekbar;
    private TextView timerTextView;
    private boolean counterIsActive = false;
    private Button controllerButton;
    private CountDownTimer countDownTimer;

    public void updateTimer(int secondsLeft){
        //把当前的progress计算成分钟:秒的形式表现出来
        int minutes = (int) secondsLeft/60;
        int seconds = secondsLeft - minutes * 60;

        String secondsString = Integer.toString(seconds);

        if(seconds <= 9){
            secondsString = "0" + secondsString;
        }
        timerTextView.setText(Integer.toString(minutes) + ":" + secondsString);
    }

    //该函数与button绑定，通过点击button可以实现toggle(GO/STOP)功能哦～
    public void controlTimer(View view){
        if(!counterIsActive) {
            counterIsActive = true;
            timerSeekbar.setEnabled(false);
            controllerButton.setText("STOP");
            countDownTimer = new CountDownTimer(timerSeekbar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    resetTimer(); //when the timer is finished, reset it!
                    //"this" --> CountDownTimer, but we want getApplicationContext()
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mplayer.start();
                    //Log.i("finished", "Done!Done!Done!Done!Done!");
                }
            }.start();
        }
        else{ //user manually reset
            resetTimer();
        }
    }

    public void resetTimer(){
        timerTextView.setText("00:30");
        timerSeekbar.setProgress(30);
        countDownTimer.cancel();
        controllerButton.setText("GO");
        timerSeekbar.setEnabled(true);
        counterIsActive = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controllerButton = (Button) findViewById(R.id.ControllerButton);
        timerSeekbar = (SeekBar) findViewById(R.id.seekBar);
        timerTextView = findViewById(R.id.TimerTextView);

        timerSeekbar.setMax(600); //10 minutes
        timerSeekbar.setProgress(30); //initial setup: 30 seconds

        timerSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            //progress --> int: The current progress level.
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }




}
