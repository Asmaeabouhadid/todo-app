package com.example.app_java;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelperSe extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "tasks.db";
    public static final int DATABASE_VERSION = 1;

    public DatabaseHelperSe(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_TASKS_TABLE = "CREATE TABLE " + TaskContract.TaskEntry.TABLE_NAME + " ("
                + TaskContract.TaskEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TaskContract.TaskEntry.COLUMN_TITLE + " TEXT NOT NULL, "
                + TaskContract.TaskEntry.COLUMN_DESCRIPTION + " TEXT, "
                + TaskContract.TaskEntry.COLUMN_PRIORITY + " TEXT, "
                + TaskContract.TaskEntry.COLUMN_ASSIGNED_USER + " TEXT, "
                + TaskContract.TaskEntry.COLUMN_CREATION_DATE + " TEXT, "
                + TaskContract.TaskEntry.COLUMN_DUE_DATE + " TEXT, "
                + TaskContract.TaskEntry.COLUMN_STATUS + " TEXT NOT NULL);";

        db.execSQL(SQL_CREATE_TASKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String SQL_DELETE_TASKS_TABLE = "DROP TABLE IF EXISTS " + TaskContract.TaskEntry.TABLE_NAME;
        db.execSQL(SQL_DELETE_TASKS_TABLE);
        onCreate(db);
    }
}