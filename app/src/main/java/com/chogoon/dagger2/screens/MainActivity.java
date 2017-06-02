package com.chogoon.dagger2.screens;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.chogoon.dagger2.GithubApplication;
import com.chogoon.dagger2.screens.main.dagger.DaggerMainComponent;
import com.chogoon.dagger2.screens.main.dagger.MainModule;
import com.chogoon.dagger2.screens.main.mvp.MainPresenter;
import com.chogoon.dagger2.screens.main.mvp.MainView;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    MainView view;

    @Inject
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerMainComponent.builder()
                .mainModule(new MainModule(this))
                .githubApplicationComponent(GithubApplication.get(this).component())
                .build().inject(this);

        setContentView(view);

        presenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}
