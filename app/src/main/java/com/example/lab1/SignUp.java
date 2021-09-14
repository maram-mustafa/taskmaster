package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        try {
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.configure(getApplicationContext());
            // Add this line, to include the Auth plugin.

            Log.i("taskmaster1", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("taskmaster1", "Could not initialize Amplify", error);
        }
        Button logInBtn = findViewById(R.id.sinUp);

        EditText userName = findViewById(R.id.loginUserNameTxt);
        EditText password = findViewById(R.id.loginPasswordTxt);
        EditText email = findViewById(R.id.emailTxt);
        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthSignUpOptions options = AuthSignUpOptions.builder()
                        .userAttribute(AuthUserAttributeKey.email(), email.toString())
                        .build();
                Amplify.Auth.signUp(userName.toString(), password.toString(), options,
                        result -> Log.i("AuthQuickStart", "Result: " + result.toString()),
                        error -> Log.e("AuthQuickStart", "Sign up failed", error)
                );


                Toast.makeText(getApplicationContext(), "signUp Confirmed !", Toast.LENGTH_LONG).show();
            }
        });

        Button goToLogInBtn = findViewById(R.id.loginButt);
        goToLogInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logIn = new Intent(SignUp.this, LogIn.class);
                startActivity(logIn);
            }
        });
        Button goTogoToVerificationBtn = findViewById(R.id.verificationBtn);
        goTogoToVerificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent verification = new Intent(SignUp.this, ConfirmSignUp.class);
                startActivity(verification);
            }
        });


    }
}