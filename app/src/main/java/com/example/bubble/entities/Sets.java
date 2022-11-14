package com.example.bubble.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Sets {
    @PrimaryKey(autoGenerate = true)
    public int sets_id;
    public String sets_name;
    public int sets_quan;

}
