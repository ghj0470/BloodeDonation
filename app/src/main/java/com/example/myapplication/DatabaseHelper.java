package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;

private class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        try{
            String DROP_SQL="drop table if exists"+TABLE_NAME;
            db.execSQL(DROP_SQL);
        } catch(Exception ex){
            Log.e(TAG,"Exception in DROP_SQL",ex);
        }

        String CREATE_SQL = "create table"+TABLE_NAME+"("++")";

        try{
            db.execSQL(CREATE_SQL);
        } catch(Exception ex){
            Log.e(TAG,"Exception in CREATE_SQL",ex);
        }

        try{
            db.execSQL("insert into"+TABLE_NAME+"()values('')");
        } catch(Exception ex){
            Log.e(TAG,"Exception in insert SQL",ex);
        }


    }

        public void onOpen(SQLiteDatabase db){

        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            Log.w(TAG,"Upgrading database from version"+oldVersion+"to"+newVersion+".");
        }

}
