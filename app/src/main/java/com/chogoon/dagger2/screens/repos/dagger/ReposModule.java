package com.chogoon.dagger2.screens.repos.dagger;

import android.app.Activity;

import com.chogoon.dagger2.network.GithubService;
import com.chogoon.dagger2.screens.main.mvp.MainModel;
import com.chogoon.dagger2.screens.main.mvp.MainPresenter;
import com.chogoon.dagger2.screens.main.mvp.MainView;
import com.chogoon.dagger2.screens.repos.mvp.ReposModel;
import com.chogoon.dagger2.screens.repos.mvp.ReposPresenter;
import com.chogoon.dagger2.screens.repos.mvp.ReposView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chogoon on 2017-05-31.
 */
@Module
public class ReposModule {

    private final Activity activity;

    public ReposModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @ReposScope
    public ReposView view(){
        return new ReposView(activity);
    }

    @Provides
    @ReposScope
    public ReposModel model(){
        return new ReposModel(activity);
    }

    @Provides
    @ReposScope
    public ReposPresenter presenter(ReposView view, ReposModel model){
        return new ReposPresenter(view, model);
    }


}
