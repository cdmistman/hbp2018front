package com.coolkids.todo.getTogether;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.view.*;

import java.util.ArrayList;

/**
 * Created by colton on 2/11/18.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private ArrayList<ToDoTask> tasks;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description;

        public ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.task_title);
            description = view.findViewById(R.id.task_description);
        }
    }

    public TaskAdapter(ArrayList<ToDoTask> tasks) { this.tasks = tasks; }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tasks_list_row, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ToDoTask task = tasks.get(position);
        holder.title.setText(task.getName());
        holder.description.setText(task.getDescription());
    }

    @Override
    public int getItemCount() { return tasks.size(); }
}