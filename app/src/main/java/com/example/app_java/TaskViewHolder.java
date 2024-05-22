package com.example.app_java;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TaskViewHolder extends RecyclerView.ViewHolder {
    public TextView taskUser;
    public TextView taskTitle;
    public TextView taskDescription;
    public TextView taskStartDate;
    public TextView taskEndDate;

    public TaskViewHolder(@NonNull View itemView) {
        super(itemView);
        taskUser = itemView.findViewById(R.id.task_user);
        taskTitle = itemView.findViewById(R.id.task_title);
        taskDescription = itemView.findViewById(R.id.task_description);
        taskStartDate = itemView.findViewById(R.id.task_start_date);
        taskEndDate = itemView.findViewById(R.id.task_end_date);
    }

    public void bind(@NonNull Task task) {
        if (taskUser != null) {
            taskUser.setText(task.getAssignedUser());
        }
        if (taskTitle != null) {
            taskTitle.setText(task.getTitle());
        }
        if (taskDescription != null) {
            taskDescription.setText(task.getDescription());
        }
        if (taskStartDate != null) {
            taskStartDate.setText(task.getCreationDate());
        }
        if (taskEndDate != null) {
            taskEndDate.setText(task.getDueDate());
        }
    }

}