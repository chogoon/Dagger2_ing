package com.chogoon.dagger2.screens.repos.mvp;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.chogoon.dagger2.R;
import com.chogoon.dagger2.models.GithubRepo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chogoon on 2017-06-01.
 */

public class ReposListItem2 extends FrameLayout {

    @BindView(R.id.repo_name)
    TextView nameTextView;

    @BindView(R.id.repo_desc)
    TextView descTextView;

    public ReposListItem2(Context context) {
        super(context);

        inflate(getContext(), R.layout.list_item_repo2, this);
        ButterKnife.bind(this);
    }

    public void setRepo(GithubRepo gitHubRepo) {
        nameTextView.setText(gitHubRepo.name);
        descTextView.setText(gitHubRepo.description);
    }

}
