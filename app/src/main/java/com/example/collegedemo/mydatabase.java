package com.example.collegedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class mydatabase extends SQLiteOpenHelper {
    public static final String COST_TABLE = "COST_TABLE";
    public static final String COLUMN_COST_NAME = "COST_NAME";
    public static final String COLUMN_COST_AGE = "COST_AGE";
    public static final String COLUMN_ACTIVE_COST = "ACTIVE_COST";
    public static final String COLUMN_ID = "ID";
    private Object SQLiteDatabase;
    private android.database.sqlite.SQLiteDatabase db;

    public mydatabase(@Nullable Context context) {
        super(context, "student.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + COST_TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_COST_NAME + " TEXT," + COLUMN_COST_AGE + " INT, " + COLUMN_ACTIVE_COST + " BOOL)";


        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public boolean addOne(customerls customerls){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_COST_NAME, customerls.getName());
        cv.put(COLUMN_COST_AGE, customerls.getAge());
        cv.put(COLUMN_ACTIVE_COST, customerls.isActive());

        long insert = db.insert(COST_TABLE, null, cv);
        if (insert == -1){return false;}
        else {
            return true;
        }

    }



    public List<customerls> getEveryone(){
        List<customerls> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + COST_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()){
            do {
                int ID = cursor.getInt(0);
                String costName = cursor.getString(1);
                int costAge = cursor.getInt(2);
                boolean costActive = cursor.getInt(3) == 1 ? true: false;

                customerls newCost = new customerls(ID, costName,costAge,costActive);
                returnList.add(newCost);


            }while (cursor.moveToNext());

        }else {

        }
        cursor.close();
        db.close();

        return returnList;
    }


}
