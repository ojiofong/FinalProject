package com.udacity.gradle.builditbigger;

import android.util.Log;

import com.javajoke.example.Joke;



/**
 * Created by oofong25 on 10/4/15.
 *
 */
public class MyJokeTest {



    public void testVerifyJokeResponse(){
        assert(Echo.echo("hello").equals(new Joke().getJoke()));
        Log.d("tag", "something");
    }
}
