package com.example.easyquran;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TabHost;
import android.widget.TextView;
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

public class MainActivity extends AppCompatActivity {
    ListView listv1, listv2, listv3;
    TabHost host;
    int pp = 0;
    ArrayList<Data> data;
    ArrayList<String> listt1;
    ArrayList<String> listt2;
    int last_pos;
    int no;
    ///ArrayList<Integer> surat_list;


    //
    InputStream inputStream1, inputStream2, inputStream3, inputStream4;
    DatabaseHelper databaseHelper;//=new DatabaseHelper(this);
    String[] ids1;
    String[] ids2;
    String[] ids3;
    String[] ids4;

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //p = 0;pp=0;
///
        int index = 0;
        ///


        Intent intent = getIntent();
        String mode = intent.getStringExtra("mode");
        no = intent.getIntExtra("surat", 1);
        int card = intent.getIntExtra("card", 1);
        int last = intent.getIntExtra("last", -1);


        databaseHelper = new DatabaseHelper(this);


        //read_store();
        data = databaseHelper.fetchData();

        ///

        host = (TabHost) findViewById(R.id.tabHost);
        host.setup();
///

        // System.out.println("arraylist is"+data.get(0).ahmedali);

//
        //Tab 1

        listt1 = new ArrayList<String>();
        listt2 = new ArrayList<String>();
        //ArrayList <String> listt2 =new ArrayList<String>();


        ArrayList<String> listt3 = new ArrayList<String>();
        ArrayList<String> listt4 = new ArrayList<String>();
        ArrayList<Integer> surat_list = new ArrayList<Integer>();

        for (int i = 0; i < 1199; i++) {
            listt1.add(data.get(i).Arabic);
            listt2.add(data.get(i).ahmedali);
            listt3.add(data.get(i).ahmedraza);
            listt4.add(data.get(i).jaland);
            surat_list.add(new Integer(data.get(i).surat_no));
            //System.out.println("arraylist is"+data.get(i).ahmedali);
        }

        if (mode.equals("single")) {
            Intent intent2 = new Intent(getApplicationContext(), MushafMode.class);

            switch (card) {

                case 1:

                    Intent intent1 = new Intent(getApplicationContext(), Mushaf_list.class);
                    intent1.putStringArrayListExtra("mushaf_list", listt1);
                    startActivity(intent1);
                    //intent2.putExtra("list",listt1);
                    //startActivity(intent2);
                    break;
                case 2:
                    intent2.putExtra("list", listt2);
                    startActivity(intent2);
                    break;
                case 3:
                    intent2.putExtra("list", listt3);
                    startActivity(intent2);
                    break;
                case 4:
                    intent2.putExtra("list", listt4);
                    startActivity(intent2);
                    break;
                case 5://about us
                    String string = getString(R.string.about_us);
                    intent2.putExtra("list", listt3);
                    startActivity(intent2);
                    break;

            }

            //Auto select item3
        }

        listv1 = findViewById(R.id.list1);
        listv3 = findViewById(R.id.list3);
        listv2 = findViewById(R.id.list2);

        registerForContextMenu(listv1);
        registerForContextMenu(listv2);
        registerForContextMenu(listv3);


        final MyAdapter adapter1 = new MyAdapter(this, R.layout.row, listt1, listt2);
        listv1.setAdapter(adapter1);
        //find index of surat

        //


        TabHost.TabSpec spec = host.newTabSpec("Ahmed Ali");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Ahmed Ali");
        host.addTab(spec);

//
        //last read

