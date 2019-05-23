package com.example.final_project;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.InputStream;

public class TattooDetailsActivity extends AppCompatActivity {

    ImageButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tattoo_details);

        final Intent myIntent = getIntent();

        String tPhoto=myIntent.getStringExtra("Photo");
        String tID=myIntent.getStringExtra("ID");
        final String tFlash=myIntent.getStringExtra("Flash");
        final String tFlashPhoto=myIntent.getStringExtra("FlashPhoto");
        String tName=myIntent.getStringExtra("Name");
        String tType=myIntent.getStringExtra("Type");
        String tPrice=myIntent.getStringExtra("Price");
        String tSize=myIntent.getStringExtra("Size");
        final String tShared=myIntent.getStringExtra("Shared");
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

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent myIntentFlash = new Intent(getApplicationContext(), FlashActivity.class);
                if(position==1){

                    myIntentFlash.putExtra("FlashPhoto", tFlashPhoto);
                    myIntentFlash.putExtra("Flash",tFlash);
                    myIntentFlash.putExtra("Shared",tShared);
                    startActivity(myIntentFlash);
                }

                else{
                    //do nothing
                }
            }
        });


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

    //button
    /*
    public void addListenerOnButton() {

        button = (ImageButton) findViewById(R.id.imageButton);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "", null));
                startActivity(intent);

            }

        });


    }
    */
}
