package com.chogoon.dagger2;

import android.app.Activity;
import android.app.Application;

import com.chogoon.dagger2.util.Constants;


import net.danlew.android.joda.JodaTimeAndroid;

import timber.log.Timber;

/**
 * Created by chogoon on 2017-05-31.
 */

public class GithubApplication extends Application {



    //   Activity

    //GithubService   picasso

    //retrofit    OkHttp3Downloader

    //gson      okhttp

    //      logger    cache

    //      timber           file

    private GithubApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        if(BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree(){
                @Override
                protected void log(int priority, String tag, String message, Throwable t) {
                    super.log(priority, Constants.LOGTAG, message, t);
                }
            });

        }

        JodaTimeAndroid.init(this);
        component = DaggerGithubApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .build();

    }

    public GithubApplicationComponent component(){
        return component;
    }

    public static GithubApplication get(Activity activity) {
        return (GithubApplication) activity.getApplication();
    }

}
