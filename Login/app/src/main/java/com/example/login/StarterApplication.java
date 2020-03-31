package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.os.Bundle;

import com.parse.Parse;
import com.parse.Parse;
import com.parse.ParseACL;

import android.app.Application;

public class StarterApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("coi8o5ldU93cVFDh25zirnqBNHTA0OfO9YAVC8Ib")
                // if defined
                .clientKey("PRKCPnn309oK4g8uRA13P38w7Y5dn5fX5NoBnLzR")
                .server("https://parseapi.back4app.com/")
                .build()
        );

        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
    }
}