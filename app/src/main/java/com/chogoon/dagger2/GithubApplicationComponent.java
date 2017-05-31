package com.chogoon.dagger2;

import com.chogoon.dagger2.network.GithubService;
import com.squareup.picasso.Picasso;

import dagger.Component;

/**
 * Created by chogoon on 2017-05-31.
 */

@Component(modules = {GithubServiceModule.class, PicassoModule.class })
public interface GithubApplicationComponent {

    Picasso getPicasso();

    GithubService getGithubService();



}
