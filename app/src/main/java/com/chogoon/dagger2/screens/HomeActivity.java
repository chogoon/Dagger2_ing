package com.chogoon.dagger2.screens;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.chogoon.dagger2.GithubApplication;
import com.chogoon.dagger2.R;
import com.chogoon.dagger2.models.BaseData;
import com.chogoon.dagger2.models.GithubRepo;
import com.chogoon.dagger2.models.ItemData;
import com.chogoon.dagger2.network.GithubService;
import com.chogoon.dagger2.screens.home.AdapterRepos;
import com.chogoon.dagger2.screens.home.dagger.DaggerHomeActivityComponent;
import com.chogoon.dagger2.screens.home.dagger.HomeActivityComponent;
import com.chogoon.dagger2.screens.home.dagger.HomeActivityModule;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;

public class HomeActivity extends AppCompatActivity {

  @BindView(R.id.repo_home_list)
  ListView listView;

  @Inject
  GithubService githubService;

  Call<List<GithubRepo>> reposCall;
  Call<BaseData> listCall;

  @Inject
  DatabaseReference database;

  @Inject
  AdapterRepos adapterRepos;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
    ButterKnife.bind(this);

    DaggerHomeActivityComponent.builder()
            .homeActivityModule(new HomeActivityModule(this))
            .githubApplicationComponent(GithubApplication.get(this).component())
            .build().inject(this);

    listView.setAdapter(adapterRepos);

//    reposCall = githubService.getAllRepos();
//    reposCall.enqueue(new Callback<List<GithubRepo>>() {
//        @Override
//        public void onResponse(Call<List<GithubRepo>> call, Response<List<GithubRepo>> response) {
////          adapterRepos.swapData(response.body());
//        }
//
//        @Override
//        public void onFailure(Call<List<GithubRepo>> call, Throwable t) {
//          Toast.makeText(HomeActivity.this, "Error getting repos " + t.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//      });


    database.child("items").addChildEventListener(new ChildEventListener() {
      @Override
      public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        Log.d("chogoon", "onChildAdded:" + dataSnapshot.getKey() + " / " + s);
        adapterRepos.addData(dataSnapshot.getValue(ItemData.class));
      }

      @Override
      public void onChildChanged(DataSnapshot dataSnapshot, String s) {
        Log.d("chogoon", "onChildChanged:" + dataSnapshot.getKey() + " / " + s);

        adapterRepos.swapData(dataSnapshot.getValue(ItemData.class));
      }

      @Override
      public void onChildRemoved(DataSnapshot dataSnapshot) {
        Log.d("chogoon", "onChildRemoved:" + dataSnapshot.getKey());
        adapterRepos.removeData(dataSnapshot.getValue(ItemData.class));
      }

      @Override
      public void onChildMoved(DataSnapshot dataSnapshot, String s) {
        Log.d("chogoon", "onChildMoved:" + dataSnapshot.getKey() + " / " + s);
      }

      @Override
      public void onCancelled(DatabaseError databaseError) {
        Log.d("chogoon", "onCancelled:" + databaseError.getMessage() );
      }
    });



//    listCall = githubService.getList();
//    listCall.enqueue(new Callback<BaseData>() {
//      @Override
//      public void onResponse(Call<BaseData> call, Response<BaseData> response) {
//        Log.e("chogoon", "onResponse");
//        List<ItemData> items = new ArrayList<ItemData>();
//        BaseData base = response.body();
//        for (GroupData group : base.data) {
//          for (ItemData item : group.items) {
//            database.child("items").child(String.valueOf(item.id)).setValue(item);
//            items.add(item);
//          }
//        }
//        Log.e("chogoon", "list.size " + items.size());
//        adapterRepos.swapData(items);
//      }
//
//      @Override
//      public void onFailure(Call<BaseData> call, Throwable t) {
//        Toast.makeText(HomeActivity.this, "Error getting repos " + t.getMessage(), Toast.LENGTH_SHORT).show();
//      }
//    });
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    if(reposCall != null) {
      reposCall.cancel();
    }
  }
}
