package com.example.uberandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;


//坑死爸爸了！！！！一定一定要记得更新application name/activity这玩意！！ StarterAct. 是一个empty activity!!!
/*
* <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme"
        android:name=".StarterActivity">
        <activity android:name=".StarterActivity"></activity>
* */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    
    public void getStarted(View view){
        Switch userTypeSwitch =(Switch) findViewById(R.id.userTypeSwitch);

        String userType = "rider";
        if(userTypeSwitch.isChecked()){
            userType = "driver";
        }
        ParseUser.getCurrentUser().put("riderOrDriver", userType);
        Log.i("put_feedback", "Successfully");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        //check whether the user is login or not
        if(ParseUser.getCurrentUser() == null){
            ParseAnonymousUtils.logIn(new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if(e == null){
                        Log.i("info", "Anonymously login successful");
                    } else{
                        Log.i("info", "Anonymously login failed");
                    }
                }
            });
        } else{
            Log.i("info", "I am log in and the current user is not null!!!");
            if(ParseUser.getCurrentUser().get("riderOrDriver") != null){
                Log.i("info", "Redirect to" + ParseUser.getCurrentUser().get("riderOrDriver"));
            }
        }

        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }
}
