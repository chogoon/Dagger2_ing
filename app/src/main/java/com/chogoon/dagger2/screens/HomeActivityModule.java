package com.chogoon.dagger2.screens;

import android.app.Activity;

import com.chogoon.dagger2.screens.home.AdapterRepos;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chogoon on 2017-05-31.
 */
@Module
public class HomeActivityModule {

    private final Activity activity;

    public HomeActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @HomeActivityScope
    public Activity activity(){
        return activity;
    }

    @Provides
    @HomeActivityScope
    public DatabaseReference database(){
        return FirebaseDatabase.getInstance().getReference();
    }
//    @Provides
//    @HomeActivityScope
//    public AdapterRepos adapterRepos(Picasso picasso){
//        return new AdapterRepos(activity, picasso);
//    }


}
