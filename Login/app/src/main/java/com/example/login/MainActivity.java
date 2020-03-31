package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

//make textview interactively
public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {
    private boolean signUpModeActive = true;
    private TextView signupModeTextView;
    private EditText passwordText;
    private EditText usernameText;

    public void showUserList(){
        Intent intent = new Intent(getApplicationContext(), UserListActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        //press enter key --> login
        //event.getAction() == event.ACTION_DOWN --> force the form to only submit once when we have enter key press down
        if(keyCode == event.KEYCODE_ENTER && event.getAction() == event.ACTION_DOWN){
            signUp(v);
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Instagram");
        setContentView(R.layout.activity_main);
        signupModeTextView = (TextView) findViewById(R.id.signupModeTextView);
        signupModeTextView.setOnClickListener(this);

        ConstraintLayout background = findViewById(R.id.background);
        ImageView logoImageView = findViewById(R.id.logoImageView);

        background.setOnClickListener(this);
        logoImageView.setOnClickListener(this);

        if(ParseUser.getCurrentUser() != null){
            showUserList();
        }
        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.signupModeTextView) {
            Log.i("AppInfo", "Change Signup Mode.");
            Button signupButton = findViewById(R.id.signupButton);
            passwordText = findViewById(R.id.passwordText);
            passwordText.setOnKeyListener(this);
            if(signUpModeActive){
                signUpModeActive = false;
                signupButton.setText("Login");
                signupModeTextView.setText("Or, Signup");
            } else{
                signUpModeActive = true;
                signupButton.setText("Signup");
                signupModeTextView.setText("Or, Login");
            }
        } else if (v.getId() == R.id.background || v.getId() == R.id.logoImageView){
            //hide the keyboard
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
            //inputMethodManager.hideSoftInputFromWindow(getCurrentFocus(), v.getWindowToken(), 0);
        }
    }

    public void signUp(View view){
        usernameText = findViewById(R.id.usernameText);
        passwordText =findViewById(R.id.passwordText);
        if(usernameText.getText().toString().isEmpty() || passwordText.getText().toString().isEmpty()){
            Toast.makeText(this, "A username and password are required.", Toast.LENGTH_SHORT).show();
        } else {
            if(signUpModeActive){
                ParseUser user = new ParseUser();
                user.setUsername(usernameText.getText().toString());
                user.setPassword(passwordText.getText().toString());
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e == null){
                            Log.i("Signup", "Signup Successful");
                            showUserList();

                        } else{
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else{
                ParseUser.logInInBackground(usernameText.getText().toString(), passwordText.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(user != null){
                            Log.i("Signup", "Login Successful");
                            showUserList();
                        } else{
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        }
    }
}
