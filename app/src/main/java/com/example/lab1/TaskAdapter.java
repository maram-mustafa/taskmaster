package com.example.lab1;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    List<Task> allTasks = new ArrayList<Task>();

    public TaskAdapter(List<Task> allTasks) {
        this.allTasks = allTasks;
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder{

        public Task task;
        View itemView;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView=itemView; // set View to the one will pass

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent goToDetailsPage = new Intent(view.getContext(), Detail.class);
                    goToDetailsPage.putExtra("task", task.body);
                    view.getContext().startActivity(goToDetailsPage);

                }
            });

        }
    }

    @NonNull
    @Override   // create a view that will present task in UI
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create new view out of fragment
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task_item,parent,false);
        // create viewHolder thats wrap that view
        TaskViewHolder taskViewHolder = new TaskViewHolder(view);
        return taskViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        // to bind the data that i have in that position inside list that the holder passing
        holder.task=allTasks.get(position);

        TextView title = holder.itemView.findViewById(R.id.taskTitleInFragment);
        TextView body = holder.itemView.findViewById(R.id.taskBodyInFragment);
        TextView state = holder.itemView.findViewById(R.id.taskStateInFragment);

        title.setText(holder.task.title);
        body.setText(holder.task.body);
        state.setText(holder.task.state);



    }

    @Override
    public int getItemCount() { // RecyclerView will use it to loop over the items and calls fragment to cover the size of the list
        return allTasks.size() ;
    }

}



///////////// NOTES:
// 1. create class
//2.  every adapter should have a list of data that will be use to bind with viewHolder
// 3.  create the viewHodler class -> inner class that (static -> create viewHolder without create instance of taskAdapter )
// 4. model object
//5. view object
// - @nonNull -> it can't be a null
//6. extend RecyclerVBiew.Adapter
//7. Oncreate-> when ever create a new viewHolder then this viewHolder wraps view
// so 1) create view thats wrap to have ui element
// fragment is responseple of creating the views