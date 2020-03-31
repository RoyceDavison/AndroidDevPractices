package com.example.download;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

//记得去Manifest 更新--> ask for permission
public class MainActivity extends AppCompatActivity {
    ImageView imageView;

    /*
    //1th: type of our data we want to send to our download task
    //2nd: the name of method we may or may not to use show the progress of this task
    //3rd: type of data return by our task
    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String res = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try{
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in  = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read(); //track the location of html context we are on

                while(data != -1){
                    char current = (char) data;
                    res += current;
                    data = reader.read();
                }

                return res;

            }
            catch(Exception e){
                e.printStackTrace();
                return "Failed";
            }
        }
    } */

    public class ImageDownload extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... urls) {
            try{
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            }
            catch (MalformedURLException e){
                e.printStackTrace();
            }
            catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }
    }

    public void downloadImage(View view){
        ImageDownload task = new ImageDownload();
        Bitmap myImage;
        try{
            myImage = task.execute("https://upload.wikimedia.org/wikipedia/commons/thumb/a/a1/Wiki-quality-dimensions.png/220px-Wiki-quality-dimensions.png").get();
            imageView.setImageBitmap(myImage);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        Log.i("Image", "Button being pressed!");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        DownloadTask task = new DownloadTask();
        String res = "";

        try {
            res = task.execute("https://www.google.com/").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.i("Content of url: ", res);*/


        imageView = (ImageView) findViewById(R.id.imageView);
    }
}
