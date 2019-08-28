package com.example.accountrecord;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "Record.db";
    private static final String TABLE_NAME = "ACCOUNTRECORD";

    public DBHelper(Context context,int Ver){
        super(context,DATABASE_NAME,null,Ver);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                "(_id INTEGER PRIMARY KEY NOT NULL," +
                "Date VARCHAR NOT NULL," +
                "Item VARCHAR NOT NULL," +
                "Price VARCHAR NOT NULL)");
    }

    public void onUpgrade(SQLiteDatabase db,int oldVer,int newVer){

    }

    public long insert(String Date,String Item,String Price){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Date",Date);
        cv.put("Item",Item);
        cv.put("Price",Price);
        return db.insert(TABLE_NAME,null,cv);
    }

    public Cursor select(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,"Date"+" DESC");
        return cursor;
    }
}
