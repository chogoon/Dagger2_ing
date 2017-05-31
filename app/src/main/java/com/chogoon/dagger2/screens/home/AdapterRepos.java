package com.chogoon.dagger2.screens.home;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.chogoon.dagger2.models.GithubRepo;
import com.chogoon.dagger2.models.ItemData;
import com.chogoon.dagger2.screens.HomeActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class AdapterRepos extends BaseAdapter {

  private final List<ItemData> list = new ArrayList<>(0);
  private final Picasso picasso;
  private final Context context;

  @Inject
  public AdapterRepos(Activity context, Picasso picasso) {
    this.context = context;
    this.picasso = picasso;
  }

  @Override
  public int getCount() {
    return list.size();
  }

  @Override
  public ItemData getItem(int position) {
    return list.get(position);
  }

  @Override
  public boolean hasStableIds() {
    return true;
  }

  @Override
  public long getItemId(int position) {
    return list.get(position).id;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    RepoListItem repoListItem;
    if(convertView == null) {
      repoListItem = new RepoListItem(context, picasso);
    } else {
      repoListItem = (RepoListItem) convertView;
    }

    repoListItem.setRepo(list.get(position));

    return repoListItem;
  }

  public void swapData(Collection<ItemData> githubRepos) {
    list.clear();
    if(githubRepos != null) {
      list.addAll(githubRepos);
    }
    notifyDataSetChanged();
  }

  public void addData(ItemData data) {
    list.add(data);
//    Collections.sort(list);
    notifyDataSetChanged();
  }

  public void swapData(ItemData data) {
    int position = list.indexOf(data);
    if(list.remove(data)){
      list.add(position, data);
    }
    notifyDataSetChanged();
  }

  public void removeData(ItemData data) {
    list.remove(data);
    notifyDataSetChanged();
  }

}
