package com.example.bubble.entities.relation;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.bubble.entities.Sets;
import com.example.bubble.entities.Student;

import java.util.List;

public class SetWithStudents {
    @Embedded public Sets sets;
    @Relation(
            parentColumn = "sets_name",
            entityColumn = "sets_name"
    )
    public List<Student> student;
}
