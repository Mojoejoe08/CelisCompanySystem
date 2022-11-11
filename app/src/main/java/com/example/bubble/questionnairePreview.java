package com.example.bubble;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import java.util.ArrayList;

public class questionnairePreview extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire_preview);
        getSupportActionBar().setTitle("Questionnaire");
//         PYTHON INITIALLIZATION
        if (! Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }
        Python py = Python.getInstance();
        PyObject pyobj = py.getModule("func");
        

        ArrayList<Item> que;
        DBHelper db;
        Bundle bundleObject = getIntent().getExtras();
        String teacher = Item.teachername;
        String subject = Item.subjectname;
        TextView teacherTxt = (TextView) findViewById(R.id.teacherTxt);
        TextView subjectTxt = (TextView) findViewById(R.id.subjectTxt);
        teacherTxt.setText(teacher);
        subjectTxt.setText(subject);
        db = new DBHelper(this);
        que = (ArrayList<Item>) bundleObject.getSerializable("questions");
        RecyclerView previewRecycleView = findViewById(R.id.previewRecycleView);
        preview_RecyclerView_Adapted adapter = new preview_RecyclerView_Adapted(this);
        adapter.setItems(que);
        previewRecycleView.setAdapter(adapter);
        previewRecycleView.setLayoutManager(new LinearLayoutManager(this));
        Button questionnairePBackBtn = (Button) findViewById(R.id.questionnairePBackBtn);
        questionnairePBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(questionnairePreview.this,questionnaire.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });
//        Button questionnairePDownloadBtn = (Button) findViewById(R.id.questionnairePDownloadBtn);
//        questionnairePDownloadBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                try (PyObject obj = pyobj.callAttr("createPdf", subject, teacher)) {
////                }catch (Exception e){
////                    Toast.makeText(questionnairePreview.this, "cant make it", Toast.LENGTH_SHORT).show();
////                }
//            }
//        });
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button answerSheetBtn = (Button) findViewById(R.id.answerSheetBtn);
        answerSheetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(questionnairePreview.this,answersheetPreview.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        });
    }
}