package com.example.bubble;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.bubble.entities.KeyAnswer;
import com.example.bubble.entities.Sets;
import com.example.bubble.entities.Student;
import com.example.bubble.entities.StudentScore;

@Database(entities = {
        Sets.class,
        KeyAnswer.class,
        Student.class,
        StudentScore.class
},version = 1)
public abstract class SetDatabase extends RoomDatabase {
    public abstract SetsDao setsDao();
    public static SetDatabase INSTANCE;
    public static SetDatabase getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    SetDatabase.class,"Bubble").allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
