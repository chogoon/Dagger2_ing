package com.chogoon.dagger2;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.chogoon.dagger2.network.DateTimeConverter;
import com.chogoon.dagger2.network.GithubService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
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

    private GithubService githubService;
    private Picasso picasso;

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());

        GithubApplicationComponent component = DaggerGithubApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .build();

        githubService = component.getGithubService();
        picasso = component.getPicasso();

    }

    public static GithubApplication get(Activity activity) {
        return (GithubApplication) activity.getApplication();
    }

    public GithubService getGithubService() {
        return githubService;
    }

    public Picasso getPicasso() {
        return picasso;
    }
}
