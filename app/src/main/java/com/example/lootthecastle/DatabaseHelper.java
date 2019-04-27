package com.example.lootthecastle;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "data_base";

    private static final String KEY = "ID";
    private static final String COL1 = "data_name";
    private static final String COL2 = "value";

    public DatabaseHelper(Context c) {
        super(c, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String table = "CREATE TABLE " + TABLE_NAME + " (" + KEY + " INTEGER PRIMARY KEY, " + COL1 + " TEXT, " + COL2 + " INTEGER)";
        db.execSQL(table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addData(String data_name, int value) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, data_name);
        contentValues.put(COL2, value);

        Log.d(TAG, "addData: Adding " + data_name + " to " + TABLE_NAME + " with " + value);

        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }
    public int findValue(String data_name) {
        String query = "Select * FROM " + TABLE_NAME + " WHERE " + COL1 + " =  \"" + data_name + "\"";

        SQLiteDatabase db = this.getWritableDatabase();
        int value;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            value = cursor.getInt(2);
            cursor.close();
        } else {
            value = -1;
        }
        db.close();
        Log.d(TAG,"Value: " + value);
        return value;
    }
    public boolean deleteValue(String data_name) {

        boolean result = false;

        String query = "Select * FROM " + TABLE_NAME + " WHERE " + COL1 + " =  \"" + data_name + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        String value = "";

        if (cursor.moveToFirst()) {
            db.delete(TABLE_NAME, "ID =  + ?",
                    new String[] {String.valueOf(cursor.getString(0))});
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
    public void replace(String data_name, int newValue) {
        String query = "Select * FROM " + TABLE_NAME + " WHERE " + COL1 + " =  \"" + data_name + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues v = new ContentValues();
        v.put(COL1, data_name);
        v.put(COL2, newValue);

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            int key = cursor.getInt(0);

            v.put(KEY, key);

            db.replace(TABLE_NAME, null, v);

            cursor.close();
        }

        db.close();
    }
}