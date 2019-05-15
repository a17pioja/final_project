package com.example.final_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class TattooDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tattoo_details);

        Intent myIntent = getIntent();

        String tID=myIntent.getStringExtra("ID");
        String tFlash=myIntent.getStringExtra("Flash");
        String tName=myIntent.getStringExtra("Name");
        String tType=myIntent.getStringExtra("Type");
        String tPrice=myIntent.getStringExtra("Price");
        String tSize=myIntent.getStringExtra("Size");
        String tShared=myIntent.getStringExtra("Shared");
        String tUsed=myIntent.getStringExtra("Used");

        ArrayAdapter<String> adapterItem;
        adapterItem = new ArrayAdapter(getApplicationContext(),R.layout.list_item_textview,
                R.id.my_item_textview);
        ListView myListView = (ListView)findViewById(R.id.myDetailListView);

        adapterItem.add("ID: " + tID);
        adapterItem.add("Flash: " + tFlash);
        adapterItem.add("Name: " + tName);
        adapterItem.add("Type: " + tType);
        adapterItem.add("Price: " + tPrice);
        adapterItem.add("Size: " + tSize);
        adapterItem.add("Shared: " + tShared);
        adapterItem.add("Used: " + tUsed);

        myListView.setAdapter(adapterItem);

    }
}
