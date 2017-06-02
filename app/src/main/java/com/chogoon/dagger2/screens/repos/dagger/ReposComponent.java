package com.chogoon.dagger2.screens.repos.dagger;

import com.chogoon.dagger2.GithubApplicationComponent;
import com.chogoon.dagger2.screens.MainActivity;
import com.chogoon.dagger2.screens.ReposActivity;

import dagger.Component;

/**
 * Created by chogoon on 2017-05-31.
 */
@ReposScope
@Component(modules = {ReposModule.class}, dependencies = GithubApplicationComponent.class)
public interface ReposComponent {

    void inject(ReposActivity activity);

}
