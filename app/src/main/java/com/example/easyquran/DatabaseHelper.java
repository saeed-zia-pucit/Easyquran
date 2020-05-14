package com.example.easyquran;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public  static  final String database1="quranmjed.db";
    public  static  final String table1="alquran1";
    public  static  final String table2="bookmark";
    //public  static  final int col1=0;
    //public  static  final String col2="NAME";


    public DatabaseHelper(Context context) {
        super(context, database1, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS   "+table1+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,SURAT INTEGER,AYAT INTEGER,ARABIC TEXT,AHMEDALI TEXT,AHMEDRAZA TEXT ,JALAND TEXT) ");
        db.execSQL("CREATE TABLE IF NOT EXISTS   bookmark(ID INTEGER PRIMARY KEY AUTOINCREMENT,POSITION INTEGER,VALUE TEXT) ");


    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+table1);
        db.execSQL("DROP TABLE IF EXISTS "+table2);
        onCreate(db);
    }

    public void insertD(int surat_no,int ayt_no,String arabic,String a_ali,String a_raza,String jaland){
        SQLiteDatabase db=this.getWritableDatabase();
        //db.execSQL("DROP TABLE IF EXISTS "+table1);
        db.execSQL("CREATE TABLE IF NOT EXISTS   alquran1(ID INTEGER PRIMARY KEY AUTOINCREMENT,SURAT INTEGER,AYAT INTEGER,ARABIC TEXT,AHMEDALI TEXT,AHMEDRAZA TEXT ,JALAND TEXT) ");

        db.execSQL("insert into alquran1(SURAT,AYAT,ARABIC,AHMEDALI,AHMEDRAZA,JALAND) values('"+surat_no+"','"+ayt_no+"','"+arabic+"','"+a_ali+"','"+a_raza+"','"+jaland+"')");
    }


    public void StoreBookMark(int index,String text){


        SQLiteDatabase db1=this.getWritableDatabase();
        db1.execSQL("CREATE TABLE IF NOT EXISTS   bookmark(ID INTEGER PRIMARY KEY AUTOINCREMENT,POSITION INTEGER,VALUE TEXT) ");


        db1.execSQL("insert into bookmark(POSITION,VALUE) values('"+index+"','"+text+"')");

    }
    public  ArrayList<Data> fetchData(){
        Data d;
        int i=0;
        ArrayList<Data> dataarray=new ArrayList<Data>();
        SQLiteDatabase db=this.getReadableDatabase();
        String projection[]={"SURAT","AYAT","ARABIC","AHMEDALI","AHMEDRAZA","JALAND"};
        Cursor c=db.query(table1,projection,null,null,null,null,null);
        c.moveToFirst();

        while(c.moveToNext())
        {

            d=new Data(c.getInt(0),c.getInt(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5));
           dataarray.add(d);
           System.out.println("name is"+c.getString(0)+c.getString(1));
            //dataarray.get(i);
        }

        return dataarray;

    }

    public  ArrayList<BookmarkData> getBookmark(){
        BookmarkData d;
        int i=0;
        ArrayList<BookmarkData> dataarray=new ArrayList<BookmarkData>();
        SQLiteDatabase db=this.getReadableDatabase();
        String projection[]={"POSITION","VALUE"};
        Cursor c=db.query("bookmark",projection,null,null,null,null,null);
        c.moveToFirst();
        // System.out.println("name is"+c.getString(0)+c.getString(1)+c.getString(2));

        //c.moveToNext();
        //  data=new Data(c.getString(0),c.getString(1));
        while(c.moveToNext()){

            d=new BookmarkData(c.getInt(0),c.getString(1));
            dataarray.add(d);
            System.out.println("name is"+c.getInt(0)+c.getString(1));
            //dataarray.get(i);
        }
        return dataarray;

    }



}

