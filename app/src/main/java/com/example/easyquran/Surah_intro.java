package com.example.easyquran;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Surah_intro extends AppCompatActivity {
    //TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surah_intro);

        Intent intent=getIntent();
        EditText editText=(EditText)findViewById(R.id.editText2);
        editText.setText(intent.getStringExtra("surah_intro"));



    }
}
