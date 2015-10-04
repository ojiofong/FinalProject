package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.androidjoke.JokeActivity;
import com.example.oofong25.myapplication.backend.jokeApi.JokeApi;
import com.example.oofong25.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.javajoke.example.Joke;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view){
        Toast.makeText(this, new Joke().getJoke(), Toast.LENGTH_SHORT).show();    // Joke from java library
        new EndpointsAsyncTask().execute(); // Get joke from GCE and start android lib activity
    }


    private  class EndpointsAsyncTask extends AsyncTask<Void, Void, String>{

      //  private   MyApi myApiService = null;
        private JokeApi myApiService = null;


        @Override
        protected String doInBackground(Void... params) {

           String output = null;

            if (myApiService==null){ //do this once
                JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null);
                myApiService = builder.build();
            }

            try {
                output = myApiService.tellAJoke().execute().getJoke();
                //output = myApiService.sayHi("James").execute().getData().toString();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return output;
        }

        @Override
        protected void onPostExecute(String output) {
            super.onPostExecute(output);
            //start activity with joke retrieved from GCE
            Intent intent = new Intent(getApplicationContext(), JokeActivity.class);
            intent.putExtra("joke_key", output);
            startActivity(intent);
        }
    }


    
}
