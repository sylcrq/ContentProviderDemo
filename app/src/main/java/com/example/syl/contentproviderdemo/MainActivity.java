package com.example.syl.contentproviderdemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.UserDictionary;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mQuery;
    private Button mInsert;
    private Button mDelete;
    private Button mUpdate;
    private ListView mListView;
    private SimpleCursorAdapter mCursorAdapter;
    private Cursor mCursor;

    private String[] xxx = {
            BookContract.BookEntry.COLUMN_NAME_TITLE,
            BookContract.BookEntry.COLUMN_NAME_SUBTITLE
    };

    private int[] yyy = {
            R.id.title,
            R.id.subtitle
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuery = (Button) findViewById(R.id.query);
        mInsert = (Button) findViewById(R.id.insert);
        mDelete = (Button) findViewById(R.id.delete);
        mUpdate = (Button) findViewById(R.id.update);
        mListView = (ListView) findViewById(R.id.listview);

        mQuery.setOnClickListener(this);
        mInsert.setOnClickListener(this);
        mDelete.setOnClickListener(this);
        mUpdate.setOnClickListener(this);

        mCursorAdapter = new SimpleCursorAdapter(
                getApplicationContext(),
                R.layout.book_list_row,
                mCursor,
                xxx,
                yyy,
                0
        );

        mListView.setAdapter(mCursorAdapter);

//        Uri uri = insert();
//        Log.d(TAG, "" + uri);
//
//        Cursor cursor = query(null);
//        if (cursor == null) {
//            Log.e(TAG, "null");
//        } else if (cursor.getCount() < 1) {
//            Log.d(TAG, "empty");
//        } else {
//            Log.d(TAG, "");
//
//            int index = cursor.getColumnIndex(UserDictionary.Words.WORD);
//
//            while (cursor.moveToNext()) {
//                String xx = cursor.getString(index);
//                Log.d(TAG, "==> " + xx);
//            }
//        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.query:
                queryAllBooks();
                break;
            case R.id.insert:
                insertBook();
                break;
            case R.id.delete:
                deleteBook();
                break;
            case R.id.update:
                updateBook();
                break;
//            case R.id.listview:
//                break;
            default:
                break;
        }
    }

    private void queryAllBooks() {
        Toast.makeText(this, "query", Toast.LENGTH_SHORT).show();

        String[] projection = {
                BookContract.BookEntry._ID,
                BookContract.BookEntry.COLUMN_NAME_TITLE,
                BookContract.BookEntry.COLUMN_NAME_SUBTITLE
        };
//        String selection = BookContract.BookEntry.COLUMN_NAME_TITLE + " = ?";
//        String[] selectionArgs = {""};
        String selection = null;
        String[] selectionArgs = null;

        String sortOrder =
                BookContract.BookEntry.COLUMN_NAME_SUBTITLE + " DESC";

        mCursor = getContentResolver().query(
                Uri.parse(BookProvider.CONTENT_URI),
                projection,
                selection,
                selectionArgs,
                sortOrder
        );
//        mCursor.moveToFirst();
//        mCursorAdapter.notifyDataSetChanged();

        mCursorAdapter.changeCursor(mCursor);

        if (mCursor == null) {
            Log.e(BookProvider.TAG, "null");
        } else if (mCursor.getCount() < 1) {
            Log.e(BookProvider.TAG, "empty");
        } else {
            while (mCursor.moveToNext()) {
                Log.d(BookProvider.TAG, "==>" + mCursor.getString(mCursor.getColumnIndex(BookContract.BookEntry.COLUMN_NAME_TITLE)));
            }
        }
    }

    private void insertBook() {
        Toast.makeText(this, "insert", Toast.LENGTH_SHORT).show();

        ContentValues values = new ContentValues();
        values.put(BookContract.BookEntry.COLUMN_NAME_TITLE, "1024");
        values.put(BookContract.BookEntry.COLUMN_NAME_SUBTITLE, "1024-1");

        Uri uri = getContentResolver().insert(
                Uri.parse(BookProvider.CONTENT_URI),
                values
        );
        Log.d(BookProvider.TAG, "" + uri);
    }

    private void deleteBook() {
        Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show();

        getContentResolver().delete(
                Uri.parse(BookProvider.CONTENT_URI),
                null,
                null
        );
    }

    private void updateBook() {
        Toast.makeText(this, "update", Toast.LENGTH_SHORT).show();

        getContentResolver().update(
                Uri.parse(BookProvider.CONTENT_URI),
                null,
                null,
                null
        );
    }

    /**
     * 查询
     *
     * @param key
     * @return
     */
//    private Cursor query(String key) {
//
//        String[] projection = {
//                UserDictionary.Words._ID,
//                UserDictionary.Words.WORD,
//                UserDictionary.Words.LOCALE
//        };
//        String selectionClause = null;
//        String[] selectionArgs = {""};
//        String sortOrder = "";
//
//        if (TextUtils.isEmpty(key)) {
//            selectionClause = null;
//            selectionArgs = null;
//        } else {
//            selectionClause = UserDictionary.Words.WORD + " = ?";
//            selectionArgs[0] = key;
//        }
//
//        return getContentResolver().query(
//                UserDictionary.Words.CONTENT_URI,
//                projection,
//                selectionClause,
//                selectionArgs,
//                sortOrder
//        );
//    }

    /**
     * 插入
     *
     * @return
     */
//    private Uri insert() {
//        ContentValues values = new ContentValues();
//        values.put(UserDictionary.Words.APP_ID, "example.user");
//        values.put(UserDictionary.Words.LOCALE, "en_US");
//        values.put(UserDictionary.Words.WORD, "insert");
//        values.put(UserDictionary.Words.FREQUENCY, "100");
//
//        return getContentResolver().insert(
//                UserDictionary.Words.CONTENT_URI,
//                values);
//    }

    /**
     * 更新
     *
     * @return
     */
//    private int update() {
//        ContentValues values = new ContentValues();
//
//        String selectionClause = UserDictionary.Words.LOCALE + "LIKE ?";
//        String[] selectionArgs = {"en_%"};
//
//        return getContentResolver().update(
//                UserDictionary.Words.CONTENT_URI,
//                values,
//                selectionClause,
//                selectionArgs
//        );
//    }

    /**
     * 删除
     *
     * @return
     */
//    private int delete() {
//        String selectionClause = UserDictionary.Words.APP_ID + " LIKE ?";
//        String[] selectionArgs = {"user"};
//
//        return getContentResolver().delete(
//                UserDictionary.Words.CONTENT_URI,
//                selectionClause,
//                selectionArgs
//        );
//    }
}
