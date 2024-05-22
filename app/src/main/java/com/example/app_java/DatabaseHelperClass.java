package com.example.app_java;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelperClass extends SQLiteOpenHelper {

    //Database version
    private static final int DATABASE_VERSION = 1;
    //Database Name
    private static final String DATABASE_NAME = "employee_database";
    //Database Table name
    private static final String TABLE_NAME = "EMPLOYEE";
    //Table columns
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    private SQLiteDatabase sqLiteDatabase;
    private static final String TASK_TABLE_NAME = "TASK";
    public static final String TASK_ID = "id";
    public static final String TASK_NAME = "task_name";
    public static final String TASK_CREATED_DATE = "created_date";
    public static final String TASK_DUE_DATE = "due_date";
    public static final String TASK_STATUS = "status";
    public static final String TASK_CREATOR_ID = "creator_id";
    private static final String SHARED_TASK_TABLE_NAME = "SHARED_TASK";
    public static final String SHARED_TASK_ID = "id";
    public static final String SHARED_TASK_USER_ID = "user_id";
    public static final String SHARED_TASK_DESCRIPTION = "description";
    public static final String SHARED_TASK_START_DATE = "start_date";
    public static final String SHARED_TASK_END_DATE = "end_date";

    private static final String CREATE_SHARED_TASK_TABLE = "create table " + SHARED_TASK_TABLE_NAME + "(" + SHARED_TASK_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT," + SHARED_TASK_USER_ID + " INTEGER NOT NULL," +
            SHARED_TASK_DESCRIPTION + " TEXT NOT NULL," + SHARED_TASK_START_DATE + " TEXT NOT NULL," +
            SHARED_TASK_END_DATE + " TEXT NOT NULL);";


    private static final String CREATE_TASK_TABLE = "create table " + TASK_TABLE_NAME + "(" + TASK_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT," + TASK_NAME + " TEXT NOT NULL," +
            TASK_CREATED_DATE + " TEXT NOT NULL," + TASK_DUE_DATE + " TEXT NOT NULL," +
            TASK_STATUS + " INTEGER NOT NULL," + TASK_CREATOR_ID + " INTEGER NOT NULL);";



    //creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME +"("+ID+
            " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME + " TEXT NOT NULL,"+EMAIL+" TEXT NOT NULL);";
    //Constructor
    public DatabaseHelperClass (Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TASK_TABLE);
        db.execSQL(CREATE_SHARED_TASK_TABLE);
    }
    public void addSharedTask(int userId, String description, String startDate, String endDate){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelperClass.SHARED_TASK_USER_ID, userId);
        contentValues.put(DatabaseHelperClass.SHARED_TASK_DESCRIPTION, description);
        contentValues.put(DatabaseHelperClass.SHARED_TASK_START_DATE, startDate);
        contentValues.put(DatabaseHelperClass.SHARED_TASK_END_DATE, endDate);
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(DatabaseHelperClass.SHARED_TASK_TABLE_NAME, null,contentValues);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }



    public void deleteTask(int id){
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TASK_TABLE_NAME, TASK_ID + " = ? ", new String[]
                {String.valueOf(id)});
    }

    //Add Employee Data
    public void addEmployee(EmployeeModelClass employeeModelClass){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelperClass.NAME, employeeModelClass.getName());
        contentValues.put(DatabaseHelperClass.EMAIL, employeeModelClass.getEmail());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(DatabaseHelperClass.TABLE_NAME, null,contentValues);
    }

    public List<EmployeeModelClass> getEmployeeList(){
        String sql = "select * from " + TABLE_NAME;
        sqLiteDatabase = this.getReadableDatabase();
        List<EmployeeModelClass> storeEmployee = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                String email = cursor.getString(2);
                storeEmployee.add(new EmployeeModelClass(id,name,email));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeEmployee;
    }

    public void updateEmployee(EmployeeModelClass employeeModelClass){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelperClass.NAME,employeeModelClass.getName());
        contentValues.put(DatabaseHelperClass.EMAIL,employeeModelClass.getEmail());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.update(TABLE_NAME,contentValues,ID + " = ?" , new String[]
                {String.valueOf(employeeModelClass.getId())});
    }

    public void deleteEmployee(int id){
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, ID + " = ? ", new String[]
                {String.valueOf(id)});
    }

}