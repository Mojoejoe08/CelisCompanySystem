package com.example.bubble;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import java.util.ArrayList;

public class answerKey extends AppCompatActivity {
    private RecyclerView answerKerRecView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_key);
        getSupportActionBar().setTitle("Answer Key");
        int num = Item.num;
        answerKerRecView = findViewById(R.id.answerKerRecView);
        ArrayList<ansKey> ansList = new ArrayList<>();
        for (int i=0;i<num;i++){
            ansList.add(new ansKey(i));
        }
        ansKey_RecyclerView_Adapter adapter = new ansKey_RecyclerView_Adapter(this);
        adapter.setItems(ansList);
        answerKerRecView.setAdapter(adapter);
        answerKerRecView.setLayoutManager(new LinearLayoutManager(this));
    }
}