package com.chogoon.dagger2.screens;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.chogoon.dagger2.GithubApplication;
import com.chogoon.dagger2.models.GithubRepo;
import com.chogoon.dagger2.screens.repos.dagger.DaggerReposComponent;
import com.chogoon.dagger2.screens.repos.dagger.ReposModule;
import com.chogoon.dagger2.screens.repos.mvp.ReposPresenter;
import com.chogoon.dagger2.screens.repos.mvp.ReposView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ReposActivity extends AppCompatActivity {

    public static final String INTENT_DATA_REPOS = "INTENT_DATA_REPOS";

    @Inject
    ReposView view;

    @Inject
    ReposPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerReposComponent.builder()
                .reposModule(new ReposModule(this))
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

    public static void start(Context context, List<GithubRepo> list){
        Intent intent = new Intent(context, ReposActivity.class);
        intent.putExtra(INTENT_DATA_REPOS, new ArrayList<>(list));
        context.startActivity(intent);

    }
}
