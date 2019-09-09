package com.example.prasannakumar.fireapp;

public class Artist {
    String userId;
    String plateNo;
    String from;
    String too;
    String amount;

    public Artist(){

    }

    public Artist(String userId, String plateNo, String from, String too, String amount) {
        this.userId = userId;
        this.plateNo = plateNo;
        this.from = from;
        this.too = too;
        this.amount = amount;
    }

    public String getUserId() {
        return userId;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public String getFrom() {
        return from;
    }

    public String getToo() {
        return too;
    }

    public String getAmount() {
        return amount;
    }
}

