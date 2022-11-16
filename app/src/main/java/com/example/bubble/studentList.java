package com.example.bubble;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import java.io.ByteArrayOutputStream;

public class studentList extends AppCompatActivity {
    private static final int PERMISSION_CODE = 1000;
    ImageButton studentListImageCaptureBtn;
    String studentName;
    Bitmap imgbitmap;
    String imageString="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        studentListImageCaptureBtn = (ImageButton) findViewById(R.id.studentListImageCaptureBtn);
        studentListImageCaptureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogStudentNameInput = new AlertDialog.Builder(studentList.this);
                dialogStudentNameInput.setTitle("Name of Paper");
                final EditText nameofPaper = new EditText(studentList.this);
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
                                openCamera();
                            }
                        }else{
                            openCamera();
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
        final Python py = Python.getInstance();
        imageString = getStringImage(imgbitmap);
        PyObject pyObject = py.getModule("func");
        PyObject object = pyObject.callAttr("imageProcess",imageString);
    }

    private String getStringImage(Bitmap imgbitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        imgbitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = android.util.Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void openCamera() {
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera, PERMISSION_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PERMISSION_CODE){
            imgbitmap = (Bitmap) data.getExtras().get("data");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_CODE:{
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    openCamera();
                }else{
                    Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}