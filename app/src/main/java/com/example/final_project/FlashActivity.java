package com.example.final_project;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
//import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.InputStream;

public class FlashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);

        Intent myIntent = getIntent();

        final String tFlashPhoto=myIntent.getStringExtra("FlashPhoto");
        final String tFlash=myIntent.getStringExtra("Flash");
        final String tShared=myIntent.getStringExtra("Shared");

        new DownloadImageTask((ImageView) findViewById(R.id.imageViewFlash)).execute(tFlashPhoto);

        ArrayAdapter<String> adapterItem;
        adapterItem = new ArrayAdapter(getApplicationContext(),R.layout.list_item_textview, R.id.my_item_textview);
        ListView myListView = (ListView)findViewById(R.id.flashListView);

        adapterItem.add("Flash: " + tFlash);
        adapterItem.add("Shared: " + tShared);

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
