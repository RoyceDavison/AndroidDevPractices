package com.example.cloneinstagram;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.List;


//1.Manifests 要改两处：1."android:name=".StarterApplication">", 2.Permissions
//2.build.gradle project (this is the gradle of the root project, not module)
//3.build.gradle app
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //必看帮助手册：back4app --> https://www.back4app.com/docs/pages/android/how-to-build-an-android-app-on-back4app
        //check whether the user log in or not
        ParseUser.logOut();
        if(ParseUser.getCurrentUser() != null){
            Log.i("CurrentUser", "User logged in: " + ParseUser.getCurrentUser().getUsername());
        } else {
            Log.i("CurrentUser", "User not logged in");
        }
        /*ParseObject score = new ParseObject("Score");
        score.put("username", "Mary");
        score.put("score", "80");
        score.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null){
                    Log.i("SaveInBackground", "Successful");
                } else{
                    Log.i("SaveInBackground", "Failed. Error: " + e.toString());
                }
            }
        });*/

        /*ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");
        query.getInBackground("bmBZEpChiX", new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if(e == null && object != null){
                    object.put("score", 90);
                    object.saveInBackground();

                    Log.i("Username", object.getString("username"));
                    Log.i("Score", Integer.toString(object.getInt("score")));
                }
            }
        });*/

        /*ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");
        query.whereEqualTo("username", "Mary"); //selection
        query.setLimit(1);
        //query.whereGreaterThan("score", 50);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e == null){
                    Log.i("FindCallback", Integer.toString(objects.size()));
                    if(objects.size() > 0){
                        for(ParseObject obj: objects){
                            Log.i("FindCallbackResult", obj.get("username").toString());
                        }
                    }
                }
            }
        });*/

        /* ParseUser to log in
        ParseUser.logInInBackground("qzhao", "wrongpassword", new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(user != null){
                    Log.i("LogIn", "Successful");
                } else{
                    Log.i("Failed", e.toString());
                }
            }
        }); */

        /* Sign Up
        ParseUser user = new ParseUser();
        user.setUsername("qzhao");
        user.setPassword("qwe123");
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null){
                    Log.i("SignUp", "Successful");
                } else{
                    Log.i("Failure", "Failed. Error: " + e.toString());
                }
            }
        });*/

        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }
}
