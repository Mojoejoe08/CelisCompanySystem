package com.example.bubble;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class questionnaire extends AppCompatActivity {
    private Button questionnaireBackBtn;
    private Button questionnaireGenerateBtn;
    private RecyclerView conRecycleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);
        getSupportActionBar().setTitle("Questionnaire");
        int num = Item.num;

        questionnaireBackBtn= (Button) findViewById(R.id.questionnaireBackBtn);
        questionnaireBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(questionnaire.this,createExam.class));
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });
        questionnaireGenerateBtn= (Button) findViewById(R.id.questionnaireGenerateBtn);
        questionnaireGenerateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(questionnaire.this,questionnaire.class));
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        });
        conRecycleView = findViewById(R.id.conRecycleView);
        ArrayList<Item> items = new ArrayList<>();
        for (int i=0;i<num;i++){
            items.add(new Item("Question "+(i+1),"","","",""));
        }
        item_RecyclerView_Adapter adapter = new item_RecyclerView_Adapter(this);
        adapter.setItems(items);
        conRecycleView.setAdapter(adapter);
        conRecycleView.setLayoutManager(new LinearLayoutManager(this));
    }
}