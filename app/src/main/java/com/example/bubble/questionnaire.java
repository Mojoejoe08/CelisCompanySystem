package com.example.bubble;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class questionnaire extends AppCompatActivity {
    private Button questionnaireBackBtn;
    private Button questionnaireGenerateBtn;
    private RecyclerView conRecycleView;
    public DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);
        getSupportActionBar().setTitle("Questionnaire");
        int num = Item.num;

        conRecycleView = findViewById(R.id.conRecycleView);
        ArrayList<Item> items = new ArrayList<>();

        for (int i=0;i<num;i++){
            items.add(new Item(i+1,"Question "+(i+1),"","","",""));
        }
        item_RecyclerView_Adapter adapter = new item_RecyclerView_Adapter(this);
        adapter.setItems(items);
        conRecycleView.setAdapter(adapter);
        conRecycleView.setLayoutManager(new LinearLayoutManager(this));

        db = new DBHelper(this);
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
                for (int i=0;i<num;i++){
                    db.insertData(items.get(i).getQuestion(),
                            items.get(i).getA(),
                            items.get(i).getB(),
                            items.get(i).getC(),
                            items.get(i).getD()
                    );
                }
                Intent intent = new Intent(questionnaire.this, questionnairePreview.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("questions", items);
                intent.putExtras(bundle);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }
}