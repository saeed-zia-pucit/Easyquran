package com.example.easyquran;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Mushaf_list extends AppCompatActivity {
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mushaf_list);

        Intent intent=getIntent();
        ArrayList<String>list=intent.getStringArrayListExtra("mushaf_list");
        int pos=intent.getIntExtra("pos",1);
        lv=(ListView) findViewById(R.id.listview_with_fab);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, list);
        lv.setAdapter(adapter);
        lv.setSelection(pos);

    }
}
