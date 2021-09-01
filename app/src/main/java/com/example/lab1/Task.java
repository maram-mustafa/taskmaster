package com.example.lab1;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// class(model) to represent data for each task
@Entity
public class Task {
    @PrimaryKey
    public int uid;
    @ColumnInfo(name = "title")
    public String title;
    @ColumnInfo(name = "body")
    public String body;
    @ColumnInfo(name = "state")
    public String state;

    public Task(String title, String body, String state) {
        this.title = title;
        this.body = body;
        this.state = state;
    }
}

////////// NOTES:
// away of create a partial UI can use over and over can use it inside application
// crete part ui that present each task
// fragment to present one single task in list
// each time ResiclerView add new item for the list it will create new instance from task fragment
// step three create Adapter