package com.example.lab1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
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
import android.widget.Toast;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.Callback;
import com.amazonaws.mobile.client.UserStateDetails;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.pinpoint.PinpointConfiguration;
import com.amazonaws.mobileconnectors.pinpoint.PinpointManager;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.Team;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private static PinpointManager pinpointManager;

    public static PinpointManager getPinpointManager(final Context applicationContext) {
        if (pinpointManager == null) {
            final AWSConfiguration awsConfig = new AWSConfiguration(applicationContext);
            AWSMobileClient.getInstance().initialize(applicationContext, awsConfig, new Callback<UserStateDetails>() {
                @Override
                public void onResult(UserStateDetails userStateDetails) {
                    Log.i("INIT", String.valueOf(userStateDetails.getUserState()));
                }

                @Override
                public void onError(Exception e) {
                    Log.e("INIT", "Initialization error.", e);
                }
            });

            PinpointConfiguration pinpointConfig = new PinpointConfiguration(
                    applicationContext,
                    AWSMobileClient.getInstance(),
                    awsConfig);

            pinpointManager = new PinpointManager(pinpointConfig);

            FirebaseMessaging.getInstance().getToken()
                    .addOnCompleteListener(new OnCompleteListener<String>() {
                        @Override
                        public void onComplete(@NonNull com.google.android.gms.tasks.Task<String> task) {
                            if (!task.isSuccessful()) {
                                Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                                return;
                            }
                            final String token = task.getResult();
                            Log.d(TAG, "Registering push notifications token: " + token);
                            pinpointManager.getNotificationClient().registerDeviceToken(token);
                        }
                    });
        }
        return pinpointManager;
    }

    String teamName= "" ;
    StringBuilder teamId= new StringBuilder();

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
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.addPlugin(new AWSS3StoragePlugin());
            getPinpointManager(getApplicationContext());

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
        List<Task> taskList = new ArrayList<Task>();
        RecyclerView taskRecyclerView = findViewById(R.id.TaskListRecycler);
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskRecyclerView.setAdapter(new TaskAdapter(taskList));

        Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback(){
            @Override
            public boolean handleMessage(@NonNull Message message) {
                taskRecyclerView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });


        Amplify.API.query(
                ModelQuery.list(Team.class),
                response -> {
                    for (Team team : response.getData()) {
                        if (team.getName().equals(teamName)) {
                            teamId.append(team.getId());
                        }
                    }
                    Amplify.API.query(
                            ModelQuery.list(com.amplifyframework.datastore.generated.model.Task.class),
                            response1 -> {
                                for (Task task : response1.getData()) {
                                    taskList.add(task);
                                }
                                handler.sendEmptyMessage(1);
                            },
                            error -> Log.e("MyAmplifyApp", "Query failure", error)
                    );
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );
//        Toast.makeText(getApplicationContext(), "************************on create **************************", Toast.LENGTH_LONG).show();
//        System.out.println("************************on create **************************");
    }

    ////////////////////// for settings ->  sharedPreferences (username)

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "************************on resume **************************", Toast.LENGTH_LONG).show();
        System.out.println("************************on resume **************************");
        String userNameTasks = "Task";

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String userName = sharedPreferences.getString("userName", "username");
        Toast.makeText(getApplicationContext(), "************************on resume2 **************************", Toast.LENGTH_LONG).show();
        System.out.println("************************on resume2 **************************");
        TextView userNameview = findViewById(R.id.userNameTasks);
        userNameview.setText(userName + " " + userNameTasks);
        Toast.makeText(getApplicationContext(), "************************on resume3 **************************", Toast.LENGTH_LONG).show();
        System.out.println("************************on resume3 **************************");


    }
}
