package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class UserFeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_feed);
        Log.i("info", "UserFeedActivity");
        Intent intent = getIntent();
        String activeUsername = intent.getStringExtra("username");
        setTitle(activeUsername +"'s Feed");
        final LinearLayout linearLayout = findViewById(R.id.linearLayout);

        //get all the image posted by the user
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Image");
        query.whereEqualTo("username", activeUsername);
        query.orderByDescending("createdAt");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e == null){
                    if(objects.size() > 0){
                        for(ParseObject obj: objects){
                            ParseFile file = (ParseFile) obj.get("image");
                            //download the image file
                            file.getDataInBackground(new GetDataCallback() {
                                @Override
                                public void done(byte[] data, ParseException e) {
                                    if(e == null && data != null){
                                        //start at offset_0 and keep going to the end of the data --> data.length
                                        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                                        ImageView imageView = new ImageView(getApplicationContext());
                                        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                                                ViewGroup.LayoutParams.MATCH_PARENT,
                                                ViewGroup.LayoutParams.WRAP_CONTENT
                                        ));
                                        //imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.instagramlogo));
                                        imageView.setImageBitmap(bitmap);
                                        linearLayout.addView(imageView);
                                    }
                                    Log.i("info", "getDataInBackground");
                                }
                            });
                        }
                    }
                } else {
                    Toast.makeText(UserFeedActivity.this, "Error " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
