package com.example.bubble;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class createExam extends AppCompatActivity {
    private Spinner itemNumberSpinner;
    private EditText teacherName;
    private EditText subjectName;
    private Integer itemNumTxt;
    private String teacherNameTxt;
    private String subjectNameTxt;
    private Button createBackBtn;
    private Button createGenerateBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_exam);
        getSupportActionBar().setTitle("Create Exam");
        itemNumberSpinner = findViewById(R.id.itemNumberSpinner);
        teacherName = findViewById(R.id.teacherName);
        subjectName = findViewById(R.id.subjectName);

        ArrayList<Integer> itemNum = new ArrayList<>();
        itemNum.add(25);
        itemNum.add(50);
        itemNum.add(75);
        ArrayAdapter<Integer> itemNumAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item,itemNum
        );
        itemNumberSpinner.setAdapter(itemNumAdapter);
        itemNumberSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(createExam.this, itemNum.get(position).toString(), Toast.LENGTH_SHORT).show();
                itemNumTxt = itemNum.get(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        teacherName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){

                }else{
                    teacherNameTxt = teacherName.getText().toString();
                    Toast.makeText(createExam.this, teacherNameTxt, Toast.LENGTH_SHORT).show();
                }
            }
        });
        subjectName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                }else{
                    subjectNameTxt = subjectName.getText().toString();
                    Toast.makeText(createExam.this, subjectNameTxt, Toast.LENGTH_SHORT).show();
                }
            }
        });
        createBackBtn = (Button) findViewById(R.id.createBackBtn);
        createBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(createExam.this,MainActivity.class));
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });
    }



    public Integer getItemNumTxt() {
        return itemNumTxt;
    }

    public String getTeacherNameTxt() {
        return teacherNameTxt;
    }

    public String getSubjectNameTxt() {
        return subjectNameTxt;
    }


}