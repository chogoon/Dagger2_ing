package com.chogoon.dagger2.screens.main.dagger;

import com.chogoon.dagger2.GithubApplicationComponent;
import com.chogoon.dagger2.screens.MainActivity;

import dagger.Component;

/**
 * Created by chogoon on 2017-05-31.
 */
@MainScope
@Component(modules = {MainModule.class}, dependencies = GithubApplicationComponent.class)
public interface MainComponent {

    void inject(MainActivity activity);

}
