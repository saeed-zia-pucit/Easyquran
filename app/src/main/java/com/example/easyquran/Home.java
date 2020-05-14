package com.example.easyquran;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.util.Log;
import java.util.ArrayList;

public class Home extends AppCompatActivity {

    ListView listView;
    DatabaseHelper d;
    ArrayList<Data>data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        listView = (ListView) findViewById(R.id.listview_with_fab);

        String[] listItwms = new String[]{"Al-Fatiha","Al-Baqarah","Aal-Imran","An-nisa","Aal-Maida","Al-anam","Al-araf","Al-Anfal","Al-Tobah","younus","Hood" };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, listItwms);
        listView.setAdapter(adapter);

        Intent intent=getIntent();
        final  int card=intent.getIntExtra("mode",0);

    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               if(card==1)
               {
                   d=new DatabaseHelper(getApplicationContext());
                   data=new ArrayList<Data>();
                   data=d.fetchData();
                   ArrayList<String> arabic= new ArrayList<String>();
                   ArrayList<Integer> surat_list=new ArrayList<>();
                   Log.d("surat_no","yesreached");
                   for (int i = 0; i < 1199; i++) {
                       arabic.add(data.get(i).Arabic);
                       surat_list.add(new Integer(data.get(i).surat_no));
                       Log.d("surat_no",data.get(i).Arabic);
                   }

                   int surat_po = surat_list.indexOf(position+1);


                   Intent intent=new Intent(Home.this,Mushaf_list.class);
                   intent.putStringArrayListExtra("mushaf_list",arabic);
                   intent.putExtra("pos",surat_po);
                   startActivity(intent);
               }
             else {

                   Intent intent = new Intent(Home.this, MainActivity.class);
                   intent.putExtra("surat", position + 1);
                   intent.putExtra("mode", "multi");
                   startActivity(intent);
               }

        }
    });
        FloatingActionButton fab = findViewById(R.id.floating_action_button_fab_with_listview);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                int last=PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getInt("last", -1);
                Intent intent=new Intent(Home.this,MainActivity.class);
                intent.putExtra("surat",1);
                intent.putExtra("mode","multi");
                intent.putExtra("last",last);
                startActivity(intent);
            }
        });

    }
}