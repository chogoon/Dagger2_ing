package com.chogoon.dagger2.screens.main.dagger;

import android.app.Activity;

import com.chogoon.dagger2.network.GithubService;
import com.chogoon.dagger2.screens.main.mvp.MainModel;
import com.chogoon.dagger2.screens.main.mvp.MainPresenter;
import com.chogoon.dagger2.screens.main.mvp.MainView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chogoon on 2017-05-31.
 */
@Module
public class MainModule {

    private final Activity activity;

    public MainModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @MainScope
    public MainView view(){
        return new MainView(activity);
    }

    @Provides
    @MainScope
    public MainModel model(GithubService service){
        return new MainModel(activity, service);
    }

    @Provides
    @MainScope
    public MainPresenter mainPresenter(MainView view, MainModel model){
        return new MainPresenter(view, model);
    }


}
