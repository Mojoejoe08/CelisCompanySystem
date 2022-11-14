package com.example.bubble;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bubble.entities.Sets;

import java.util.ArrayList;

public class newSet extends AppCompatActivity {
    private int itemNumTxt;
    private EditText newSetEditTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_set);
        getSupportActionBar().setTitle("New Set");

        Spinner newSetSpinner = (Spinner) findViewById(R.id.newSetSpinner);
        ArrayList<Integer> itemNum = new ArrayList<>();
        itemNum.add(25);
        itemNum.add(50);
        itemNum.add(75);
        ArrayAdapter<Integer> itemNumAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item,itemNum
        );
        newSetSpinner.setAdapter(itemNumAdapter);
        newSetSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    itemNumTxt = itemNum.get(position);
                    Item.num = itemNumTxt;
                    Toast.makeText(newSet.this, itemNumTxt, Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                 e.printStackTrace();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button newSetBackBtn = (Button) findViewById(R.id.newSetBackBtn);
        newSetBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(newSet.this,checkExam.class));
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });

        newSetEditTxt = (EditText) findViewById(R.id.newSetEditTxt);
        Button newSetNextBtn = (Button) findViewById(R.id.newSetNextBtn);
        newSetNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item.name_set = newSetEditTxt.getText().toString();
                saveNewSet(newSetEditTxt.getText().toString(),itemNumTxt);
                Toast.makeText(newSet.this, newSetEditTxt.getText().toString(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(newSet.this,answerKey.class));
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        });
    }
    private void saveNewSet(String set_name,int set_quan){
        SetDatabase db = SetDatabase.getInstance(this.getApplicationContext());
        Sets sets = new Sets();
        sets.sets_name = set_name;
        sets.sets_quan = set_quan;
        db.setsDao().insertSet(sets);
        finish();
    }
}