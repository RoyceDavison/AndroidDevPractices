package com.example.databasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try{
               /*SQLiteDatabase database = this.openOrCreateDatabase("Events", MODE_PRIVATE, null);
            database.execSQL("CREATE TABLE IF NOT EXISTS events(event VARCHAR, year INT(4))");
            database.execSQL("INSERT INTO events (event, year) VALUES ('END OF WW2', 1996)");
            database.execSQL("INSERT INTO events (event, year) VALUES ('LA', 1969)");

            Cursor cursor = database.rawQuery("SELECT * FROM events", null);
            int evenIdx = cursor.getColumnIndex("event");
            int yearIdx = cursor.getColumnIndex("year");
            cursor.moveToFirst();
            while(cursor != null){
                Log.i("event", cursor.getString(evenIdx));
                Log.i("year", cursor.getString(yearIdx));
                cursor.moveToNext();
            }*/


            WebView webView = findViewById(R.id.webView);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebViewClient(new WebViewClient());
            //webView.loadUrl("https://www.google.com");
            webView.loadData("<html><body><h1>祝睿睿</h1><p>新年快乐</p></body></html>", "text/html", "UTF-8");

            //for special error catch function we passed null
            SQLiteDatabase database = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);
            database.execSQL("CREATE TABLE IF NOT EXISTS users(name VARCHAR, age INT(3))");
            database.execSQL("INSERT INTO users (name, age) VALUES ('ROB', 30)");
            database.execSQL("INSERT INTO users (name, age) VALUES ('Tommy', 20)");
            database.execSQL("INSERT INTO users(name, age) VALUES ('Marry', 19)");
            database.execSQL("DELETE FROM users WHERE name = 'Rob'");
            database.execSQL("UPDATE users set age = 19 WHERE name = 'Rob'");

            Cursor cursor = database.rawQuery("SELECT * FROM users", null);
            int nameIndex = cursor.getColumnIndex("name");
            int ageIndex = cursor.getColumnIndex("age");

            cursor.moveToFirst();
            while(cursor != null){
                Log.i("name", cursor.getString(nameIndex));
                Log.i("age", String.valueOf(cursor.getString(ageIndex)));
                cursor.moveToNext();
             }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
