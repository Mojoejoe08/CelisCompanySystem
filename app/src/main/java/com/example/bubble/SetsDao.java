package com.example.bubble;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.bubble.entities.KeyAnswer;
import com.example.bubble.entities.Sets;
import com.example.bubble.entities.Student;
import com.example.bubble.entities.StudentScore;
import com.example.bubble.entities.relation.SetWithStudents;
import com.example.bubble.entities.relation.StudentAndScore;
import com.example.bubble.entities.relation.setsAndanskey;

import java.util.List;

@Dao
public interface SetsDao {
    //Inserts
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSet(Sets... sets);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAnswerKey(KeyAnswer... keyAnswers);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStudents(Student... student);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStudentsScore(StudentScore... studentScore);

    //Delete methods
    @Delete
    void deleteSet(Sets sets);

    @Delete
    void deleteAnswerKey(KeyAnswer keysAnswer);

    @Delete
    void deleteStudents(Student student);

    @Delete
    void deleteStudentsScore(StudentScore studentScore);

    //Queries
    @Query("Select * FROM sets")
    List<Sets> getSets();


    @Transaction
    @Query("SELECT * FROM sets WHERE sets_name =:sets_name")
    List<setsAndanskey> getsetAndAnswerKeyWithSetName(String sets_name);

    @Transaction
    @Query("SELECT * FROM sets WHERE sets_name =:sets_name")
    List<SetWithStudents> getSetWithStudent(String sets_name);

    @Transaction
    @Query("SELECT * FROM student WHERE stud_name=:stud_name ")
    List<StudentAndScore> getStudentWithScore(String stud_name);
}
