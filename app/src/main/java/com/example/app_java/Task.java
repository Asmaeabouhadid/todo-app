package com.example.app_java;

import android.os.Parcel;
import android.os.Parcelable;

public class Task implements Parcelable {
    private int id;
    private String title;
    private String description;
    private String priority;
    private String assignedUser;
    private String creationDate;
    private String dueDate;
    private String status;
    // Constructeur de Parcelable
    protected Task(Parcel in) {
        title = in.readString();
        description = in.readString();
        // Lisez d'autres champs nécessaires
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        // Écrivez d'autres champs nécessaires
    }
    public Task(int id, String title, String description, String priority, String assignedUser, String creationDate, String dueDate, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.assignedUser = assignedUser;
        this.creationDate = creationDate;
        this.dueDate = dueDate;
        this.status = status;
    }
    public String getInitials() {
        String[] names = assignedUser.split(" ");
        StringBuilder initials = new StringBuilder();
        for (String name : names) {
            if (!name.isEmpty()) {
                initials.append(name.charAt(0));
            }
        }
        return initials.toString().toUpperCase();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(String assignedUser) {
        this.assignedUser = assignedUser;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
