package com.chogoon.dagger2.screens;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.chogoon.dagger2.GithubApplication;
import com.chogoon.dagger2.R;
import com.chogoon.dagger2.models.GithubRepo;
import com.chogoon.dagger2.network.GithubService;
import com.chogoon.dagger2.screens.home.AdapterRepos;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

  @BindView(R.id.repo_home_list)
  ListView listView;

  GithubService githubService;

  Call<List<GithubRepo>> reposCall;

  Picasso picasso;
  AdapterRepos adapterRepos;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
    ButterKnife.bind(this);
    githubService = GithubApplication.get(this).getGithubService();
    picasso = GithubApplication.get(this).getPicasso();

    adapterRepos = new AdapterRepos(this, picasso);
    listView.setAdapter(adapterRepos);


    reposCall = githubService.getAllRepos();
    reposCall.enqueue(new Callback<List<GithubRepo>>() {
        @Override
        public void onResponse(Call<List<GithubRepo>> call, Response<List<GithubRepo>> response) {
          adapterRepos.swapData(response.body());
        }

        @Override
        public void onFailure(Call<List<GithubRepo>> call, Throwable t) {
          Toast.makeText(HomeActivity.this, "Error getting repos " + t.getMessage(), Toast.LENGTH_SHORT).show();
        }
      });
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    if(reposCall != null) {
      reposCall.cancel();
    }
  }
}
