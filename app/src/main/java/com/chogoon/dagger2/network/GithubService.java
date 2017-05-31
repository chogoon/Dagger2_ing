package com.chogoon.dagger2.network;


import com.chogoon.dagger2.models.BaseData;
import com.chogoon.dagger2.models.GithubRepo;
import com.chogoon.dagger2.models.GithubUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubService {

  @GET("users/{username}/repos")
  Call<List<GithubRepo>> getReposForUser(@Path("username") String username);

  @GET("repositories")
  Call<List<GithubRepo>> getAllRepos();

  @GET("users/{username}")
  Call<GithubUser> getUser(@Path("username") String username);

  @GET("/v1/ads?arino=3006&adcno=3&udlat=37.47700320704257&sel_date2=2017-06-01&sel_date=2017-05-31&udlng=126.89790889620781&promotion=N&reserve=0%2C0&sort=HIT")
  Call<BaseData> getList();
}
