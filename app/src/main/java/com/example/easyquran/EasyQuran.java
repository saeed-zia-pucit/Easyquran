package com.example.easyquran;

import android.app.DownloadManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class EasyQuran extends AppCompatActivity {

    GridLayout mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_quran);
        mainGrid = (GridLayout) findViewById(R.id.mainGrid);

        //Set Event
        setSingleEvent(mainGrid);
        //setToggleEvent(mainGrid);
    }

    public void call_mushaf() {
       // ArrayList<String> list=new ArrayList<String>();
        String s=getString(R.string.about_us);
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(s.split(",")));
        Intent intent = new Intent(EasyQuran.this, MushafMode.class);

        intent.putExtra("list", list);

        startActivity(intent);

    }





    private void setToggleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            final CardView cardView = (CardView) mainGrid.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (cardView.getCardBackgroundColor().getDefaultColor() == -1) {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FF6F00"));
                        Toast.makeText(EasyQuran.this, "State : True", Toast.LENGTH_SHORT).show();

                    } else {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                        Toast.makeText(EasyQuran.this, "State : False", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setSingleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (finalI == 0) {

                        Intent intent = new Intent(EasyQuran.this, Home.class);
                        // intent.putExtra("mode","single");
                        // intent.putExtra("selected_card",finalI);
                        startActivity(intent);
                    }
                    else if(finalI==1){
                        Intent intent = new Intent(EasyQuran.this, Home.class);
                         intent.putExtra("mode",1);
                        // intent.putExtra("selected_card",finalI);
                        startActivity(intent);

                    }
                    else if (finalI == 5) {


                     call_mushaf();

                        // test();
                    }

                    else {
                        Intent intent = new Intent(EasyQuran.this, MainActivity.class);
                        intent.putExtra("card", finalI);
                        intent.putExtra("mode", "single");

                        startActivity(intent);
                    }

                }
            });
        }
    }


}