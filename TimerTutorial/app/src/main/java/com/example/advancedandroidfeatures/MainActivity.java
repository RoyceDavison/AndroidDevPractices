package com.example.advancedandroidfeatures;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView myListView = (ListView) findViewById(R.id.myListView);
        final ArrayList<String> familys = new ArrayList<String>(asList("Rob", "John", "Marry", "Mark"));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, familys);
        myListView.setAdapter(arrayAdapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //parent.setVisibility(View.GONE);
                String name = familys.get(position);
                Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
            }
        });

        /*new CountDownTimer(10000, 1000){

            public void onTick(long millisecondsUntilDone){
                //counter is counting down every second...
                Log.i("second left: ", String.valueOf(millisecondsUntilDone/ 1000));
            }

            public void onFinish(){
                //counter is finished after 10 seconds...
                Log.i("Done", "CountDown Timer is finished!");
            }
        }.start();

        final Handler handler = new Handler();
        Runnable run = new Runnable() {
            @Override
            public void run() {
                Log.i("info", "One second has passed...");

                handler.postDelayed(this, 1000);
            }
        handler.post(run);*/
    }
}
