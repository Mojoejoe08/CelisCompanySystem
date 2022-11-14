package com.example.bubble;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bubble.entities.Sets;

import java.util.List;

public class checkExam extends AppCompatActivity {
    private Button newSetBtn;
    private Button checkBackBtn;
    private NewSet_RecView_Adapter newSetRecViewAdapter;
    private SetDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_exam);
        getSupportActionBar().setTitle("Check Exam");
        newSetBtn = (Button) findViewById(R.id.newSetBtn);
        newSetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(checkExam.this,newSet.class));
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        });
        checkBackBtn = (Button) findViewById(R.id.checkBackBtn);
        checkBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(checkExam.this,MainActivity.class));
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });
        initRecyclerView();
        loadSetsList();
    }

    private void initRecyclerView() {
        RecyclerView newSetRecView = (RecyclerView) findViewById(R.id.newSetRecView);
        newSetRecView.setLayoutManager(new LinearLayoutManager(this));
        newSetRecViewAdapter = new NewSet_RecView_Adapter(this);
        newSetRecView.setAdapter(newSetRecViewAdapter);
    }

    private void loadSetsList(){
        db = SetDatabase.getInstance(this.getApplicationContext());
        List<Sets> setsList = db.setsDao().getSets();
        newSetRecViewAdapter.setSetsList(setsList);
    }
}