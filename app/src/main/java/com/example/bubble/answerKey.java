package com.example.bubble;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.bubble.entities.KeyAnswer;
import com.example.bubble.entities.Sets;

import java.util.ArrayList;

public class answerKey extends AppCompatActivity {
    private RecyclerView answerKerRecView;
    ArrayList<ansKey> ansList;
    Button answerKeyBackBtn;
    Button answerKeySaveBtn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_key);
        getSupportActionBar().setTitle("Answer Key");
        int num = Item.num;
        String name_set = Item.name_set;
        answerKerRecView = findViewById(R.id.answerKerRecView);
        ansList = new ArrayList<>();
        for (int i=0;i<num;i++){
            ansList.add(new ansKey(i,"NULL"));
        }
        ansKey_RecyclerView_Adapter adapter = new ansKey_RecyclerView_Adapter(this);
        adapter.setItems(ansList);
        answerKerRecView.setAdapter(adapter);
        answerKerRecView.setLayoutManager(new LinearLayoutManager(this));

        answerKeyBackBtn = (Button) findViewById(R.id.answerKeyBackBtn);
        answerKeyBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(answerKey.this,newSet.class));
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });

        answerKeySaveBtn = (Button) findViewById(R.id.answerKeySaveBtn);
        answerKeySaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num==25){
                    saveNewAnswerKey25(name_set,
                            ansList.get(0).getAns_num(), ansList.get(1).getAns_num(),
                            ansList.get(2).getAns_num(), ansList.get(3).getAns_num(),
                            ansList.get(4).getAns_num(), ansList.get(5).getAns_num(),
                            ansList.get(6).getAns_num(), ansList.get(7).getAns_num(),
                            ansList.get(8).getAns_num(), ansList.get(9).getAns_num(),
                            ansList.get(10).getAns_num(), ansList.get(11).getAns_num(),
                            ansList.get(12).getAns_num(), ansList.get(13).getAns_num(),
                            ansList.get(14).getAns_num(), ansList.get(15).getAns_num(),
                            ansList.get(16).getAns_num(), ansList.get(17).getAns_num(),
                            ansList.get(18).getAns_num(), ansList.get(19).getAns_num(),
                            ansList.get(20).getAns_num(), ansList.get(21).getAns_num(),
                            ansList.get(22).getAns_num(), ansList.get(23).getAns_num(),
                            ansList.get(24).getAns_num());
                }else if (num == 50){
                    saveNewAnswerKey50(name_set,
                            ansList.get(0).getAns_num(), ansList.get(1).getAns_num(),
                            ansList.get(2).getAns_num(), ansList.get(3).getAns_num(),
                            ansList.get(4).getAns_num(), ansList.get(5).getAns_num(),
                            ansList.get(6).getAns_num(), ansList.get(7).getAns_num(),
                            ansList.get(8).getAns_num(), ansList.get(9).getAns_num(),
                            ansList.get(10).getAns_num(), ansList.get(11).getAns_num(),
                            ansList.get(12).getAns_num(), ansList.get(13).getAns_num(),
                            ansList.get(14).getAns_num(), ansList.get(15).getAns_num(),
                            ansList.get(16).getAns_num(), ansList.get(17).getAns_num(),
                            ansList.get(18).getAns_num(), ansList.get(19).getAns_num(),
                            ansList.get(20).getAns_num(), ansList.get(21).getAns_num(),
                            ansList.get(22).getAns_num(), ansList.get(23).getAns_num(),
                            ansList.get(24).getAns_num(),
                            ansList.get(25).getAns_num(), ansList.get(26).getAns_num(),
                            ansList.get(27).getAns_num(), ansList.get(28).getAns_num(),
                            ansList.get(29).getAns_num(), ansList.get(30).getAns_num(),
                            ansList.get(31).getAns_num(), ansList.get(32).getAns_num(),
                            ansList.get(33).getAns_num(), ansList.get(34).getAns_num(),
                            ansList.get(35).getAns_num(), ansList.get(36).getAns_num(),
                            ansList.get(37).getAns_num(), ansList.get(38).getAns_num(),
                            ansList.get(39).getAns_num(), ansList.get(40).getAns_num(),
                            ansList.get(41).getAns_num(), ansList.get(42).getAns_num(),
                            ansList.get(43).getAns_num(), ansList.get(44).getAns_num(),
                            ansList.get(45).getAns_num(), ansList.get(46).getAns_num(),
                            ansList.get(47).getAns_num(), ansList.get(48).getAns_num(),
                            ansList.get(49).getAns_num());
                }else if(num == 75){
                    saveNewAnswerKey75(name_set,
                            ansList.get(0).getAns_num(), ansList.get(1).getAns_num(),
                            ansList.get(2).getAns_num(), ansList.get(3).getAns_num(),
                            ansList.get(4).getAns_num(), ansList.get(5).getAns_num(),
                            ansList.get(6).getAns_num(), ansList.get(7).getAns_num(),
                            ansList.get(8).getAns_num(), ansList.get(9).getAns_num(),
                            ansList.get(10).getAns_num(), ansList.get(11).getAns_num(),
                            ansList.get(12).getAns_num(), ansList.get(13).getAns_num(),
                            ansList.get(14).getAns_num(), ansList.get(15).getAns_num(),
                            ansList.get(16).getAns_num(), ansList.get(17).getAns_num(),
                            ansList.get(18).getAns_num(), ansList.get(19).getAns_num(),
                            ansList.get(20).getAns_num(), ansList.get(21).getAns_num(),
                            ansList.get(22).getAns_num(), ansList.get(23).getAns_num(),
                            ansList.get(24).getAns_num(),
                            ansList.get(25).getAns_num(), ansList.get(26).getAns_num(),
                            ansList.get(27).getAns_num(), ansList.get(28).getAns_num(),
                            ansList.get(29).getAns_num(), ansList.get(30).getAns_num(),
                            ansList.get(31).getAns_num(), ansList.get(32).getAns_num(),
                            ansList.get(33).getAns_num(), ansList.get(34).getAns_num(),
                            ansList.get(35).getAns_num(), ansList.get(36).getAns_num(),
                            ansList.get(37).getAns_num(), ansList.get(38).getAns_num(),
                            ansList.get(39).getAns_num(), ansList.get(40).getAns_num(),
                            ansList.get(41).getAns_num(), ansList.get(42).getAns_num(),
                            ansList.get(43).getAns_num(), ansList.get(44).getAns_num(),
                            ansList.get(45).getAns_num(), ansList.get(46).getAns_num(),
                            ansList.get(47).getAns_num(), ansList.get(48).getAns_num(),
                            ansList.get(49).getAns_num(),
                            ansList.get(50).getAns_num(), ansList.get(51).getAns_num(),
                            ansList.get(52).getAns_num(), ansList.get(53).getAns_num(),
                            ansList.get(54).getAns_num(), ansList.get(55).getAns_num(),
                            ansList.get(56).getAns_num(), ansList.get(57).getAns_num(),
                            ansList.get(58).getAns_num(), ansList.get(59).getAns_num(),
                            ansList.get(60).getAns_num(), ansList.get(61).getAns_num(),
                            ansList.get(62).getAns_num(), ansList.get(63).getAns_num(),
                            ansList.get(64).getAns_num(), ansList.get(65).getAns_num(),
                            ansList.get(66).getAns_num(), ansList.get(67).getAns_num(),
                            ansList.get(68).getAns_num(), ansList.get(69).getAns_num(),
                            ansList.get(70).getAns_num(), ansList.get(71).getAns_num(),
                            ansList.get(72).getAns_num(), ansList.get(73).getAns_num(),
                            ansList.get(74).getAns_num());
                }else{
                    Toast.makeText(answerKey.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
                startActivity(new Intent(answerKey.this,studentList.class));
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        });
    }

    private void saveNewAnswerKey25(String set_name
    ,String one,String two,String three,String four,String five,String six,String seven
    ,String eight,String nine,String ten,String eleven,String twelve,String thirteen,String fourteen
    ,String fifteen,String sixteen,String seventeen,String eighteen,String nineteen,String twenty
    ,String twenty_one,String twenty_two,String twenty_three,String twenty_four,String twenty_five){
        SetDatabase db = SetDatabase.getInstance(this.getApplicationContext());
        KeyAnswer keyAnswer = new KeyAnswer(); keyAnswer.sets_name = set_name;

        keyAnswer.one = one; keyAnswer.two = two; keyAnswer.three = three;
        keyAnswer.four = four; keyAnswer.five = five; keyAnswer.six = six;
        keyAnswer.seven = seven; keyAnswer.eight = eight; keyAnswer.nine = nine;
        keyAnswer.ten = ten; keyAnswer.eleven = eleven; keyAnswer.twelve = twelve;
        keyAnswer.thirteen = thirteen; keyAnswer.fourteen = fourteen; keyAnswer.fifteen = fifteen;
        keyAnswer.sixteen = sixteen; keyAnswer.seventeen = seventeen; keyAnswer.eighteen = eighteen;
        keyAnswer.nineteen = nineteen; keyAnswer.twenty = twenty; keyAnswer.twenty_one = twenty_one;
        keyAnswer.twenty_two = twenty_two; keyAnswer.twenty_three = twenty_three; keyAnswer.twenty_four = twenty_four;
        keyAnswer.twenty_five = twenty_five;

        db.setsDao().insertAnswerKey(keyAnswer);
        finish();
    }


    private void saveNewAnswerKey50(String set_name
            ,String one,String two,String three,String four,String five,String six,String seven
            ,String eight,String nine,String ten,String eleven,String twelve,String thirteen,String fourteen
            ,String fifteen,String sixteen,String seventeen,String eighteen,String nineteen,String twenty
            ,String twenty_one,String twenty_two,String twenty_three,String twenty_four,String twenty_five
            ,String twenty_six,String twenty_seven,String twenty_eight,String twenty_nine,String thirty
            ,String thirty_one,String thirty_two,String thirty_three,String thirty_four,String thirty_five
            ,String thirty_six,String thirty_seven,String thirty_eight,String thirty_nine,String forty
            ,String forty_one,String forty_two,String forty_three,String forty_four,String forty_five
            ,String forty_six,String forty_seven,String forty_eight,String forty_nine,String fifty){
        SetDatabase db = SetDatabase.getInstance(this.getApplicationContext());
        KeyAnswer keyAnswer = new KeyAnswer(); keyAnswer.sets_name = set_name;

        keyAnswer.one = one; keyAnswer.two = two; keyAnswer.three = three;
        keyAnswer.four = four; keyAnswer.five = five; keyAnswer.six = six;
        keyAnswer.seven = seven; keyAnswer.eight = eight; keyAnswer.nine = nine;
        keyAnswer.ten = ten; keyAnswer.eleven = eleven; keyAnswer.twelve = twelve;
        keyAnswer.thirteen = thirteen; keyAnswer.fourteen = fourteen; keyAnswer.fifteen = fifteen;
        keyAnswer.sixteen = sixteen; keyAnswer.seventeen = seventeen; keyAnswer.eighteen = eighteen;
        keyAnswer.nineteen = nineteen; keyAnswer.twenty = twenty;
        keyAnswer.twenty_one = twenty_one;keyAnswer.twenty_two = twenty_two;keyAnswer.twenty_three = twenty_three;
        keyAnswer.twenty_four = twenty_four;keyAnswer.twenty_five = twenty_five;keyAnswer.twenty_six = twenty_six;
        keyAnswer.twenty_seven = twenty_seven;keyAnswer.twenty_eight = twenty_eight;keyAnswer.twenty_nine = twenty_nine;
        keyAnswer.thirty = thirty;keyAnswer.thirty_one = thirty_one;keyAnswer.thirty_two = thirty_two;
        keyAnswer.thirty_three = thirty_three;keyAnswer.thirty_four = thirty_four;keyAnswer.thirty_five = thirty_five;
        keyAnswer.thirty_six = thirty_six;keyAnswer.thirty_seven = thirty_seven;keyAnswer.thirty_eight = thirty_eight;
        keyAnswer.thirty_nine = thirty_nine;keyAnswer.forty = forty;keyAnswer.forty_one = forty_one;
        keyAnswer.forty_two = forty_two;keyAnswer.forty_three = forty_three;keyAnswer.forty_four = forty_four;
        keyAnswer.forty_five = forty_five;keyAnswer.forty_six = forty_six;keyAnswer.forty_seven = forty_seven;
        keyAnswer.forty_eight = forty_eight;keyAnswer.forty_nine = forty_nine;keyAnswer.fifty = fifty;


        db.setsDao().insertAnswerKey(keyAnswer);
        finish();
    }

    private void saveNewAnswerKey75(String set_name
            ,String one,String two,String three,String four,String five,String six,String seven
            ,String eight,String nine,String ten,String eleven,String twelve,String thirteen,String fourteen
            ,String fifteen,String sixteen,String seventeen,String eighteen,String nineteen,String twenty
            ,String twenty_one,String twenty_two,String twenty_three,String twenty_four,String twenty_five
            ,String twenty_six,String twenty_seven,String twenty_eight,String twenty_nine,String thirty
            ,String thirty_one,String thirty_two,String thirty_three,String thirty_four,String thirty_five
            ,String thirty_six,String thirty_seven,String thirty_eight,String thirty_nine,String forty
            ,String forty_one,String forty_two,String forty_three,String forty_four,String forty_five
            ,String forty_six,String forty_seven,String forty_eight,String forty_nine,String fifty
            ,String fifty_one,String fifty_two,String fifty_three,String fifty_four,String fifty_five
            ,String fifty_six,String fifty_seven,String fifty_eight,String fifty_nine,String sixty
            ,String sixty_one,String sixty_two,String sixty_three,String sixty_four,String sixty_five
            ,String sixty_six,String sixty_seven,String sixty_eight,String sixty_nine,String seventy
            ,String seventy_one,String seventy_two,String seventy_three,String seventy_four,String seventy_five){
        SetDatabase db = SetDatabase.getInstance(this.getApplicationContext());
        KeyAnswer keyAnswer = new KeyAnswer(); keyAnswer.sets_name = set_name;

        keyAnswer.one = one; keyAnswer.two = two; keyAnswer.three = three;
        keyAnswer.four = four; keyAnswer.five = five; keyAnswer.six = six;
        keyAnswer.seven = seven; keyAnswer.eight = eight; keyAnswer.nine = nine;
        keyAnswer.ten = ten; keyAnswer.eleven = eleven; keyAnswer.twelve = twelve;
        keyAnswer.thirteen = thirteen; keyAnswer.fourteen = fourteen; keyAnswer.fifteen = fifteen;
        keyAnswer.sixteen = sixteen; keyAnswer.seventeen = seventeen; keyAnswer.eighteen = eighteen;
        keyAnswer.nineteen = nineteen; keyAnswer.twenty = twenty;keyAnswer.twenty_one = twenty_one;
        keyAnswer.twenty_two = twenty_two;keyAnswer.twenty_three = twenty_three;keyAnswer.twenty_four = twenty_four;
        keyAnswer.twenty_five = twenty_five;keyAnswer.twenty_six = twenty_six;keyAnswer.twenty_seven = twenty_seven;
        keyAnswer.twenty_eight = twenty_eight;keyAnswer.twenty_nine = twenty_nine;keyAnswer.thirty = thirty;
        keyAnswer.thirty_one = thirty_one;keyAnswer.thirty_two = thirty_two;keyAnswer.thirty_three = thirty_three;
        keyAnswer.thirty_four = thirty_four;keyAnswer.thirty_five = thirty_five;keyAnswer.thirty_six = thirty_six;
        keyAnswer.thirty_seven = thirty_seven;keyAnswer.thirty_eight = thirty_eight;keyAnswer.thirty_nine = thirty_nine;
        keyAnswer.forty = forty;keyAnswer.forty_one = forty_one;keyAnswer.forty_two = forty_two;
        keyAnswer.forty_three = forty_three;keyAnswer.forty_four = forty_four;keyAnswer.forty_five = forty_five;
        keyAnswer.forty_six = forty_six;keyAnswer.forty_seven = forty_seven;keyAnswer.forty_eight = forty_eight;
        keyAnswer.forty_nine = forty_nine;keyAnswer.fifty = fifty;keyAnswer.fifty_one = fifty_one;
        keyAnswer.fifty_two = fifty_two;keyAnswer.fifty_three = fifty_three;keyAnswer.fifty_four = fifty_four;
        keyAnswer.fifty_five = fifty_five;keyAnswer.fifty_six = fifty_six;keyAnswer.fifty_seven = fifty_seven;
        keyAnswer.fifty_eight = fifty_eight;keyAnswer.fifty_nine = fifty_nine;keyAnswer.sixty = sixty;keyAnswer.sixty_one = sixty_one;keyAnswer.sixty_two = sixty_two;
        keyAnswer.sixty_three = sixty_three;keyAnswer.sixty_four = sixty_four;keyAnswer.sixty_five = sixty_five;
        keyAnswer.sixty_six = sixty_six;keyAnswer.sixty_seven = sixty_seven;keyAnswer.sixty_eight = sixty_eight;
        keyAnswer.sixty_nine = sixty_nine;keyAnswer.seventy = seventy;keyAnswer.seventy_one = seventy_one;
        keyAnswer.seventy_two = seventy_two;keyAnswer.seventy_three = seventy_three;keyAnswer.seventy_four = seventy_four;
        keyAnswer.seventy_five = seventy_five;

        db.setsDao().insertAnswerKey(keyAnswer);
        finish();
    }
}