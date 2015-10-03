package com.androidjoke;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import millennialmedia.androidjoke.R;

/**
 * Created by oofong25 on 10/3/15.
 *
 */
public class JokeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            ((TextView)findViewById(R.id.joke_tv)).setText(bundle.getString("joke_key"));
        }
    }
}
