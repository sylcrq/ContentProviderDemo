package com.example.syl.contentproviderdemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Shen YunLong on 2016/09/19.
 */
public class BookDBHelper extends SQLiteOpenHelper {

    public static final String TAG = BookDBHelper.class.getSimpleName();

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Book.db";


    public BookDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Thread " + Thread.currentThread().getId() + " # onCreate");
        // 建表
        db.execSQL(DBSQL.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "Thread " + Thread.currentThread().getId() + " # onUpgrade" +
                oldVersion + " -> " + newVersion);
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
    }
}
