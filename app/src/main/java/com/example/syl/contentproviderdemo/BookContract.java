package com.example.syl.contentproviderdemo;

import android.provider.BaseColumns;

/**
 * Created by Shen YunLong on 2016/09/19.
 */
public final class BookContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private BookContract() {
    }

    /* Inner class that defines the table contents */
    public static class BookEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_SUBTITLE = "subtitle";
    }
}
