package com.example.final_project;

import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<Tattoo> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new FetchData().execute();

        adapter = new ArrayAdapter(getApplicationContext(),R.layout.list_item_textview,
                R.id.my_item_textview);

        ListView myListView = (ListView)findViewById(R.id.myListView);
        myListView.setAdapter(adapter);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent myIntent = new Intent(getApplicationContext(), TattooDetailsActivity.class);

                Tattoo t=adapter.getItem(position);
                myIntent.putExtra("Photo",t.getPhoto());
                myIntent.putExtra("ID",t.getID());
                myIntent.putExtra("Flash",t.getFlash());
                myIntent.putExtra("FlashPhoto",t.getFlashPhoto());
                myIntent.putExtra("Name",t.getName());
                myIntent.putExtra("Type",t.getType());
                myIntent.putExtra("Price",t.getPrice());
                myIntent.putExtra("Size",t.getSize());
                myIntent.putExtra("Shared",t.getShared());
                myIntent.putExtra("Used",t.getUsed());
                startActivity(myIntent);
            }
        });

    }
    private class FetchData extends AsyncTask<Void,Void,String> {

        @Override
        protected void onPostExecute(String o) {
            super.onPostExecute(o);
            Log.d("blaxie","DataFetched");
            // This code executes after we have received our data. The String object o holds
            // the un-parsed JSON string or is null if we had an IOException during the fetch.

            try {
                JSONArray json1 = new JSONArray(o);
                Log.d("blaxie","I'm here");
                int i=json1.length();
                for (int x = 0; x < i; x++){
                    JSONObject jsonOb = json1.getJSONObject(x);
                    Log.d("jsontest",jsonOb.getString("ID"));
                    Log.d("jsontest",jsonOb.getString("name"));

                    String photo = jsonOb.getString("photo");
                    String ID = jsonOb.getString("ID");
                    String flash = jsonOb.getString("flash");
                    String flashPhoto = jsonOb.getString("flashPhoto");
                    String name = jsonOb.getString("name");
                    String type = jsonOb.getString("type");
                    String priceStr = jsonOb.getString("price");
                    int price = Integer.parseInt(priceStr);
                    String size = jsonOb.getString("size");
                    String sharedStr = jsonOb.getString("shared");
                    boolean shared = Boolean.parseBoolean(sharedStr);
                    String usedStr = jsonOb.getString("used");
                    int used = Integer.parseInt(usedStr);

                    Tattoo t = new Tattoo(photo, ID, flash, flashPhoto, name, type, price, size, shared, used);
                    adapter.add(t);
                }




            } catch (JSONException e) {
                e.printStackTrace();
            }

            // Implement a parsing code that loops through the entire JSON and creates objects
        }


        @Override
        protected String doInBackground(Void... params) {
            // These two variables need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a Java string.
            String jsonStr = null;



            try {
                // Construct the URL for the Internet service
                URL url = new URL("http://wwwlab.iit.his.se/a17pioja/android_project.json");

                // Create the request to the PHP-service, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                jsonStr = buffer.toString();
                return jsonStr;
            } catch (IOException e) {
                Log.e("PlaceholderFragment", "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in
                // attempting to parse it.
                return null;
            } finally{
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("Network error", "Error closing stream", e);
                    }
                }
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            Intent myIntent = new Intent(getApplicationContext(), AboutActivity.class);
            startActivity(myIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
