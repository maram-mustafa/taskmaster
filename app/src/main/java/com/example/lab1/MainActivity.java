package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // get two buttons
        Button addTasksButton = findViewById(R.id.button3);
        Button allTasksButton = findViewById(R.id.button4);

        // Add click listener
        addTasksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                System.out.println("clicked");
                // MOVE TO ANOTHER ACTIVITY
                Intent goToAddTask= new Intent(MainActivity.this,AddTasks.class);
                startActivity(goToAddTask);

            }
        });

        // Add click listener
        allTasksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // MOVE TO ANOTHER ACTIVITY
                Intent goToAllTasks= new Intent(MainActivity.this,AllTasks.class);
                startActivity(goToAllTasks);

            }
        });

    }
}