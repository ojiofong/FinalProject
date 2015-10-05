package com.udacity.gradle.builditbigger;

import android.util.Log;

import com.javajoke.example.Joke;



/**
 * Created by oofong25 on 10/4/15.
 *
 */
import org.junit.Test;

public class PaidEchoTest {

    @Test
    public void verifyEchoResponse() {
        assert Echo.echo("hello").equals("hello");
    }

//    @Test
//    public void verifyLoggingEchoResponse() {
//        assert Echo.echo("hello", true).equals("hello");
//    }
}

