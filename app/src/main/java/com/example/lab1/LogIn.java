package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;

public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);


        Button logInBtn = findViewById(R.id.loginButt);

        EditText userName = findViewById(R.id.loginUserNameTxt);
        EditText password = findViewById(R.id.loginPasswordTxt);

        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Amplify.Auth.signIn(
                        "abdallahAlabed1",
                        "Abdllah321",
                        result -> Log.i("AuthQuickstart", result.isSignInComplete() ? "Sign in succeeded" : "Sign in not complete"),
                        error -> Log.e("AuthQuickstart", error.toString())
                );

                Toast.makeText(getApplicationContext(), "hallelooya!", Toast.LENGTH_LONG).show();
            }
        });

        Button startBtn = findViewById(R.id.start);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent getStarted = new Intent(LogIn.this, MainActivity.class);
                startActivity(getStarted);
            }
        });
        Button goToSinUpBtn = findViewById(R.id.goToSinUp);
        goToSinUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sinUp = new Intent(LogIn.this, SignUp.class);
                startActivity(sinUp);
            }
        });

    }
}