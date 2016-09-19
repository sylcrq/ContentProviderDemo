package com.example.syl.contentproviderdemo;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Shen YunLong on 2016/09/18.
 */
public class BookProvider extends ContentProvider {

    public static final String TAG = BookProvider.class.getSimpleName();

    public static final String AUTHORITY = "com.example.syl.contentproviderdemo.provider";
    public static final String CONTENT_URI = "content://" + AUTHORITY + "/book";

    private BookDBHelper mDbHelper;
    private SQLiteDatabase mDb;
    private Context mContext;

    @Override
    public boolean onCreate() {
        Log.d(TAG, "Thread " + Thread.currentThread().getId() + " # onCreate");

        mDbHelper = new BookDBHelper(getContext());
        mDb = mDbHelper.getWritableDatabase();
        mContext = getContext();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Log.d(TAG, "Thread " + Thread.currentThread().getId() + " # query");

//        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
//        String[] projection = {
//                BookContract.BookEntry._ID,
//                BookContract.BookEntry.COLUMN_NAME_TITLE,
//                BookContract.BookEntry.COLUMN_NAME_SUBTITLE
//        };

        // Filter results WHERE "title" = 'My Title'
//        String selection = BookContract.BookEntry.COLUMN_NAME_TITLE + " = ?";
//        String[] selectionArgs = {"My Title"};

        // How you want the results sorted in the resulting Cursor
//        String sortOrder =
//                BookContract.BookEntry.COLUMN_NAME_SUBTITLE + " DESC";

        return mDb.query(BookContract.BookEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.d(TAG, "Thread " + Thread.currentThread().getId() + " # insert");

        // Gets the data repository in write mode
//        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
//        ContentValues values = new ContentValues();
//        values.put(BookContract.BookEntry.COLUMN_NAME_TITLE, title);
//        values.put(BookContract.BookEntry.COLUMN_NAME_SUBTITLE, subtitle);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = mDb.insert(BookContract.BookEntry.TABLE_NAME, null, values);
//        mContext.getContentResolver().notifyChange(uri, null);
        return uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.d(TAG, "Thread " + Thread.currentThread().getId() + " # delete");

//        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Define 'where' part of query.
//        String selection = BookContract.BookEntry.COLUMN_NAME_TITLE + " LIKE ?";
        // Specify arguments in placeholder order.
//        String[] selectionArgs = {"MyTitle"};

        // Issue SQL statement.
        return mDb.delete(BookContract.BookEntry.TABLE_NAME, selection, selectionArgs);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        Log.d(TAG, "Thread " + Thread.currentThread().getId() + " # update");

//        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // New value for one column
//        ContentValues values = new ContentValues();
//        values.put(BookContract.BookEntry.COLUMN_NAME_TITLE, title);

        // Which row to update, based on the title
//        String selection = BookContract.BookEntry.COLUMN_NAME_TITLE + " LIKE ?";
//        String[] selectionArgs = {"MyTitle"};

        return mDb.update(
                BookContract.BookEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }
}
