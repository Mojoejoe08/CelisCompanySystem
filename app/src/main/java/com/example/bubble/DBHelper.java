package com.example.bubble;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper( Context context) {
        super(context,"Bubble.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("create Table questionnaireTbl(q_id Integer primary key autoincrement," +
            "question Text, choice_A Text, choice_B Text, choice_C Text, choice_D Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists questionnaireTbl");
    }
    public boolean insertData(String question, String choice_A, String choice_B, String choice_C,String choice_D){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("question",question);
        contentValues.put("choice_A",choice_A);
        contentValues.put("choice_B",choice_B);
        contentValues.put("choice_C",choice_C);
        contentValues.put("choice_D",choice_D);
        long result = db.insert("questionnaireTbl", null,contentValues);
        if (result == -1){
            return false;
        }else{
            return true;
        }
    }
    public boolean updateData(String question, String choice_A, String choice_B, String choice_C,String choice_D){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("question",question);
        contentValues.put("choice_A",choice_A);
        contentValues.put("choice_B",choice_B);
        contentValues.put("choice_C",choice_C);
        contentValues.put("choice_D",choice_D);
        Cursor cursor = db.rawQuery("Select * from questionnaireTbl where question=?",new String[]{question});
        if (cursor.getCount()>0) {
            long result = db.update("questionnaireTbl", contentValues, "question=?", new String[]{question});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else {
            return false;
        }
    }
    public boolean deleteData(String question){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from questionnaireTbl where question=?",new String[]{question});
        if (cursor.getCount()>0) {
            long result = db.delete("questionnaireTbl", "question=?", new String[]{question});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else {
            return false;
        }
    }
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from questionnaireTbl",null);
        return cursor;
    }
}
