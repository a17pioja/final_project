package com.example.final_project;

import android.media.Image;

public class Tattoo {
    private String photo;
    private String ID;
    private String flash;
    private String flashPhoto;
    private String name;
    private String type;
    private int price;
    private String size;
    private boolean shared;
    private int used;


    public Tattoo(String inPhoto,String inID, String inFlash, String inFlashPhoto, String inName, String inType, int inPrice, String inSize, boolean inShared, int inUsed){
        photo = inPhoto;
        ID = inID;
        flash = inFlash;
        flashPhoto = inFlashPhoto;
        name = inName;
        type = inType;
        price = inPrice;
        size = inSize;
        shared = inShared;
        used = inUsed;

    }

    public String getPhoto(){
        String str=photo;
        return str;
    }

    public String getID(){
        String str=ID;
        return str;
    }

    public String getFlash(){
        String str=flash;
        return str;
    }

    public String getFlashPhoto(){
        String str=flashPhoto;
        return str;
    }

    public String getName(){
        String str=name;
        return str;
    }

    public String getType(){
        String str=type;
        return str;
    }

    public String getPrice(){
        int n=price;
        String str=Integer.toString(n);
        return str;
    }

    public String getSize(){
        String str=size;
        return str;
    }

    public String getShared(){
        boolean b=shared;
        String str=Boolean.toString(b);
        return str;
    }

    public String getUsed(){
        int n=used;
        String str=Integer.toString(n);
        return str;
    }


    @Override
    public String toString() {
        return name;
    }
}
