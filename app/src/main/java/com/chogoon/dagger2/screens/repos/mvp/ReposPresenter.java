package com.chogoon.dagger2.screens.repos.mvp;

import com.chogoon.dagger2.screens.main.mvp.MainModel;
import com.chogoon.dagger2.screens.main.mvp.MainView;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by chogoon on 2017-05-31.
 */

public class ReposPresenter {

    private final ReposView view;
    private final ReposModel model;
    private final CompositeSubscription compositeSubscription = new CompositeSubscription();

    public ReposPresenter(ReposView view, ReposModel model) {
        this.view = view;
        this.model = model;
    }

    public void onCreate(){
        setIntentData();
    }


    public void onDestroy(){
        compositeSubscription.clear();
    }


    private void setIntentData(){
        view.setData(model.githubRepoIntent());
    }


}
