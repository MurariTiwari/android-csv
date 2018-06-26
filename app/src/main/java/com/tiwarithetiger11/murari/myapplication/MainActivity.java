package com.tiwarithetiger11.murari.myapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
checkpermission();
       // scsv();
    }

    private void scsv() {

        try{


            String baseDir = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
            String fileName = "AnalysisData.csv";
            String filePath = baseDir + File.separator + fileName;
            File f = new File(filePath );
            CSVWriter writer;
// File exist
            if(f.exists() && !f.isDirectory()){
               FileWriter mFileWriter = new FileWriter(filePath , true);
                writer = new CSVWriter(mFileWriter);
            }
            else {
                writer = new CSVWriter(new FileWriter(filePath));
            }
            List<String[]> data = new ArrayList<String[]>();
            data.add(new String[] {"murari", "murari"});
            data.add(new String[] {"murari", "murari"});
            data.add(new String[] {"Germany", "Berlin"});


            writer.writeAll(data);

            writer.close();
            Toast.makeText(this,"Written to File",Toast.LENGTH_LONG).show();



        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void checkpermission() {
        if(Build.VERSION.SDK_INT>=23)
        {
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},123);
            }

        }
      //  loadSong();
        scsv();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode)
        {
            case 123:
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                   // loadSong();
               scsv();
                }else
                {
                    Toast.makeText(this,"Permission Denied",Toast.LENGTH_LONG).show();
                    checkpermission();
                }break;
            default: super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }


    }

}
