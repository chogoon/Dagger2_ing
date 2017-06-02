package com.chogoon.dagger2.screens.repos.mvp;

import android.app.Activity;

import com.chogoon.dagger2.models.GithubRepo;
import com.chogoon.dagger2.network.GithubService;
import com.twistedequations.rxstate.RxSaveState;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

import static com.chogoon.dagger2.screens.ReposActivity.INTENT_DATA_REPOS;

/**
 * Created by chogoon on 2017-06-01.
 * only data
 */

public class ReposModel {

    private final Activity activity;

    public ReposModel(Activity activity) {
        this.activity = activity;
    }

    public List<GithubRepo> githubRepoIntent(){
        return activity.getIntent().getParcelableArrayListExtra(INTENT_DATA_REPOS);
    }

}
