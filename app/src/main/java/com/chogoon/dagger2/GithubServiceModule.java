package com.chogoon.dagger2;

import com.chogoon.dagger2.network.DateTimeConverter;
import com.chogoon.dagger2.network.GithubService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.joda.time.DateTime;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chogoon on 2017-05-31.
 */

@Module(includes = NetworkModule.class)
public class GithubServiceModule {

    @Provides
    @GithubApplicationScope
    public GithubService githubService(Retrofit retrofit){
        return retrofit.create(GithubService.class);
    }

    @Provides
    public Gson gson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(DateTime.class, new DateTimeConverter());
        return gsonBuilder.create();
    }

    @Provides
    public Retrofit retrofit(OkHttpClient okHttpClient, Gson gson){
         return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                 .client(okHttpClient)
                .baseUrl("https://api3.goodchoice.kr/")
                .build();
    }


}
