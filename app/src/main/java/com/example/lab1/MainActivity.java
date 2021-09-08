package com.example.lab1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /////////////////////////////////////////// for api
        try {
            // Add these lines to add the AWSApiPlugin plugins
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());

            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }
        ////////////////////////////////////////// lab 29////////////////////////////////////////////

//        appDatabase  =  Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "taskdatabase").allowMainThreadQueries()
//                .build();
//
//        TaskDao taskDao = appDatabase.taskDao();
//        List<Task> tasks = taskDao.getAll();
//
//        RecyclerView allTasksRecyclerView = findViewById(R.id.TaskListRecycler);  //get Recycler
//        allTasksRecyclerView.setLayoutManager(new LinearLayoutManager(this)); // set layout manager
//
//        allTasksRecyclerView.setAdapter(new TaskAdapter(tasks));
        //////////////////////////////////////////// lab 28///////////////////////////////////////////

////         create data
//        ArrayList <Task> allTasks = new ArrayList<Task>();
//        allTasks.add(new Task("lab26" , "done with lab 26" , "complete"));
//        allTasks.add(new Task("lab27" , "finished","assigned"));
//        allTasks.add(new Task("lab28" , "working on it","in progress"));
//        allTasks.add(new Task("lab29" , "not open yet" , "new"));
//
//
//
//        RecyclerView allTasksRecyclerView = findViewById(R.id.TaskListRecycler);  //get Recycler
//        allTasksRecyclerView.setLayoutManager(new LinearLayoutManager(this)); // set layout manager
//
//        allTasksRecyclerView.setAdapter(new TaskAdapter(allTasks));

        /////////////////////////////////////////////// lab 26 ////////////////////////////////////

        // get two buttons
        Button addTasksButton = findViewById(R.id.button3);
        Button allTasksButton = findViewById(R.id.button4);

        // Add click listener
        addTasksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                System.out.println("clicked");
                // MOVE TO ANOTHER ACTIVITY
                Intent goToAddTask = new Intent(MainActivity.this, AddTasks.class);
                startActivity(goToAddTask);

            }
        });

        // Add click listener
        allTasksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // MOVE TO ANOTHER ACTIVITY
                Intent goToAllTasks = new Intent(MainActivity.this, AllTasks.class);
                startActivity(goToAllTasks);

            }
        });

        //////////////////////////////////////////////lab 27 ///////////////////////////////////////

        // for Task detail (before click on any task)
        Button taskDetail = findViewById(R.id.taskDetail);

        // for three tasks (after click)
        Button taskA = findViewById(R.id.buttonTaskA);
        Button taskB = findViewById(R.id.buttonTaskB);
        Button taskC = findViewById(R.id.buttonTaskC);


        // listener for task detail
        taskDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToTaskDetail = new Intent(MainActivity.this, TaskDetail.class);
                startActivity(goToTaskDetail);
            }
        });


        // listener for task A
        taskA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titleA = taskA.getText().toString();
                Intent goToTaskDetail = new Intent(MainActivity.this, TaskDetail.class);
                goToTaskDetail.putExtra("titleA", titleA);
                startActivity(goToTaskDetail);
            }
        });

        // listener for task B
        taskB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titleB = taskB.getText().toString();
                Intent goToTaskDetail = new Intent(MainActivity.this, TaskDetail.class);
                goToTaskDetail.putExtra("titleB", titleB);
                startActivity(goToTaskDetail);
            }
        });

        // listener for task C
        taskC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titleC = taskC.getText().toString();
                Intent goToTaskDetail = new Intent(MainActivity.this, TaskDetail.class);
                goToTaskDetail.putExtra("titleC", titleC);
                startActivity(goToTaskDetail);
            }
        });


        ///////////////////// for settings page
        Button settingsPage = findViewById(R.id.settingsPage);

        settingsPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToSittengs = new Intent(MainActivity.this, Settings.class);
                startActivity(goToSittengs);
            }
        });
        /////////////////////////////// lab 32
        RecyclerView taskRecyclerView = findViewById(R.id.TaskListRecycler);

        Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback(){
            @Override
            public boolean handleMessage(@NonNull Message message) {
                taskRecyclerView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });

        List<Task> taskList = new ArrayList<Task>();
        Amplify.API.query(
                ModelQuery.list(com.amplifyframework.datastore.generated.model.Task.class),
                response -> {
                    for (Task task : response.getData()) {
                        taskList.add(task);
                    }
                    handler.sendEmptyMessage(1);
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskRecyclerView.setAdapter(new TaskAdapter(taskList));



    }

    ////////////////////// for settings ->  sharedPreferences (username)

    @Override
    protected void onResume() {
        super.onResume();

        String userNameTasks = "Task";

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String userName = sharedPreferences.getString("userName", "username");

        TextView userNameview = findViewById(R.id.userNameTasks);
        userNameview.setText(userName + " " + userNameTasks);
    }
}
