package com.example.bubble.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class KeyAnswer {
    @PrimaryKey(autoGenerate = true)
    public int answerkey_id;
    public String sets_name;
}
