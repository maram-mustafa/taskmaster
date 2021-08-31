package com.example.lab1;

// class(model) to represent data for each task
public class Task {
    public String title;
    public String body;
    public String state;

    public Task(String title, String body, String state) {
        this.title = title;
        this.body = body;
        this.state = state;
    }
}

// away of create a partial UI can use over and over can use it inside application
// crete part ui that present each task
// fragment to present one single task in list
// each time ResiclerView add new item for the list it will create new instance from task fragment
// step three create Adapter