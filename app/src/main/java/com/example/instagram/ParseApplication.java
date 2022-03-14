package com.example.instagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(Post.class);
        
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("RUabcUseGciQww2WQGevRNZm2N1B5DvQwaaNtOcB")
                .clientKey("ZtrePxILYbgX3ZJChjHlXGBGiPedNW65L4TPNUsI")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
