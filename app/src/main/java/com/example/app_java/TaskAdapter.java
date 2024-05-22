package com.example.app_java;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Random;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private List<Task> taskList;
    public Task getItem(int position) {
        return taskList.get(position);
    }
    private OnItemClickListener listener;

    public TaskAdapter(List<Task> taskList) {
        this.taskList = taskList;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.bind(task);
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public void addTask(Task task) {
        taskList.add(task);
        notifyItemInserted(taskList.size() - 1);
    }

    public void updateTask(int position, Task task) {
        taskList.set(position, task);
        notifyItemChanged(position);
    }

    public void removeTask(int position) {
        taskList.remove(position);
        notifyItemRemoved(position);
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        public TextView priorityTextView, titleTextView, descriptionTextView, assignedUserAndDatesTextView, statusTextView,initialsTextView;

        public TaskViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            priorityTextView = itemView.findViewById(R.id.text_priority);
            titleTextView = itemView.findViewById(R.id.text_title);
            descriptionTextView = itemView.findViewById(R.id.text_description);
            assignedUserAndDatesTextView = itemView.findViewById(R.id.text_creation_date);
            statusTextView = itemView.findViewById(R.id.text_status);
            initialsTextView = itemView.findViewById(R.id.text_initials);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }

        public void bind(@NonNull Task task) {
            titleTextView.setText(task.getTitle());
            descriptionTextView.setText(task.getDescription());
            assignedUserAndDatesTextView.setText(task.getAssignedUser() + " | Creation: " + task.getCreationDate() + " | Due: " + task.getDueDate());
            statusTextView.setText(task.getStatus());

            // Set text for priority
            priorityTextView.setText(task.getPriority());

            // Set text color for priority
            String priority = task.getPriority();
            int color;
            switch (priority) {
                case "High":
                    color = Color.RED;
                    break;
                case "Medium":
                    color = Color.parseColor("#FFA500"); // Orange color
                    break;
                case "Low":
                    color = Color.YELLOW;
                    break;
                default:
                    color = Color.BLACK; // Default color
                    break;
            }
            priorityTextView.setTextColor(color);

            // Set initials for assigned user
            initialsTextView.setText(getInitials(task.getAssignedUser()));
            initialsTextView.setBackgroundColor(getRandomColor());
        }

        private String getInitials(String name) {
            if (name == null || name.isEmpty()) {
                return "";
            }
            String[] words = name.split(" ");
            StringBuilder initials = new StringBuilder();
            for (String word : words) {
                if (!word.isEmpty()) {
                    initials.append(word.charAt(0));
                }
            }
            return initials.toString().toUpperCase();
        }

        private int getRandomColor() {
            Random random = new Random();
            return Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
        }





    }
}
