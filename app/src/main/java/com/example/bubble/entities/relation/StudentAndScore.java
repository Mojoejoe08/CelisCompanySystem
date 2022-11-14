package com.example.bubble.entities.relation;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.bubble.entities.KeyAnswer;
import com.example.bubble.entities.Student;
import com.example.bubble.entities.StudentScore;

import java.util.List;

public class StudentAndScore {
    @Embedded public Student student;
    @Relation(
            parentColumn = "stud_name",
            entityColumn = "stud_name"
    )
    public List<StudentScore> studentScore;
}
