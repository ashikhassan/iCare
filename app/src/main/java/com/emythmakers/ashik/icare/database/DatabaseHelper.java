package com.emythmakers.ashik.icare.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    static  final String DATABASE_NAME="bitm_icare_login.db";
    static  final int DATABASE_VERSSION=1;

    static  final String TABLE_CONTACT="user_list";

    static  final String COL_ID="id";
    static  final String COL_NAME="name";
    static  final String COL_AGE="age";
    static  final String COL_HEIGHT="height";
    static  final String COL_WEIGHT="weight";

    static  final String CREATE_TABLE_CONTACT=" create table "+ TABLE_CONTACT +
            "( "+ COL_ID + " integer primary key, "+
            " "+ COL_NAME + " text, " +
            " "+ COL_AGE + " text, " +
            " "+ COL_HEIGHT + " text, " +
            COL_WEIGHT + " text );";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CONTACT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXIST"+ TABLE_CONTACT);
        onCreate(db);
    }
}
