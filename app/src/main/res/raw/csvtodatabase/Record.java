package com.example.csvtodatabase;

public class Record {
int ayt_no,sura_no;
String arabic,jalnd,ahmedali,ahmedraza;

    public Record(int ayt_no, int sura_no, String arabic, String jalnd, String ahmedali, String ahmedraza) {
        this.ayt_no = ayt_no;
        this.sura_no = sura_no;
        this.arabic = arabic;
        this.jalnd = jalnd;
        this.ahmedali = ahmedali;
        this.ahmedraza = ahmedraza;
    }
}
