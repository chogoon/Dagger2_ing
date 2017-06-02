package com.chogoon.dagger2.screens.main.mvp;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

/**
 * Created by chogoon on 2017-05-31.
 */

public class MainPresenter {

    private final MainView view;
    private final MainModel model;
    private final CompositeSubscription compositeSubscription = new CompositeSubscription();

    public MainPresenter(MainView view, MainModel model) {
        this.view = view;
        this.model = model;
    }

    public void onCreate(){
        compositeSubscription.add(observeButton());
        compositeSubscription.add(loadSavedState());
    }


    public void onDestroy(){
        compositeSubscription.clear();
    }

    private Subscription loadSavedState(){
        return model.getListFromSaveState().subscribe(gitHubRepoList -> view.setMessage(gitHubRepoList + ""));
    }

    public Subscription observeButton(){
        return view.observeButton()
                .doOnNext(__ -> view.showLoading(true))
                .map(__ -> view.getUserName())
                .observeOn(Schedulers.io())
                .switchMap(name -> model.getUserRepos(name))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(gitHubRepoList -> model.saveListState(gitHubRepoList))
                .doOnEach(__ -> view.showLoading(false))
                .subscribe(gitHubRepoList -> {
                    view.setMessage( "" + gitHubRepoList);
                    model.startRepoActivity(gitHubRepoList);
                });
    }


}
