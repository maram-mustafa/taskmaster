package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class TaskDetail extends AppCompatActivity {

    TextView taskTitleA;
    TextView taskTitleB;
    TextView taskTitleC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        taskTitleA = (TextView)findViewById(R.id.taskTitleA);
        Intent intentA = getIntent();
        String titleA = intentA.getStringExtra("titleA");
        taskTitleA.setText(titleA);



        taskTitleB = (TextView)findViewById(R.id.taskTitleB);
        Intent intentB = getIntent();
        String titleB = intentB.getStringExtra("titleB");
        taskTitleB.setText(titleB);



        taskTitleC = (TextView)findViewById(R.id.taskTitleC);
        Intent intentC = getIntent();
        String titleC = intentC.getStringExtra("titleC");
        taskTitleC.setText(titleC);


    }
}