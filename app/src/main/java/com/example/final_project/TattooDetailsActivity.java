package com.example.final_project;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.InputStream;

public class TattooDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tattoo_details);

        Intent myIntent = getIntent();

        String tPhoto=myIntent.getStringExtra("Photo");
        String tID=myIntent.getStringExtra("ID");
        String tFlash=myIntent.getStringExtra("Flash");
        String tName=myIntent.getStringExtra("Name");
        String tType=myIntent.getStringExtra("Type");
        String tPrice=myIntent.getStringExtra("Price");
        String tSize=myIntent.getStringExtra("Size");
        String tShared=myIntent.getStringExtra("Shared");
        String tUsed=myIntent.getStringExtra("Used");

        new DownloadImageTask((ImageView) findViewById(R.id.imageView)).execute(tPhoto);



        ArrayAdapter<String> adapterItem;
        adapterItem = new ArrayAdapter(getApplicationContext(),R.layout.list_item_textview, R.id.my_item_textview);
        ListView myListView = (ListView)findViewById(R.id.myDetailListView);

        //adapterItem.add("ID: " + tID);
        adapterItem.add("Name: " + tName);
        adapterItem.add("Flash: " + tFlash);
        adapterItem.add("Type: " + tType);
        adapterItem.add("Price: " + tPrice + "USD");
        adapterItem.add("Size: " + tSize);
        adapterItem.add("Shared: " + tShared);
        adapterItem.add("Used: " + tUsed);

        myListView.setAdapter(adapterItem);
    }

    public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public DownloadImageTask(ImageView imageView) {
            this.imageView = imageView;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }
}
