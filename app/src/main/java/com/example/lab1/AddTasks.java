package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;

import java.util.List;


public class AddTasks extends AppCompatActivity {

//    AppDatabase appDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tasks);
        
        // get button and text data
        Button addTaskButton= findViewById(R.id.buttonAddTask);
        EditText title = findViewById(R.id.editTextTitle);
        EditText body = findViewById(R.id.editTextDescreption);
        EditText state= findViewById(R.id.editTextState);

        // add listener
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                //new instance from task model
//                Task newTasks= new Task(title.getText().toString(),body.getText().toString(),state.getText().toString());
//                //instance from database
//                appDatabase  =  Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "taskdatabase").allowMainThreadQueries()
//                        .build();
//                //instance from Dao
//                TaskDao taskDao = appDatabase.taskDao();
//                taskDao.insertAll(newTasks);
//
//                Toast.makeText(getApplicationContext(), "submitted!", Toast.LENGTH_SHORT).show();

                Task task = Task.builder()
                        .title(title.getText().toString())
                        .body(body.getText().toString())
                        .status(state.getText().toString())
                        .build();

                Amplify.API.mutate(
                        ModelMutation.create(task),
                        response -> Log.i("MyAmplifyApp", "Added task with id: " + response.getData().getId()),
                        error -> Log.e("MyAmplifyApp", "Create failed", error)
                );
                Toast.makeText(getApplicationContext(), "submitted!", Toast.LENGTH_SHORT).show();
                Intent goToHome = new Intent(AddTasks.this, MainActivity.class);
                startActivity(goToHome);




            }
        });
        
    }


}