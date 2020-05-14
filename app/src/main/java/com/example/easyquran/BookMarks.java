package com.example.easyquran;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class BookMarks extends AppCompatActivity {
ListView listView;
ArrayList<BookmarkData> book_array;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_marks);


        DatabaseHelper databaseHelper=new DatabaseHelper(this);
        book_array=databaseHelper.getBookmark();



        ArrayList <String> listt1 =new ArrayList<String>();
        for(int i=0;i<book_array.size();i++) {
            listt1.add(book_array.get(i).text);

            //System.out.println("arraylist is"+data.get(i).ahmedali);
        }
        listView=findViewById(R.id.list);

        final BookMarks.MyAdapter adapter1 = new BookMarks.MyAdapter(this,R.layout.bookmark_row,listt1);
        listView.setAdapter(adapter1);
       // registerForContextMenu(listView);


listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String s=listView.getItemAtPosition(position).toString();
        System.out.println("here is"+s);
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",s);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }
});



    }



    class MyAdapter extends ArrayAdapter<String>
    {
        int resource;
        ArrayList<String> l1;
        LayoutInflater inflater;
        int pp=0;
        MyAdapter(Context c,int resource,ArrayList l1)
        {

            super(c,resource,l1);
            this.l1 = l1;

            this.resource = resource;
            inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            BookMarks.MyAdapter.Holder holder = null;
            if(convertView == null)
            {
                holder = new BookMarks.MyAdapter.Holder();
                convertView = inflater.inflate(resource,null);
                holder.ar = (TextView) convertView.findViewById(R.id.textView4);
                convertView.setTag(holder);

            }
            else {
                holder = (BookMarks.MyAdapter.Holder)convertView.getTag();

            }
            holder.ar.setText(l1.get(position).toString());

            return convertView;
        }
        class Holder
        {
            TextView ar,tr;
        }

    }
}
