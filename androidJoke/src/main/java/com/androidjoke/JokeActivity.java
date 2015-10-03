package com.androidjoke;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by oofong25 on 10/3/15.
 *
 */
public class JokeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
      //  setTitle(R.string.app_name);

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            ((TextView)findViewById(R.id.joke_tv)).setText(bundle.getString("joke_key"));
        }
    }
}
