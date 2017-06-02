package com.chogoon.dagger2;

import com.chogoon.dagger2.models.GithubRepo;
import com.chogoon.dagger2.screens.main.mvp.MainModel;
import com.chogoon.dagger2.screens.main.mvp.MainPresenter;
import com.chogoon.dagger2.screens.main.mvp.MainView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Created by chogoon on 2017-06-02.
 */

public class MainPresenterTest {

    private MainPresenter mainPresenter;
    private MainView mainView;
    private MainModel mainModel;

    private List<GithubRepo> mockRepos = new ArrayList<>(0);



    @Before
    public void setUp() throws Exception{
        RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidSchedulersHook(){

            @Override
            public Scheduler getMainThreadScheduler() {
                return Schedulers.immediate();
            }
        });


        mainView = Mockito.mock(MainView.class);
        mainModel = Mockito.mock(MainModel.class);

        mainPresenter = new MainPresenter(mainView, mainModel);

        Mockito.when(mainView.getUserName()).thenReturn("chogoon");
        Mockito.when(mainView.observeButton()).thenReturn(Observable.never());
        Mockito.when(mainModel.getListFromSaveState()).thenReturn(Observable.empty());
        Mockito.when(mainModel.getUserRepos(Mockito.anyString())).thenReturn(Observable.just(mockRepos));

    }

    @Test
    public void onGetReposSavedStateNoData(){
        Mockito.when(mainModel.getListFromSaveState()).thenReturn(Observable.empty());
        mainPresenter.onCreate();

        Mockito.verify(mainView, Mockito.never()).setMessage(Mockito.anyString());
    }

    @Test
    public void onGetReposSavedStateHasData(){
        Mockito.when(mainModel.getListFromSaveState()).thenReturn(Observable.just(mockRepos));
        mainPresenter.onCreate();

        Mockito.verify(mainView, Mockito.times(1)).setMessage("look up button " + mockRepos);
    }

    @Test
    public void onButtonClick(){
        Mockito.when(mainView.observeButton()).thenReturn(Observable.just(null));
        Mockito.when(mainModel.getUserRepos(Mockito.eq("chogoon")))
                .thenReturn(Observable.just(mockRepos));
        mainPresenter.onCreate();

        Mockito.verify(mainView, Mockito.times(1)).showLoading(Mockito.eq(true));
        Mockito.verify(mainView, Mockito.times(2)).showLoading(Mockito.eq(false));
        Mockito.verify(mainModel, Mockito.times(1)).saveListState(Mockito.eq(mockRepos));
        Mockito.verify(mainModel, Mockito.times(1)).startRepoActivity(Mockito.eq(mockRepos));
    }
}
