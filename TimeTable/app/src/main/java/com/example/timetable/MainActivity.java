package com.example.timetable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView TimeTableListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SeekBar TimeTableSeekBar = (SeekBar) findViewById(R.id.TimeTableSeekBar);
        TimeTableListView = (ListView) findViewById(R.id.TimeTableListView);

        TimeTableSeekBar.setMax(20);
        TimeTableSeekBar.setProgress(10);

        TimeTableSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int min = 1;
                int timesTable;
                if(progress < min){
                    timesTable = 1;
                    TimeTableSeekBar.setProgress(min); //保证seekbar不能拖到最左边底部
                }
                else{
                    timesTable = progress;
                }

                generateTimesTable(timesTable);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        generateTimesTable(10); //使得初始process = 10 的时候有table存在
    }

    public void generateTimesTable(int scale){
        ArrayList<String> timesTableContent = new ArrayList<String>();
        for(int i = 1; i <= 10; i++){ //generate a table: 余下的数是当前第一个数的倍数
            timesTableContent.add(Integer.toString(i * scale));
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, timesTableContent);
        TimeTableListView.setAdapter(arrayAdapter);
    }
}
