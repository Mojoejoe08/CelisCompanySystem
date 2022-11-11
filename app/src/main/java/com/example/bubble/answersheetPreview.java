package com.example.bubble;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class answersheetPreview extends AppCompatActivity {
    PDFView pdfView;
    private static final int MY_PERMISSION_REQUEST_STORAGE = 1;
    int num = Item.num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answersheet_preview);
        //setting title
        getSupportActionBar().setTitle("Answer Sheet");

        //PDF previewing using API barteksc | AndroidPdfViewer
        pdfView = (PDFView) findViewById(R.id.answerSheetView);
        previewPdf();

        Button answerSheetBtn = (Button) findViewById(R.id.answerSheetBtn);
        answerSheetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(answersheetPreview.this,MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                }catch (Exception e){
                    Toast.makeText(answersheetPreview.this, "Problem", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button answerSheetBackBtn = (Button) findViewById(R.id.answerSheetBackBtn);
        answerSheetBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(answersheetPreview.this,questionnaire.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);

            }
        });
        Button answerSheetDownloadBtn = (Button) findViewById(R.id.answerSheetDownloadBtn);
        answerSheetDownloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num==25){
                    copyAsset("25_item_draft_thesis.pdf");
                }else if(num==50){
                    copyAsset("50_item_draft_thesis.pdf");
                }else if (num==75){
                    copyAsset("75_item_draft_thesis.pdf");
                }else{
                    Toast.makeText(answersheetPreview.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void previewPdf() {
        // to identify which pdf to use
        if (num==25){
            pdfView.fromAsset("25_item_draft_thesis.pdf").load();
        }else if(num==50){
            pdfView.fromAsset("50_item_draft_thesis.pdf").load();
        }else if (num==75){
            pdfView.fromAsset("75_item_draft_thesis.pdf").load();
        }else{
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
        }
    }
    private void copyAsset(String filename){
        String dirPath = String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS));
        File dir = new File(dirPath);
        if (!dir.exists()){
            dir.mkdirs();
        }
        AssetManager assetManager = getAssets();
        InputStream in = null;
        OutputStream  out = null;
        try {
            in = assetManager.open(filename);
            File outFile = new File(dirPath,filename);
            out = new FileOutputStream(outFile);
            copyFile(in,out);
            Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Not Saved!", Toast.LENGTH_SHORT).show();
        }finally {
            if(in !=null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(out !=null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer))!=-1){
            out.write(buffer, 0, read);
        }
    }
}