package com.example.oofong25.myapplication.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;


/**
 * Created by oofong25 on 10/3/15.
 *
 */

@Entity
public class JokeRecord {

    @Id
    Long id;

    @Index
    private String keyJoke;

    private String joke;

    public JokeRecord(){
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }


    public String getKeyJoke() {
        return keyJoke;
    }

    public void setKeyJoke(String keyJoke) {
        this.keyJoke = keyJoke;
    }
}
