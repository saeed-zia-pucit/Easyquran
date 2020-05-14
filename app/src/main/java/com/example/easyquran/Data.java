package com.example.easyquran;

public class Data {
    int ayt_no,surat_no;
    String Arabic,ahmedali,ahmedraza,jaland;

    public Data() {
    }

    public Data(int surat_no,int ayt_no, String arabic, String ahmedali, String ahmedraza, String jaland) {
        this.ayt_no = ayt_no;
        Arabic = arabic;
        this.ahmedali = ahmedali;
        this.ahmedraza = ahmedraza;
        this.jaland = jaland;
        this.surat_no=surat_no;
    }
}
