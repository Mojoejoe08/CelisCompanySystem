package com.example.bubble.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Student {
    @NonNull
    @PrimaryKey(autoGenerate = false)
    public String stud_name;
    public String sets_name;
}
