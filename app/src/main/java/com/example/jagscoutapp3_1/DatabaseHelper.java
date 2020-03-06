//doing everything from video youtube.com/watch?v=cp2rL3AFmI&t=315s Android SQLite Database Tutorial

package com.example.jagscoutapp3_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "scoutdata.db";
    public static final String TABLE_NAME = "scouttable";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "stRobot";
    public static final String COL_3 = "inner_goals";
    public static final String COL_4 = "outer_goals";
    public static final String COL_5 = "lower_goals";
    public static final String COL_6 = "missed_goals";
    public static final String COL_7 = "penalty_count";
    public static final String COL_8 = "st";
    public static final String COL_9 = "stMatch";
    public static final String COL_10 = "stDrive";
    public static final String COL_11 = "stAuto";
    public static final String COL_12 = "stRotation";
    public static final String COL_13 = "stPosition";
    public static final String COL_14 = "stClimb";
    public static final String COL_15 = "stLevel";
    public static final String COL_16 = "stPark";
    public static final String COL_17 = "stNone";
    public static final String COL_18 = "stNotes";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, stRobot STRING, inner_goals INTEGER, outer_goals INTEGER, lower_goals INTEGER, missed_goals INTEGER, penalty_count INTEGER, st STRING, stMatch STRING, stDrive STRING, stRotation STRING, stPosition STRING, stClimb STRING, stLevel STRING, stPark STRING, stNone STRING, stNotes STRING)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }
    public boolean insertData(String stRobot, Integer inner_goals, Integer outer_goals, Integer lower_goals, Integer missed_goals, Integer penalty_count,String st,String stMatch,String stDrive,String stAuto, String stRotation, String  stPosition, String stClimb, String stLevel, String stPark, String stNone, String stNotes){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, stRobot);
        contentValues.put(COL_3, inner_goals);
        contentValues.put(COL_4, outer_goals);
        contentValues.put(COL_5, lower_goals);
        contentValues.put(COL_6, missed_goals);
        contentValues.put(COL_7, penalty_count);
        contentValues.put(COL_8, st);
        contentValues.put(COL_9, stMatch);
        contentValues.put(COL_10, stDrive);
        contentValues.put(COL_11, stAuto);
        contentValues.put(COL_12, stRotation);
        contentValues.put(COL_13, stPosition);
        contentValues.put(COL_14, stClimb);
        contentValues.put(COL_15, stLevel);
        contentValues.put(COL_16, stPark);
        contentValues.put(COL_17, stNone);
        contentValues.put(COL_18, stNotes);

        long result = db.insert(TABLE_NAME, null,contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from "+TABLE_NAME, null);
        return result;
    }
}
