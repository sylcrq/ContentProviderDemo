package com.example.syl.contentproviderdemo;

/**
 * Created by Shen YunLong on 2016/09/19.
 */
public class DBSQL {

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + BookContract.BookEntry.TABLE_NAME + " (" +
                    BookContract.BookEntry._ID + " INTEGER PRIMARY KEY," +
                    BookContract.BookEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                    BookContract.BookEntry.COLUMN_NAME_SUBTITLE + TEXT_TYPE + " )";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + BookContract.BookEntry.TABLE_NAME;

}
