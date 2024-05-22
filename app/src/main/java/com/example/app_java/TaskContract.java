package com.example.app_java;

import android.provider.BaseColumns;

public class TaskContract {
    public static final class TaskEntry {
        public static final String TABLE_NAME = "tasks";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_PRIORITY = "priority";
        public static final String COLUMN_ASSIGNED_USER = "assigned_user";
        public static final String COLUMN_CREATION_DATE = "creation_date";
        public static final String COLUMN_DUE_DATE = "due_date";
        public static final String COLUMN_STATUS = "status";
    }
}