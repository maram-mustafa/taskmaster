package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;


public class AddTasks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tasks);
        
        // get button
        Button addTaskButton= findViewById(R.id.buttonAddTask);
        
        // add listener
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //new instance from task model
                //
                Toast.makeText(getApplicationContext(), "submitted!", Toast.LENGTH_SHORT).show();
            }
        });
        
    }


}