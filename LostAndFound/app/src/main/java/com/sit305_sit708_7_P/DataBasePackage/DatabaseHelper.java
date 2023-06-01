package com.sit305_sit708_7_P.DataBasePackage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sit305_sit708_7_P.DataModelPackage.DataModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mydatabase.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "mytable";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_POST_TYPE = "post_type";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_LOCATION = "location";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create your table
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_POST_TYPE + " TEXT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_PHONE + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_LOCATION + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Upgrade your table if needed
    }
    public boolean insertData(String postType, String name, String phone, String description, String date, String location) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_POST_TYPE, postType);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_PHONE, phone);
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_LOCATION, location);
        long result = db.insert(TABLE_NAME, null, values);
        db.close();
        return result != -1; // Return true if data insertion was successful, false otherwise
    }

    public List<DataModel> getAllData() {
        List<DataModel> dataList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
                String postType = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_POST_TYPE));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
                String phone = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PHONE));
                String description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION));
                String date = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE));
                String location = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LOCATION));

                DataModel data = new DataModel(id, postType, name, phone, description, date, location);
                dataList.add(data);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return dataList;
    }

    public boolean deleteData(int id) {
        SQLiteDatabase db = getWritableDatabase();
        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};
        int deletedRows = db.delete(TABLE_NAME, selection, selectionArgs);
        db.close();
        return deletedRows > 0; // Return true if at least one row was deleted, false otherwise
    }

}
