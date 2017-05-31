package com.chogoon.dagger2.screens;

import android.app.Activity;

import com.chogoon.dagger2.GithubApplicationComponent;
import com.chogoon.dagger2.PicassoModule;
import com.chogoon.dagger2.network.GithubService;
import com.chogoon.dagger2.screens.home.AdapterRepos;

import dagger.Component;
import dagger.Module;

/**
 * Created by chogoon on 2017-05-31.
 */
@HomeActivityScope
@Component(modules = HomeActivityModule.class, dependencies = GithubApplicationComponent.class)
public interface HomeActivityComponent {

    void inject(HomeActivity activity);

}
