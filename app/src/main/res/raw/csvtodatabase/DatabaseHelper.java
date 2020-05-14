package com.example.csvtodatabase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public  static  final String database1="packages.db";
    public  static  final String table1="companies";
    //public  static  final int col1=0;
    //public  static  final String col2="NAME";


    public DatabaseHelper(Context context) {
        super(context, database1, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS   "+table1+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,TRANSLATION TEXT) ");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+table1);
        onCreate(db);
    }
    public void insertD(String name,String trans){
        SQLiteDatabase db=this.getWritableDatabase();

        db.execSQL("insert into companies(NAME,TRANSLATION) values('"+name+"','"+trans+"')");
    }
    public  Data fetchData(){
        Data data;
        SQLiteDatabase db=this.getReadableDatabase();
        String projection[]={"NAME","TRANSLATION"};
        Cursor c=db.query(table1,projection,null,null,null,null,null);
        c.moveToFirst();
        // System.out.println("name is"+c.getString(0)+c.getString(1)+c.getString(2));

        c.moveToNext();
        data=new Data(c.getString(0),c.getString(1));
        while(c.moveToNext()){

            System.out.println("name is"+c.getString(0)+c.getString(1));

        }
        return data;

    }

}
