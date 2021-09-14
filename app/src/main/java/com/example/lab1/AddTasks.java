package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.Team;

import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
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

                ///////////////////////////////////////////////////////////////////////////////////


                RadioGroup radioGroup = findViewById(R.id.radioGroup2);
                int radioButtonId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(radioButtonId);
                String name = radioButton.getText().toString();

                StringBuilder teamId = new StringBuilder();

                Amplify.API.query(
                        ModelQuery.list(Team.class),
                        response -> {
                            for (Team team : response.getData()) {
                                if (team.getName().equals(name)) {
                                    teamId.append(team.getId());
                                }
                            }
                            Task task = Task.builder()
                                    .teamId(teamId.toString())
                                    .title(title.getText().toString())
                                    .body(body.getText().toString())
                                    .status(state.getText().toString())
                                    .build();

                            Amplify.API.mutate(
                                    ModelMutation.create(task),
                                    response1 -> Log.i("MyAmplifyApp", "Added task with id: " + response1.getData().getId()),
                                    error -> Log.e("MyAmplifyApp", "Create failed", error)
                            );

                        },
                        error -> Log.e("MyAmplifyApp", "Query failure", error)

                );

                Toast.makeText(getApplicationContext(), "submitted!", Toast.LENGTH_SHORT).show();
                Intent goToHome = new Intent(AddTasks.this, MainActivity.class);
                startActivity(goToHome);

            }
        });
        //////////////////////////////////////// lab 37
        Button addnBtn = findViewById(R.id.addPic);

        addnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickFile();
            }
        });
    }

    String fileName ="";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        File file = new File(data.getData().getPath());
        fileName=file.getName();

        try {
            InputStream exampleInputStream = getContentResolver().openInputStream(data.getData());
            Amplify.Storage.uploadInputStream(
                    fileName,
                    exampleInputStream,
                    result -> Log.i("MyAmplifyApp", "Successfully uploaded: " + result.getKey()),
                    storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
            );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
    private void pickFile () {
        Intent selctedFile = new Intent(Intent.ACTION_GET_CONTENT);
        selctedFile.setType(("*/*"));
        selctedFile = Intent.createChooser(selctedFile, "Select File");
        startActivityForResult(selctedFile, 1234);
        Toast.makeText(getApplicationContext(), "you added a new pic", Toast.LENGTH_LONG).show();

    }

}