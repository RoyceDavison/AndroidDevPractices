package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {
    private EditText cityName;
    private TextView textView;

    public class WeatherLoader extends AsyncTask<String, Void, String>{
        //doInBackground can not interact with UI at all
        //Interaction with UI should be done in the UI thread or main thread or via onPostExecute
        //onPostExecute: Update the UI
        @Override
        protected String doInBackground(String... strings) {
            String res = "";
            URL url;
            HttpURLConnection urlConnection;

            try{
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while(data != -1){
                    char current = (char) data;
                    res += current;
                    data = reader.read();
                }
                return res;
            }
            catch(Exception e){
                Toast.makeText(getApplicationContext(), "Could not find weather", Toast.LENGTH_LONG);
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject jsonObject = new JSONObject(s);
                String weather = jsonObject.getString("weather");
                JSONArray arr = new JSONArray(weather);
                String main = "";
                String description = "";
                String message = "";
                for(int i = 0; i < arr.length(); i++){
                    JSONObject jsonPart = arr.getJSONObject(i);
                    main = jsonPart.getString("main");
                    description = jsonPart.getString("description");

                    if(!main.isEmpty() && !description.isEmpty()){
                        message += main + ": " + description + "\n";
                    }

                    if(!message.isEmpty()){
                        textView.setText(message);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Could not find weather", Toast.LENGTH_LONG);
                    }

                }

                //Log.i("Weather Content", weather);

            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "Could not find weather", Toast.LENGTH_LONG);
            }

           // Log.i("Result", s);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityName = (EditText) findViewById(R.id.cityName);
        textView = (TextView) findViewById(R.id.resultTextView);
    }

    public void findWeather(View view){
        //As sson as the button is tapped, removed the keyboard which was displayed when we enter the cityName
        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        //cityName.getWindowToken() --> tell which window we want to hide
        mgr.hideSoftInputFromWindow(cityName.getWindowToken(), 0);

        WeatherLoader task = new WeatherLoader();
        //deal with the city name with space such as San Francisco, we use URLEncoder
        try{
            String encodeCityName = URLEncoder.encode(cityName.getText().toString(), "UTF-8");
            String apiKey = "da15ba0e610de7ed5fcc9f8c55036ace";
            //task.execute("http://api.openweathermap.org/data/2.5/weather?q="+ cN + "&appid=" + apiKey);
            task.execute("https://samples.openweathermap.org/data/2.5/weather?q=" + encodeCityName + "&appid=b6907d289e10d714a6e88b30761fae22");
        }
        catch(UnsupportedEncodingException e){
            Toast.makeText(getApplicationContext(), "Could not find weather", Toast.LENGTH_LONG);
        }
    }
}
