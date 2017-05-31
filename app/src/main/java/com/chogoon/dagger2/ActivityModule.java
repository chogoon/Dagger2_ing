package com.chogoon.dagger2;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chogoon on 2017-05-31.
 */
@Module
public class ActivityModule {

    private final Activity context;

    public ActivityModule(Activity context) {
        this.context = context;
    }

    @Provides
    @GithubApplicationScope
    public Context context(){
        return context;
    }
}
