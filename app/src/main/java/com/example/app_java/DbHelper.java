package com.example.app_java;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DBname="register.db";

    public DbHelper(@Nullable Context context) {
        super(context, DBname, null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase sqliteDatabse) {
        sqliteDatabse.execSQL("create table users (username TEXT primary key , password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqliteDatabse, int i, int i1) {
        sqliteDatabse.execSQL("drop table if exists users ");
    }

    public boolean insertData(String username,String password )
    {SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        long result =myDB.insert("users",null,contentValues);
        if (result==-1)return false;
        else return true;

    }
    public boolean checkUsername(String username){
     SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor cursor = myDb.rawQuery("select * from users where username = ?",new String[]{username});
        if (cursor.getCount()>0)
            return true;
        return false;
    }
    public boolean checkUsername(String username, String password)
    {SQLiteDatabase myDB=this.getWritableDatabase();
        Cursor cursor=myDB.rawQuery("select * from users where username = ? and password =? ",new String[]{username,password});

        if (cursor.getCount()>0)
            return true;
        return false;
    }

}
