package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        Button usernameSave = findViewById(R.id.usernameSave);
        usernameSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Settings.this);
                SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();

                EditText usernameField = findViewById(R.id.usernameInput);
                String userName = usernameField.getText().toString();

                sharedPreferencesEditor.putString("userName" , userName);
                sharedPreferencesEditor.apply();

            }
        });



    }
    }
