package com.chogoon.dagger2.screens.repos.mvp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.chogoon.dagger2.R;
import com.chogoon.dagger2.models.GithubRepo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chogoon on 2017-05-31.
 */

@SuppressLint("ViewConstructor")
public class ReposView extends FrameLayout {

    @BindView(R.id.repos_list)
    ListView listView;

    @BindView(R.id.repos_toolbar)
    Toolbar toolbar;

    private final ReposAdapter reposAdapter = new ReposAdapter();

    public ReposView(Activity activity) {
        super(activity);

        inflate(getContext(), R.layout.activity_repos, this);
        ButterKnife.bind(this);

        listView.setAdapter(reposAdapter);
    }

    public void setMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void setData(List<GithubRepo> gitHubRepoList) {
        reposAdapter.swapData(gitHubRepoList);
    }
}
