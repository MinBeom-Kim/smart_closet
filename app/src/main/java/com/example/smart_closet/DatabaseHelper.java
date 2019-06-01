package com.example.smart_closet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "closet.db", null,  1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user(email text primary key, password text, name text, profile text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
        onCreate(db);
    }

    public boolean insert(String email, String password, String name, String profile) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("name", name);
        contentValues.put("profile", profile);
        long ins = db.insert("user", null, contentValues);
        if(ins == 1) return false;
        else return true;
    }

    public Boolean chkemail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where email=?", new String[]{email});
        if(cursor.getCount()>0) return false;
        else return true;
    }

    public Boolean emailpassword(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where email=? and password=?", new String[]{email, password});
        if(cursor.getCount()>0) return true;
        else return false;
    }

    public ArrayList<String> getUserData(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where email = '" + email + "'", null);
        ArrayList<String> userData = new ArrayList<String>();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String mail = cursor.getString(cursor.getColumnIndex("email"));
                userData.add(mail);
                userData.add(name);
            }
        }
        cursor.close();
        return userData;
    }

}
