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

public class questionnairePreview extends AppCompatActivity {
    ArrayList<Item> que;
    private RecyclerView previewRecycleView;
    private Button questionnairePBackBtn;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire_preview);
        Bundle bundleObject = getIntent().getExtras();
        db = new DBHelper(this);
        que = (ArrayList<Item>) bundleObject.getSerializable("questions");
        previewRecycleView = findViewById(R.id.previewRecycleView);
        item_RecyclerView_Adapter adapter = new item_RecyclerView_Adapter(this);
        adapter.setItems(que);
        previewRecycleView.setAdapter(adapter);
        previewRecycleView.setLayoutManager(new LinearLayoutManager(this));
        questionnairePBackBtn= (Button) findViewById(R.id.questionnairePBackBtn);
        questionnairePBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = db.getData();
                if(res.getCount()==0){
                    Toast.makeText(questionnairePreview.this, "No Entry Exist", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()) {
                        buffer.append("1. " + res.getString(1) + "\n");
                        buffer.append("a." + res.getString(2) + "\t\t\t\t\t\t");
                        buffer.append("c." + res.getString(4) + "\n");
                        buffer.append("b." + res.getString(3) + "\t");
                        buffer.append("d." + res.getString(5) + "\n\n");
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(questionnairePreview.this);
                    builder.setCancelable(true);
                    builder.setTitle("Questionnaire");
                    builder.setMessage(buffer.toString());
                    builder.show();
                }
            }
        });

    }
}