package com.example.final_project;

public class Tattoo {
    private String ID;
    private String flash;
    private String name;
    private String type;
    private int price;
    private String size;
    private boolean shared;
    private int used;


    public Tattoo(String inID, String inFlash, String inName, String inType, int inPrice, String inSize, boolean inShared, int inUsed){
        ID = inID;
        flash = inFlash;
        name = inName;
        type = inType;
        price = inPrice;
        size = inSize;
        shared = inShared;
        used = inUsed;

    }

    @Override
    public String toString() {
        return name;
    }
}
