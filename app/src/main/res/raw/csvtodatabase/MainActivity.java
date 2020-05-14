package com.example.csvtodatabase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    InputStream inputStream1,inputStream2,inputStream3;
    DatabaseHelper databaseHelper=new DatabaseHelper(this);
    String[] ids1;
    String[] ids2;
    String[] ids3;


    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       read_store();
        //read_and_store();
      // Data d= databaseHelper.fetchData();
      //textView =(TextView)findViewById(R.id.textview);
       //textView.setText(d.getIntro());

    }
    public  void read_store(){
        inputStream1 = getResources().openRawResource(R.raw.ahmedali);
        inputStream2 = getResources().openRawResource(R.raw.ahmedraza);
        inputStream3 = getResources().openRawResource(R.raw.jalnd);


        BufferedReader reader1 = new BufferedReader(new InputStreamReader(inputStream1));
        BufferedReader reader2 = new BufferedReader(new InputStreamReader(inputStream2));
        BufferedReader reader3 = new BufferedReader(new InputStreamReader(inputStream3));

        try {
            String csvLine1,csvLine2,csvLine3;
            csvLine3 = reader3.readLine();

            for(int i=0;i<5;i++) {
                csvLine1 = reader1.readLine();
                csvLine2 = reader2.readLine();
                csvLine3 = reader3.readLine();


                ids1=csvLine1.split(",");
                ids2=csvLine2.split(",");
                ids3=csvLine3.split(",");

                try{

                    Log.e("Collumn 1 ",""+ids1[0]) ;
                    Log.e("Collumn 2 ",""+ids2[0]) ;
                    Log.e("Collumn 3 ",""+ids3[3]) ;
                    // System.out.println("column is"+ids1[0]);


                   // databaseHelper.insertD(ids[2],ids[3]);


                }catch (Exception e){
                    Log.e("Unknown fuck",e.toString());
                }
            }




        }
        catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file: "+ex);
        }

    }


    public  void read_and_store(){
        inputStream1 = getResources().openRawResource(R.raw.ahmedali);


        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream1));
        try {
            String csvLine;
            for(int i=0;i<5;i++) {
                csvLine = reader.readLine();


                ids1=csvLine.split(",");
                try{

                    Log.e("Collumn 1 ",""+ids1[0]) ;
                   // databaseHelper.insertD(ids1[2],ids1[3]);


                }catch (Exception e){
                    Log.e("Unknown fuck",e.toString());
                }
            }




        }
        catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file: "+ex);
        }

    }

}
