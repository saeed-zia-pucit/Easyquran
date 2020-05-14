package com.example.easyquran;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import java.util.ArrayList;

public class MushafMode extends AppCompatActivity {
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mushaf_mode);
        editText=(EditText)findViewById(R.id.editText);


        Intent intent=getIntent();

        ArrayList<String> list=intent.getStringArrayListExtra("list");
       // Intent intent1=getIntent();


        StringBuilder builder = new StringBuilder();
         int a=list.size();
        for (String details : list) {
            builder.append(details + "\n");
        }

        editText.setText(builder.toString());


    }
}
