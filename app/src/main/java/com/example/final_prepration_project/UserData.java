package com.example.final_prepration_project;

public class UserData {
    String Sname,Scity,Sgender,game;

    int ageInt;

    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        Sname = sname;
    }

    public String getScity() {
        return Scity;
    }

    public String getSgender() {
        return Sgender;
    }

    public void setSgender(String sgender) {
        Sgender = sgender;
    }

    public void setScity(String scity) {
        Scity = scity;
    }

    public int getAgeInt() {
        return ageInt;
    }

    public void setAgeInt(int ageInt) {
        this.ageInt = ageInt;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    UserData(){


    }

    UserData(String n , String c , int a, String s,String g){

        this.Sname = n;
        this.Scity = c;
        this.ageInt = a;
        this.Sgender = s;
        this.game = g;
    }


}
