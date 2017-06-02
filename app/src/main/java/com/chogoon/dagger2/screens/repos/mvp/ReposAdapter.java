package com.chogoon.dagger2.screens.repos.mvp;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.chogoon.dagger2.models.GithubRepo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chogoon on 2017-06-01.
 */

public class ReposAdapter extends BaseAdapter {

    private final List<GithubRepo> repoList = new ArrayList<>(0);

    @Override
    public int getCount() {
        return repoList.size();
    }

    @Override
    public GithubRepo getItem(int position) {
        return repoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return repoList.get(position).id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ReposListItem2 listItem;

        if (convertView == null) {
            listItem = new ReposListItem2(parent.getContext());
        } else {
            listItem = ReposListItem2.class.cast(convertView);
        }
        listItem.setRepo(repoList.get(position));
        return listItem;
    }

    public void swapData(List<GithubRepo> repoList) {
        this.repoList.clear();
        if (repoList != null && !repoList.isEmpty()) {
            this.repoList.addAll(repoList);
        }
        notifyDataSetChanged();
    }
}