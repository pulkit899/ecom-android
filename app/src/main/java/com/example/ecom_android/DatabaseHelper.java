package com.example.ecom_android;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper
{

    public static final String TABLE_NAME = "CART";
    public static final String P_NAME = "name";
    public static final String P_ID = "id";
    public static final String P_UNITS = "units";
    public static final String P_PRICE = "price";
    static final String DB_NAME = "APP_DB.DB";
    static final int DB_VERSION = 1;

    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + P_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + P_NAME+ " TEXT NOT NULL, "+ P_UNITS + " INTEGER NOT NULL, " + P_PRICE + " REAL);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}