        //
        MyAdapter adapter2 = new MyAdapter(this, R.layout.row, listt1, listt3);
        listv2.setAdapter(adapter2);
        listv2.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                listv1.setSelection(firstVisibleItem);
                listv2.setSelection(firstVisibleItem);
                listv3.setSelection(firstVisibleItem);
                last_pos = firstVisibleItem;
            }
        });


        //Tab 2
        spec = host.newTabSpec("Ahmed Raza");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Ahmed Raza");
        host.addTab(spec);


        MyAdapter adapter3 = new MyAdapter(this, R.layout.row, listt1, listt4);
        listv3.setAdapter(adapter3);
        listv3.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                listv1.setSelection(firstVisibleItem);
                listv2.setSelection(firstVisibleItem);
                listv3.setSelection(firstVisibleItem);
                last_pos = firstVisibleItem;

            }
        });


        listv1.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                System.out.println("scrollState" + scrollState);

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                listv1.setSelection(firstVisibleItem);

                listv2.setSelection(firstVisibleItem);
                listv3.setSelection(firstVisibleItem);
                last_pos = firstVisibleItem;

            }
        });

        //Tab 3
        spec = host.newTabSpec("Fteh Jalandihary");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Fateh Jalandihary");
        host.addTab(spec);


        int surat_po = surat_list.indexOf(no);
        listv1.setSelection(surat_po);
        listv3.setSelection(surat_po);
        listv2.setSelection(surat_po);
        if (last != -1) {

            listv1.setSelection(last);
        }

    }

    protected void onPause() {
        super.onPause();

        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putInt("last", last_pos).apply();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.pop_up, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.item1:
//
               // new EasyQuran.JSONTask().execute("https://api.myjson.com/bins/vos2d");
               JSONTask j=new JSONTask();
               j.execute("https://api.myjson.com/bins/167q49");
               // new EasyQuran.JSONTask().execute("https://api.myjson.com/bins/vos2d");


                return true;

            case R.id.item2:
                Intent intent1=new Intent(getApplicationContext(),BookMarks.class);
               // intent1.putExtra("bookmark",data.get(0).ahmedali);
                 startActivityForResult(intent1,1);
                return  true;
            case R.id.item3:
                Intent intent2 = new Intent(getApplicationContext(), Mushaf_list.class);
                intent2.putExtra("mushaf_list", listt1);
                intent2.putExtra("pos", last_pos);
                startActivity(intent2);
                return true;
            case R.id.item4:
                Intent intent3 = new Intent(getApplicationContext(), MushafMode.class);
                intent3.putExtra("list", listt2);
                startActivity(intent3);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);


        menu.setHeaderTitle("select Option");
        menu.add(0, v.getId(), 0, "Copy");
        menu.add(0, v.getId(), 0, "Share");
        menu.add(0, v.getId(), 0, "Bookmark");


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getStringExtra("result");
                int p = listt1.indexOf(result);
                if (p > -1) {
                    listv1.setSelection(p);
                }
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        TextView textView = (TextView) info.targetView.findViewById(R.id.textView);
        String text = textView.getText().toString();
        int pos = textView.getVerticalScrollbarPosition();

        if (item.getTitle() == "Copy") {
            // do your coding\


            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("text", text);
            clipboard.setPrimaryClip(clip);
            Toast.makeText(getApplicationContext(), "Selected " + text, Toast.LENGTH_LONG).show();

        } else if (item.getTitle() == "Bookmark") {
            Toast.makeText(getApplicationContext(), "Bookmarked", Toast.LENGTH_SHORT).show();
            databaseHelper.StoreBookMark(pos, text);

        } else {
            return false;
        }
        return true;
    }

    public void read_store() {
        inputStream1 = getResources().openRawResource(R.raw.ahmedali);
        inputStream2 = getResources().openRawResource(R.raw.ahmedraza);
        inputStream3 = getResources().openRawResource(R.raw.jalnd);
        inputStream4 = getResources().openRawResource(R.raw.arabic);


        BufferedReader reader1 = new BufferedReader(new InputStreamReader(inputStream1));
        BufferedReader reader2 = new BufferedReader(new InputStreamReader(inputStream2));
        BufferedReader reader3 = new BufferedReader(new InputStreamReader(inputStream3));
        BufferedReader reader4 = new BufferedReader(new InputStreamReader(inputStream4));

        try {
            String csvLine1, csvLine2, csvLine3, csvLine4;
            csvLine3 = reader3.readLine();

            int a, b;
            int m = 1, n = 1;
            for (int i = 0; i < 1200; i++) {
                csvLine1 = reader1.readLine();
                csvLine2 = reader2.readLine();
                csvLine3 = reader3.readLine();
                csvLine4 = reader4.readLine();


                ids1 = csvLine1.split(",");
                ids2 = csvLine2.split(",");
                ids3 = csvLine3.split(",");
                ids4 = csvLine4.split(",");


                try {


                    //if(ids4[0]!="1"){
                    String s = ids4[0].trim();
                    a = Integer.parseInt(s);
                    s = ids4[1].trim();
                    b = Integer.parseInt(s);

                    // int c=0;
                    databaseHelper.insertD(a, b, ids4[2], ids1[0], ids2[0], ids3[3]);


                } catch (Exception e) {

                    Log.e("Unknown fuck", e.toString());
                    databaseHelper.insertD(m, n, ids4[2], ids1[0], ids2[0], ids3[3]);
                    m++;

                }
            }


        } catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file: " + ex);
        }

    }


    //json class

     public  class JSONTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();

                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                //return buffer.toString();
                String finalJson = buffer.toString();

                try {
                    JSONObject parentObject = new JSONObject(finalJson);
                    JSONArray parentArray = parentObject.getJSONArray("ayat");
                    String data = "";

                        JSONObject finalObject = parentArray.getJSONObject(--no);

                        data = data + (finalObject.getString("number"));



                    return data;
                    //return "Avengers - 2011";
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null)
                    connection.disconnect();
                try {
                    if (reader != null)
                        reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //tvData.setText(result);
            String s = result;

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(result));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setPackage("com.google.android.youtube");
            startActivity(intent);
            //call_mushaf(result);
        }
    }

    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        TextView arabic, trans;
        int resource;
        ArrayList<String> l1, l2;
        LayoutInflater inflater;
        int pp = 0;

        MyAdapter(Context c, int resource, ArrayList l1, ArrayList l2) {

            super(c, resource, l1);
            this.l1 = l1;
            this.l2 = l2;
            this.resource = resource;
            inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        public int getpp() {
            return pp;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            pp = position;
            MyAdapter.Holder holder = null;
            if (convertView == null) {
                holder = new MyAdapter.Holder();
                convertView = inflater.inflate(resource, null);
                holder.ar = (TextView) convertView.findViewById(R.id.textView);
                holder.tr = (TextView) convertView.findViewById(R.id.textView2);
                convertView.setTag(holder);

            } else {
                holder = (MyAdapter.Holder) convertView.getTag();

            }
            holder.ar.setText(l1.get(position).toString());
            holder.tr.setText(l2.get(position).toString());

            return convertView;
        }

        class Holder {
            TextView ar, tr;
        }
    }

}

