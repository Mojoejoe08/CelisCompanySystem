package com.example.bubble;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.example.bubble.entities.KeyAnswer;
import com.example.bubble.entities.Student;
import com.example.bubble.entities.StudentScore;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class studentList extends AppCompatActivity {
    private student_RecView_Adapter studentRecViewAdapter;
    private static final int PERMISSION_CODE = 1000;
    ImageButton studentListImageCaptureBtn;
    String studentName;
    Bitmap imgbitmap;
    String imageString="";
    RecyclerView studentListRecView;
    List<KeyAnswer> answerKey_List;
    PyObject pyobj;
    PyObject objList;
    Python py;
    SetDatabase db;
    ArrayList<String> newAnswerKey;
    ArrayList<String> studentAnswers;
    List numm;
    int itemNum;
    Bitmap bitmap;
    String name_set;
    int score =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        itemNum = Item.num;
        name_set = Item.name_set;
        getSupportActionBar().setTitle(name_set);
        studentListRecView = (RecyclerView) findViewById(R.id.studentListRecView);
        db = SetDatabase.getInstance(this.getApplicationContext());
        studentAnswers = new ArrayList<>();
        answerKey_List = db.setsDao().getAnswerKey(name_set);
        newAnswerKey = new ArrayList<>();
        try {
            passListToList();
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }


        studentListImageCaptureBtn = (ImageButton) findViewById(R.id.studentListImageCaptureBtn);
        studentListImageCaptureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogStudentNameInput = new AlertDialog.Builder(studentList.this);
                dialogStudentNameInput.setTitle("Name of Paper");
                final EditText nameofPaper = new EditText(studentList.this);
                nameofPaper.setGravity(Gravity.CENTER_HORIZONTAL);
                dialogStudentNameInput.setView(nameofPaper);

                dialogStudentNameInput.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        studentName = nameofPaper.getText().toString();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if(checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED){
                                String permission = Manifest.permission.CAMERA;
                                requestPermissions(new String[]{permission},PERMISSION_CODE);
                            }else{
                                openGallery();
                            }
                        }else{
                            openGallery();
                        }
                    }
                });
                dialogStudentNameInput.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                dialogStudentNameInput.show();
            }
        });
        initRecyclerViewStudent();
        loadStudentList();
    }
    private void initRecyclerViewStudent() {
        RecyclerView studentListRecView = (RecyclerView) findViewById(R.id.studentListRecView);
        studentListRecView.setLayoutManager(new LinearLayoutManager(this));
        studentRecViewAdapter = new student_RecView_Adapter(this);
        studentListRecView.setAdapter(studentRecViewAdapter);
    }

    private void loadStudentList(){
        db = SetDatabase.getInstance(this.getApplicationContext());
        List<Student> studentList = db.setsDao().getStudents(name_set);
        studentRecViewAdapter.setStudentList(studentList);
    }

    private void studentScoreSave() {
        try{
            for (int i=0;i<itemNum;i++){
                if(newAnswerKey.get(i).equals(studentAnswers.get(i))){
                    score = score + 1;
                }
            }
            StudentScore studentScore = new StudentScore();
            studentScore.stud_name = studentName;
            studentScore.set_name = name_set;
            studentScore.stud_score = score;
            db.setsDao().insertStudentsScore(studentScore);
        }catch (Exception e){
            Toast.makeText(this, "Index not similar", Toast.LENGTH_SHORT).show();
        }

    }

    private void processImage() {
        try{
            imageString = getStringImage(imgbitmap);
            py = Python.getInstance();
            pyobj = py.getModule("script");
            objList = pyobj.callAttr("imageProcess",imageString,itemNum);
//            String str = objList.toString();
//            byte data[] = android.util.Base64.decode(str,Base64.DEFAULT);
//            Bitmap bmp = BitmapFactory.decodeByteArray(data,0, data.length);
//            studentListRecView.setImageBitmap(bmp);
            try {
                numm = objList.asList();
                for (int i=0;i <numm.size();i++){
                    if (numm.get(i).equals("0")){
                        studentAnswers.add("A");
                    }else if(numm.get(i).equals("1")){
                        studentAnswers.add("B");
                    }else if(numm.get(i).equals("2")){
                        studentAnswers.add("C");
                    }else if(numm.get(i).equals("3")){
                        studentAnswers.add("D");
                    }
                }
            }catch (Exception e){
                Toast.makeText(this, "Fucking Error" , Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
        }
    }

    private void saveStudentAnswers(int itemNum) {
        // pasok sa table ni student
        if (itemNum == 25){
            studentAnswers25items(studentName, name_set
                    ,studentAnswers.get(0),studentAnswers.get(1),studentAnswers.get(2)
                    ,studentAnswers.get(3),studentAnswers.get(4),studentAnswers.get(5)
                    ,studentAnswers.get(6),studentAnswers.get(7),studentAnswers.get(8)
                    ,studentAnswers.get(9),studentAnswers.get(10),studentAnswers.get(11)
                    ,studentAnswers.get(12),studentAnswers.get(13),studentAnswers.get(14)
                    ,studentAnswers.get(15),studentAnswers.get(16),studentAnswers.get(17)
                    ,studentAnswers.get(18),studentAnswers.get(19),studentAnswers.get(20)
                    ,studentAnswers.get(21),studentAnswers.get(22),studentAnswers.get(23),
                    studentAnswers.get(24)
            );
        }if(itemNum == 50){
            studentAnswers50items(studentName, name_set
                    ,studentAnswers.get(0),studentAnswers.get(1)
                    ,studentAnswers.get(2),studentAnswers.get(3),studentAnswers.get(4)
                    ,studentAnswers.get(5),studentAnswers.get(6),studentAnswers.get(7)
                    ,studentAnswers.get(8),studentAnswers.get(9),studentAnswers.get(10)
                    ,studentAnswers.get(11),studentAnswers.get(12),studentAnswers.get(13)
                    ,studentAnswers.get(14),studentAnswers.get(15),studentAnswers.get(16)
                    ,studentAnswers.get(17),studentAnswers.get(18),studentAnswers.get(19)
                    ,studentAnswers.get(20),studentAnswers.get(21),studentAnswers.get(22)
                    ,studentAnswers.get(23),studentAnswers.get(24),studentAnswers.get(25)
                    ,studentAnswers.get(26),studentAnswers.get(27),studentAnswers.get(28)
                    ,studentAnswers.get(29),studentAnswers.get(30),studentAnswers.get(31)
                    ,studentAnswers.get(32),studentAnswers.get(33),studentAnswers.get(34)
                    ,studentAnswers.get(35),studentAnswers.get(36),studentAnswers.get(37)
                    ,studentAnswers.get(38),studentAnswers.get(39),studentAnswers.get(40)
                    ,studentAnswers.get(41),studentAnswers.get(42),studentAnswers.get(43)
                    ,studentAnswers.get(44),studentAnswers.get(45),studentAnswers.get(46)
                    ,studentAnswers.get(47),studentAnswers.get(48),studentAnswers.get(49));
        }if(itemNum == 75){
            studentAnswers75items(studentName, name_set
                    ,studentAnswers.get(0),studentAnswers.get(1),studentAnswers.get(2)
                    ,studentAnswers.get(3),studentAnswers.get(4),studentAnswers.get(5)
                    ,studentAnswers.get(6),studentAnswers.get(7),studentAnswers.get(8)
                    ,studentAnswers.get(9),studentAnswers.get(10),studentAnswers.get(11)
                    ,studentAnswers.get(12),studentAnswers.get(13),studentAnswers.get(14)
                    ,studentAnswers.get(15),studentAnswers.get(16),studentAnswers.get(17)
                    ,studentAnswers.get(18),studentAnswers.get(19),studentAnswers.get(20)
                    ,studentAnswers.get(21),studentAnswers.get(22),studentAnswers.get(23)
                    ,studentAnswers.get(24),studentAnswers.get(25),studentAnswers.get(26)
                    ,studentAnswers.get(27),studentAnswers.get(28),studentAnswers.get(29)
                    ,studentAnswers.get(30),studentAnswers.get(31),studentAnswers.get(32)
                    ,studentAnswers.get(33),studentAnswers.get(34),studentAnswers.get(35)
                    ,studentAnswers.get(36),studentAnswers.get(37),studentAnswers.get(38)
                    ,studentAnswers.get(39),studentAnswers.get(40),studentAnswers.get(41)
                    ,studentAnswers.get(42),studentAnswers.get(43),studentAnswers.get(44)
                    ,studentAnswers.get(45),studentAnswers.get(46),studentAnswers.get(47)
                    ,studentAnswers.get(48),studentAnswers.get(49),studentAnswers.get(50)
                    ,studentAnswers.get(51),studentAnswers.get(52),studentAnswers.get(53)
                    ,studentAnswers.get(54),studentAnswers.get(55),studentAnswers.get(56)
                    ,studentAnswers.get(57),studentAnswers.get(58),studentAnswers.get(59)
                    ,studentAnswers.get(60),studentAnswers.get(61),studentAnswers.get(62)
                    ,studentAnswers.get(63),studentAnswers.get(64),studentAnswers.get(65)
                    ,studentAnswers.get(66),studentAnswers.get(67),studentAnswers.get(68)
                    ,studentAnswers.get(69),studentAnswers.get(70),studentAnswers.get(71)
                    ,studentAnswers.get(72),studentAnswers.get(73),studentAnswers.get(74));
        }else{
        }
    }


    private String getStringImage(Bitmap imgbitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        imgbitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = android.util.Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void openGallery() {
        Intent iGallery = new Intent(Intent.ACTION_PICK);
        iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(iGallery, PERMISSION_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PERMISSION_CODE){
            try {
                imgbitmap =MediaStore.Images.Media.getBitmap(this.getContentResolver(),data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
//            studentListRecView.setImageURI(data.getData());
            processImage();
            try{
                saveStudentAnswers(itemNum);
                Toast.makeText(this, studentName+" has successfully entered", Toast.LENGTH_SHORT).show();
            }catch (Exception e){
                Toast.makeText(this, "Image failed to process, get a clear image and try again!", Toast.LENGTH_SHORT).show();
            }
            studentScoreSave();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_CODE:{
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    openGallery();
                }else{
                    Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    private void passListToList() {
        if (answerKey_List.get(0).one != "null" || answerKey_List.get(0).one != "") newAnswerKey.add(answerKey_List.get(0).one);
        if (answerKey_List.get(0).two != "null" || answerKey_List.get(0).two != "") newAnswerKey.add(answerKey_List.get(0).two);
        if (answerKey_List.get(0).three != "null" || answerKey_List.get(0).three !="") newAnswerKey.add(answerKey_List.get(0).three);
        if (answerKey_List.get(0).four != "null" || answerKey_List.get(0).four != "") newAnswerKey.add(answerKey_List.get(0).four);
        if (answerKey_List.get(0).five != "null" || answerKey_List.get(0).five != "") newAnswerKey.add(answerKey_List.get(0).five);
        if (answerKey_List.get(0).six != "null" || answerKey_List.get(0).six != "") newAnswerKey.add(answerKey_List.get(0).six);
        if (answerKey_List.get(0).seven != "null" || answerKey_List.get(0).seven != "") newAnswerKey.add(answerKey_List.get(0).seven);
        if (answerKey_List.get(0).eight != "null" || answerKey_List.get(0).eight != "") newAnswerKey.add(answerKey_List.get(0).eight);
        if (answerKey_List.get(0).nine != "null" || answerKey_List.get(0).nine != "") newAnswerKey.add(answerKey_List.get(0).nine);
        if (answerKey_List.get(0).ten != "null" || answerKey_List.get(0).ten != "") newAnswerKey.add(answerKey_List.get(0).ten);
        if (answerKey_List.get(0).eleven != "null" || answerKey_List.get(0).eleven != "") newAnswerKey.add(answerKey_List.get(0).eleven);
        if (answerKey_List.get(0).twelve != "null" || answerKey_List.get(0).twelve != "") newAnswerKey.add(answerKey_List.get(0).twelve);
        if (answerKey_List.get(0).thirteen != "null" || answerKey_List.get(0).thirteen != "") newAnswerKey.add(answerKey_List.get(0).thirteen);
        if (answerKey_List.get(0).fourteen != "null" || answerKey_List.get(0).fourteen != "") newAnswerKey.add(answerKey_List.get(0).fourteen);
        if (answerKey_List.get(0).fifteen != "null" || answerKey_List.get(0).fifteen != "") newAnswerKey.add(answerKey_List.get(0).fifteen);
        if (answerKey_List.get(0).sixteen != "null" || answerKey_List.get(0).sixteen != "") newAnswerKey.add(answerKey_List.get(0).sixteen);
        if (answerKey_List.get(0).seventeen != "null" || answerKey_List.get(0).seventeen != "") newAnswerKey.add(answerKey_List.get(0).seventeen);
        if (answerKey_List.get(0).eighteen != "null" || answerKey_List.get(0).eighteen != "") newAnswerKey.add(answerKey_List.get(0).eighteen);
        if (answerKey_List.get(0).nineteen != "null" || answerKey_List.get(0).nineteen != "") newAnswerKey.add(answerKey_List.get(0).nineteen);
        if (answerKey_List.get(0).twenty != "null" || answerKey_List.get(0).twenty != "") newAnswerKey.add(answerKey_List.get(0).twenty);
        if (answerKey_List.get(0).twenty_one != "null" || answerKey_List.get(0).twenty_one != "") newAnswerKey.add(answerKey_List.get(0).twenty_one);
        if (answerKey_List.get(0).twenty_two != "null" || answerKey_List.get(0).twenty_two != "") newAnswerKey.add(answerKey_List.get(0).twenty_two);
        if (answerKey_List.get(0).twenty_three != "null" || answerKey_List.get(0).twenty_three != "") newAnswerKey.add(answerKey_List.get(0).twenty_three);
        if (answerKey_List.get(0).twenty_four != "null" || answerKey_List.get(0).twenty_four != "") newAnswerKey.add(answerKey_List.get(0).twenty_four);
        if (answerKey_List.get(0).twenty_five != "null" || answerKey_List.get(0).twenty_five != "") newAnswerKey.add(answerKey_List.get(0).twenty_five);
        if (answerKey_List.get(0).twenty_six != "null" || answerKey_List.get(0).twenty_six != "") newAnswerKey.add(answerKey_List.get(0).twenty_six);
        if (answerKey_List.get(0).twenty_seven != "null" || answerKey_List.get(0).twenty_seven != "") newAnswerKey.add(answerKey_List.get(0).twenty_seven);
        if (answerKey_List.get(0).twenty_eight != "null" || answerKey_List.get(0).twenty_eight != "") newAnswerKey.add(answerKey_List.get(0).twenty_eight);
        if (answerKey_List.get(0).twenty_nine != "null" || answerKey_List.get(0).twenty_nine != "") newAnswerKey.add(answerKey_List.get(0).twenty_nine);
        if (answerKey_List.get(0).thirty != "null" || answerKey_List.get(0).thirty != "") newAnswerKey.add(answerKey_List.get(0).thirty);
        if (answerKey_List.get(0).thirty_one != "null" || answerKey_List.get(0).thirty_one != "") newAnswerKey.add(answerKey_List.get(0).thirty_one);
        if (answerKey_List.get(0).thirty_two != "null" || answerKey_List.get(0).thirty_two != "") newAnswerKey.add(answerKey_List.get(0).thirty_two);
        if (answerKey_List.get(0).thirty_three != "null" || answerKey_List.get(0).thirty_three != "") newAnswerKey.add(answerKey_List.get(0).thirty_three);
        if (answerKey_List.get(0).thirty_four != "null" || answerKey_List.get(0).thirty_four != "") newAnswerKey.add(answerKey_List.get(0).thirty_four);
        if (answerKey_List.get(0).thirty_five != "null" || answerKey_List.get(0).thirty_five != "") newAnswerKey.add(answerKey_List.get(0).thirty_five);
        if (answerKey_List.get(0).thirty_six != "null" || answerKey_List.get(0).thirty_six != "") newAnswerKey.add(answerKey_List.get(0).thirty_six);
        if (answerKey_List.get(0).thirty_seven != "null" || answerKey_List.get(0).thirty_seven != "") newAnswerKey.add(answerKey_List.get(0).thirty_seven);
        if (answerKey_List.get(0).thirty_eight != "null" || answerKey_List.get(0).thirty_eight != "") newAnswerKey.add(answerKey_List.get(0).thirty_eight);
        if (answerKey_List.get(0).thirty_nine != "null" || answerKey_List.get(0).thirty_nine != "") newAnswerKey.add(answerKey_List.get(0).nine);
        if (answerKey_List.get(0).forty != "null" || answerKey_List.get(0).forty != "") newAnswerKey.add(answerKey_List.get(0).forty);
        if (answerKey_List.get(0).forty_one != "null" || answerKey_List.get(0).forty_one != "") newAnswerKey.add(answerKey_List.get(0).forty_one);
        if (answerKey_List.get(0).forty_two != "null" || answerKey_List.get(0).forty_two != "") newAnswerKey.add(answerKey_List.get(0).forty_two);
        if (answerKey_List.get(0).forty_three != "null" || answerKey_List.get(0).forty_three != "") newAnswerKey.add(answerKey_List.get(0).forty_three);
        if (answerKey_List.get(0).forty_four != "null" || answerKey_List.get(0).forty_four != "") newAnswerKey.add(answerKey_List.get(0).forty_four);
        if (answerKey_List.get(0).forty_five != "null" || answerKey_List.get(0).forty_five != "") newAnswerKey.add(answerKey_List.get(0).forty_five);
        if (answerKey_List.get(0).forty_six != "null" || answerKey_List.get(0).forty_six != "") newAnswerKey.add(answerKey_List.get(0).forty_six);
        if (answerKey_List.get(0).forty_seven != "null" || answerKey_List.get(0).forty_seven != "") newAnswerKey.add(answerKey_List.get(0).forty_seven);
        if (answerKey_List.get(0).forty_eight != "null" || answerKey_List.get(0).forty_eight != "") newAnswerKey.add(answerKey_List.get(0).forty_eight);
        if (answerKey_List.get(0).forty_nine != "null" || answerKey_List.get(0).forty_nine != "") newAnswerKey.add(answerKey_List.get(0).forty_nine);
        if (answerKey_List.get(0).fifty != "null" || answerKey_List.get(0).fifty != "") newAnswerKey.add(answerKey_List.get(0).fifty);
        if (answerKey_List.get(0).fifty_one != "null" || answerKey_List.get(0).fifty_one != "") newAnswerKey.add(answerKey_List.get(0).fifty_one);
        if (answerKey_List.get(0).fifty_two != "null" || answerKey_List.get(0).fifty_two != "") newAnswerKey.add(answerKey_List.get(0).fifty_two);
        if (answerKey_List.get(0).fifty_three != "null" || answerKey_List.get(0).fifty_three != "") newAnswerKey.add(answerKey_List.get(0).fifty_three);
        if (answerKey_List.get(0).fifty_four != "null" || answerKey_List.get(0).fifty_four != "") newAnswerKey.add(answerKey_List.get(0).fifty_four);
        if (answerKey_List.get(0).fifty_five != "null" || answerKey_List.get(0).fifty_five != "") newAnswerKey.add(answerKey_List.get(0).fifty_five);
        if (answerKey_List.get(0).fifty_six != "null" || answerKey_List.get(0).fifty_six != "") newAnswerKey.add(answerKey_List.get(0).fifty_six);
        if (answerKey_List.get(0).fifty_seven != "null" || answerKey_List.get(0).fifty_seven != "") newAnswerKey.add(answerKey_List.get(0).fifty_seven);
        if (answerKey_List.get(0).fifty_eight != "null" || answerKey_List.get(0).fifty_eight != "") newAnswerKey.add(answerKey_List.get(0).fifty_eight);
        if (answerKey_List.get(0).fifty_nine != "null" || answerKey_List.get(0).fifty_nine != "") newAnswerKey.add(answerKey_List.get(0).fifty_nine);
        if (answerKey_List.get(0).sixty != "null" || answerKey_List.get(0).sixty != "") newAnswerKey.add(answerKey_List.get(0).sixty);
        if (answerKey_List.get(0).sixty_one != "null" || answerKey_List.get(0).sixty_one != "") newAnswerKey.add(answerKey_List.get(0).sixty_one);
        if (answerKey_List.get(0).sixty_two != "null" || answerKey_List.get(0).sixty_two != "") newAnswerKey.add(answerKey_List.get(0).sixty_two);
        if (answerKey_List.get(0).sixty_three != "null" || answerKey_List.get(0).sixty_three != "") newAnswerKey.add(answerKey_List.get(0).sixty_three);
        if (answerKey_List.get(0).sixty_four != "null" || answerKey_List.get(0).sixty_four != "") newAnswerKey.add(answerKey_List.get(0).sixty_four);
        if (answerKey_List.get(0).sixty_five != "null" || answerKey_List.get(0).sixty_five != "") newAnswerKey.add(answerKey_List.get(0).sixty_five);
        if (answerKey_List.get(0).sixty_six != "null" || answerKey_List.get(0).sixty_six != "") newAnswerKey.add(answerKey_List.get(0).sixty_six);
        if (answerKey_List.get(0).sixty_seven != "null" || answerKey_List.get(0).sixty_seven != "") newAnswerKey.add(answerKey_List.get(0).sixty_seven);
        if (answerKey_List.get(0).sixty_eight != "null" || answerKey_List.get(0).sixty_eight != "") newAnswerKey.add(answerKey_List.get(0).sixty_eight);
        if (answerKey_List.get(0).sixty_nine != "null" || answerKey_List.get(0).sixty_nine != "") newAnswerKey.add(answerKey_List.get(0).sixty_nine);
        if (answerKey_List.get(0).seventy != "null" || answerKey_List.get(0).seventy != "") newAnswerKey.add(answerKey_List.get(0).seventy);
        if (answerKey_List.get(0).seventy_one != "null" || answerKey_List.get(0).seventy_one != "") newAnswerKey.add(answerKey_List.get(0).seventy_one);
        if (answerKey_List.get(0).seventy_two != "null" || answerKey_List.get(0).seventy_two != "") newAnswerKey.add(answerKey_List.get(0).seventy_two);
        if (answerKey_List.get(0).seventy_three != "null" || answerKey_List.get(0).seventy_three != "") newAnswerKey.add(answerKey_List.get(0).seventy_three);
        if (answerKey_List.get(0).seventy_four != "null" || answerKey_List.get(0).seventy_four != "") newAnswerKey.add(answerKey_List.get(0).seventy_four);
        if (answerKey_List.get(0).seventy_five != "null" || answerKey_List.get(0).seventy_five != "") newAnswerKey.add(answerKey_List.get(0).seventy_five);
    }

    private void studentAnswers25items(String student_name, String set_name
            ,String one,String two,String three,String four,String five,String six,String seven
            ,String eight,String nine,String ten,String eleven,String twelve,String thirteen,String fourteen
            ,String fifteen,String sixteen,String seventeen,String eighteen,String nineteen,String twenty
            ,String twenty_one,String twenty_two,String twenty_three,String twenty_four,String twenty_five) {
        SetDatabase db = SetDatabase.getInstance(this.getApplicationContext());
        Student student = new Student();
        student.stud_name = student_name;
        student.sets_name = set_name;

        student.one = one; student.two = two; student.three = three;
        student.four = four; student.five = five; student.six = six;
        student.seven = seven; student.eight = eight; student.nine = nine;
        student.ten = ten; student.eleven = eleven; student.twelve = twelve;
        student.thirteen = thirteen; student.fourteen = fourteen; student.fifteen = fifteen;
        student.sixteen = sixteen; student.seventeen = seventeen; student.eighteen = eighteen;
        student.nineteen = nineteen; student.twenty = twenty; student.twenty_one = twenty_one;
        student.twenty_two = twenty_two; student.twenty_three = twenty_three; student.twenty_four = twenty_four;
        student.twenty_five = twenty_five;

        db.setsDao().insertStudents(student);
    }

    private void studentAnswers50items(String student_name,String set_name
            ,String one,String two,String three,String four,String five,String six,String seven
            ,String eight,String nine,String ten,String eleven,String twelve,String thirteen,String fourteen
            ,String fifteen,String sixteen,String seventeen,String eighteen,String nineteen,String twenty
            ,String twenty_one,String twenty_two,String twenty_three,String twenty_four,String twenty_five
            ,String twenty_six,String twenty_seven,String twenty_eight,String twenty_nine,String thirty
            ,String thirty_one,String thirty_two,String thirty_three,String thirty_four,String thirty_five
            ,String thirty_six,String thirty_seven,String thirty_eight,String thirty_nine,String forty
            ,String forty_one,String forty_two,String forty_three,String forty_four,String forty_five
            ,String forty_six,String forty_seven,String forty_eight,String forty_nine,String fifty) {
        SetDatabase db = SetDatabase.getInstance(this.getApplicationContext());
        Student student = new Student();
        student.stud_name = student_name;
        student.sets_name = set_name;

        student.one = one; student.two = two; student.three = three;
        student.four = four; student.five = five; student.six = six;
        student.seven = seven; student.eight = eight; student.nine = nine;
        student.ten = ten; student.eleven = eleven; student.twelve = twelve;
        student.thirteen = thirteen; student.fourteen = fourteen; student.fifteen = fifteen;
        student.sixteen = sixteen; student.seventeen = seventeen; student.eighteen = eighteen;
        student.nineteen = nineteen; student.twenty = twenty;
        student.twenty_one = twenty_one;student.twenty_two = twenty_two;student.twenty_three = twenty_three;
        student.twenty_four = twenty_four;student.twenty_five = twenty_five;student.twenty_six = twenty_six;
        student.twenty_seven = twenty_seven;student.twenty_eight = twenty_eight;student.twenty_nine = twenty_nine;
        student.thirty = thirty;student.thirty_one = thirty_one;student.thirty_two = thirty_two;
        student.thirty_three = thirty_three;student.thirty_four = thirty_four;student.thirty_five = thirty_five;
        student.thirty_six = thirty_six;student.thirty_seven = thirty_seven;student.thirty_eight = thirty_eight;
        student.thirty_nine = thirty_nine;student.forty = forty;student.forty_one = forty_one;
        student.forty_two = forty_two;student.forty_three = forty_three;student.forty_four = forty_four;
        student.forty_five = forty_five;student.forty_six = forty_six;student.forty_seven = forty_seven;
        student.forty_eight = forty_eight;student.forty_nine = forty_nine;student.fifty = fifty;

        db.setsDao().insertStudents(student);
    }

    private void studentAnswers75items(String student_name ,String set_name
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
            ,String seventy_one,String seventy_two,String seventy_three,String seventy_four,String seventy_five) {
        SetDatabase db = SetDatabase.getInstance(this.getApplicationContext());
        Student student = new Student();
        student.stud_name = student_name;
        student.sets_name = set_name;

        student.one = one; student.two = two; student.three = three;
        student.four = four; student.five = five; student.six = six;
        student.seven = seven; student.eight = eight; student.nine = nine;
        student.ten = ten; student.eleven = eleven; student.twelve = twelve;
        student.thirteen = thirteen; student.fourteen = fourteen; student.fifteen = fifteen;
        student.sixteen = sixteen; student.seventeen = seventeen; student.eighteen = eighteen;
        student.nineteen = nineteen; student.twenty = twenty;student.twenty_one = twenty_one;
        student.twenty_two = twenty_two;student.twenty_three = twenty_three;student.twenty_four = twenty_four;
        student.twenty_five = twenty_five;student.twenty_six = twenty_six;student.twenty_seven = twenty_seven;
        student.twenty_eight = twenty_eight;student.twenty_nine = twenty_nine;student.thirty = thirty;
        student.thirty_one = thirty_one;student.thirty_two = thirty_two;student.thirty_three = thirty_three;
        student.thirty_four = thirty_four;student.thirty_five = thirty_five;student.thirty_six = thirty_six;
        student.thirty_seven = thirty_seven;student.thirty_eight = thirty_eight;student.thirty_nine = thirty_nine;
        student.forty = forty;student.forty_one = forty_one;student.forty_two = forty_two;
        student.forty_three = forty_three;student.forty_four = forty_four;student.forty_five = forty_five;
        student.forty_six = forty_six;student.forty_seven = forty_seven;student.forty_eight = forty_eight;
        student.forty_nine = forty_nine;student.fifty = fifty;student.fifty_one = fifty_one;
        student.fifty_two = fifty_two;student.fifty_three = fifty_three;student.fifty_four = fifty_four;
        student.fifty_five = fifty_five;student.fifty_six = fifty_six;student.fifty_seven = fifty_seven;
        student.fifty_eight = fifty_eight;student.fifty_nine = fifty_nine;student.sixty = sixty;student.sixty_one = sixty_one;student.sixty_two = sixty_two;
        student.sixty_three = sixty_three;student.sixty_four = sixty_four;student.sixty_five = sixty_five;
        student.sixty_six = sixty_six;student.sixty_seven = sixty_seven;student.sixty_eight = sixty_eight;
        student.sixty_nine = sixty_nine;student.seventy = seventy;student.seventy_one = seventy_one;
        student.seventy_two = seventy_two;student.seventy_three = seventy_three;student.seventy_four = seventy_four;
        student.seventy_five = seventy_five;

        db.setsDao().insertStudents(student);
    }
}