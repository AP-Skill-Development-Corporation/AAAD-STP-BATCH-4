package com.example.apssdc.firebasedb;

public class MyModel {
    String sname,sroll,snumber;

    public String getSname() {
        return sname;
    }

    public String getSroll() {
        return sroll;
    }

    public String getSnumber() {
        return snumber;
    }

    public MyModel(String sname, String sroll, String snumber) {
        this.sname = sname;
        this.sroll = sroll;
        this.snumber = snumber;
    }

    public MyModel() {
    }
}
