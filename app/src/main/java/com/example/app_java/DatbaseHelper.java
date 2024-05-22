package com.example.app_java;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatbaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "myapp.db";
    public static final String TB_NAME = "users";
    public static final String COL0 = "ID";
    public static final String COL1 = "fullname";
    public static final String COL2= "username";
    public static final String COL3 = "email";
    public static final String COL4 = "phone";
    public static final String COL5 = "password";
    public static final String COL6 = "gender";




    public DatbaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users (ID INTEGER PRIMARY KEY AUTOINCREMENT ,fullname TEXT,username TEXT ,email TEXT,phone TEXT,password TEXT,gender TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TB_NAME);
        onCreate(db);

    }

    public long addUser(String name,String username,String email,String phone,String password,String gender){
        SQLiteDatabase dbb=this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put("username",username);
        cv.put("password",password);
        cv.put("fullname",name);
        cv.put("email",email);
        cv.put("gender",gender);
        cv.put("phone",phone);
        long res =dbb.insert("users",null,cv);
        dbb.close();
        return res;

    }
    public boolean checkUsers(String user,String password){
        String[] columns={COL0};
        SQLiteDatabase db=this.getReadableDatabase();
        String selection = COL2 + "=?" +" and "+COL5 +"=?";
        String[] selectionArgs = {user,password};
        Cursor cursor =db.query(TB_NAME,columns,selection,selectionArgs,null,null,null);
        int count=cursor.getCount();
        cursor.close();
        db.close();
        if(count>0){
            return true;

        }else{
            return false;

        }



    }

}