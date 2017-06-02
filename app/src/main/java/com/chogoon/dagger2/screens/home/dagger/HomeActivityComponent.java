package com.chogoon.dagger2.screens.home.dagger;

import com.chogoon.dagger2.GithubApplicationComponent;
import com.chogoon.dagger2.screens.HomeActivity;

import dagger.Component;

/**
 * Created by chogoon on 2017-05-31.
 */
@HomeActivityScope
@Component(modules = HomeActivityModule.class, dependencies = GithubApplicationComponent.class)
public interface HomeActivityComponent {

    void inject(HomeActivity activity);

}
