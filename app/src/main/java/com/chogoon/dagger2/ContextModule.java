package com.chogoon.dagger2;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chogoon on 2017-05-31.
 */
@Module
public class ContextModule {

    private final Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context context(){
        return context;
    }
}
