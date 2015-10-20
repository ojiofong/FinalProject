package com.example.oofong25.myapplication.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.api.users.User;
import com.javajoke.example.Joke;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.example.oofong25.myapplication.backend.OfyService.ofy;

/**
 * Created by oofong25 on 10/3/15.
 */

@Api(
        name = "jokeApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.oofong25.example.com",
                ownerName = "backend.myapplication.oofong25.example.com",
                packagePath = ""
        )
)
public class JokeEndpoint {
    private static final Logger log = Logger.getLogger(JokeEndpoint.class.getName());

//    @ApiMethod(name = "tellAJoke")
//    public CollectionResponse<String> tellAJoke(@Named("joke") String joke) {
//        JokeRecord jokeRecord = new JokeRecord();
//        jokeRecord.setJoke(joke);
//        ofy().save().entity(jokeRecord).now();
//    }

    @ApiMethod(name = "postJoke")
    public void postJoke(@Named("keyJoke") String key, @Named("joke") String joke) {
        JokeRecord jokeRecord = new JokeRecord();
        jokeRecord.setKeyJoke(key);
        jokeRecord.setJoke(joke);
        ofy().save().entity(jokeRecord).now();
    }

    // Require authentication for getJokeCollection method only
    //For every method that you want to authorize for, we need to add a User parameter
    // at the end as we have done below. If the user is authorized correctly,
    // an instance of the com.google.appengine.api.users.User  class will be passed in here.
    @ApiMethod(
            name = "getJokeCollectionSecure",
            scopes = {Constants.EMAIL_SCOPE},
            clientIds = {Constants.WEB_CLIENT_ID, Constants.ANDROID_CLIENT_ID, com.google.api.server.spi.Constant.API_EXPLORER_CLIENT_ID},
            audiences = {Constants.ANDROID_AUDIENCE}
    )
    public CollectionResponse<String> getJokeCollectionSecure(@Named("keyJoke") String keyJoke, User user) throws UnauthorizedException {

        if (user == null) throw new UnauthorizedException("User is not valid. Piss off!");

        JokeRecord jokeRecord = ofy().load().type(JokeRecord.class).filter("keyJoke", keyJoke).first().now();
        List<String> jokes = new ArrayList<>();
        jokes.add(jokeRecord.getJoke());
        return CollectionResponse.<String>builder().setItems(jokes).build();

    }



    @ApiMethod(name = "getJoke")
    public JokeRecord getJoke(@Named("keyJoke") String keyJoke) {
        JokeRecord jokeRecord = ofy().load().type(JokeRecord.class).filter("keyJoke", keyJoke).first().now();
        return jokeRecord;

    }

    @ApiMethod(name = "tellAJoke")
    public JokeRecord tellAJoke() {
        JokeRecord jokeRecord = new JokeRecord();
        jokeRecord.setJoke(new Joke().getJoke()); // Pull joke from java library
        return jokeRecord;

    }
}
