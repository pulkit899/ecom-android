package com.example.ecom_android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager
{
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DBManager(Context c)
    {
        context = c;
    }

    public DBManager open() throws SQLException
    {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close()
    {
        dbHelper.close();
    }

    public void insert(String name, Integer units, float price)
    {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.P_NAME, name);
        contentValue.put(DatabaseHelper.P_UNITS, units);
        contentValue.put(DatabaseHelper.P_PRICE, price);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch()
    {
        String[] columns = new String[] { DatabaseHelper.P_ID, DatabaseHelper.P_NAME, DatabaseHelper.P_UNITS, DatabaseHelper.P_PRICE };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String name, Integer units, float price)
    {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.P_NAME, name);
        contentValue.put(DatabaseHelper.P_UNITS, units);
        contentValue.put(DatabaseHelper.P_PRICE, price);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValue, DatabaseHelper.P_ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id)
    {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper.P_ID + "=" + _id, null);
    }

}
