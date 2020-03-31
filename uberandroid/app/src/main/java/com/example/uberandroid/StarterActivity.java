package com.example.uberandroid;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;


public class StarterActivity extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Log.i("info", "StarterApplication start!!!");

        // Add your initialization code here
        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("buWJoy3IeDCPLiHrudavxahnCAY2mNSXL5j5NYGz")
                .clientKey("hCfhM5Eut5o1WsS3d3gD4zCv5tXbQLRQu7ChFAMZ")
                .server("https://parseapi.back4app.com/")
                .build()
        );

//        ParseObject object = new ParseObject("ExampleObject");
//        object.put("myNumber", "321");
//        object.put("myString", "john");
//
//        object.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException ex) {
//                if (ex == null) {
//                    Log.i("Parse Result", "Successful!");
//                } else {
//                    Log.i("Parse Result", "Failed" + ex.toString());
//                }
//            }
//        });

        //ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
    }
}