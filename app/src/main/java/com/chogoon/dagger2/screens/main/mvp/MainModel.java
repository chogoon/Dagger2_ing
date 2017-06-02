package com.chogoon.dagger2.screens.main.mvp;

import android.app.Activity;

import com.chogoon.dagger2.models.GithubRepo;
import com.chogoon.dagger2.network.GithubService;
import com.chogoon.dagger2.screens.ReposActivity;
import com.twistedequations.rxstate.RxSaveState;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by chogoon on 2017-06-01.
 * only data
 */

public class MainModel {

    public static final String LIST_STATE_KEY = "LIST_STATE_KEY";
    private final Activity activity;
    private final GithubService service;

    public MainModel(Activity activity, GithubService service) {
        this.activity = activity;
        this.service = service;
    }

    public Observable<List<GithubRepo>> getUserRepos(String name){
        return service.getReposForUser(name);
    }

    public void saveListState(List<GithubRepo> list){
        RxSaveState.updateSaveState(activity, bundle -> {
            bundle.putParcelableArrayList(LIST_STATE_KEY, new ArrayList<>(list));
        });
    }

    public Observable<List<GithubRepo>> getListFromSaveState(){
        return RxSaveState.getSavedState(activity).map(bundle -> bundle.getParcelableArrayList(LIST_STATE_KEY));
    }

    public void startRepoActivity(List<GithubRepo> list){
        ReposActivity.start(activity, list);

    }
}
