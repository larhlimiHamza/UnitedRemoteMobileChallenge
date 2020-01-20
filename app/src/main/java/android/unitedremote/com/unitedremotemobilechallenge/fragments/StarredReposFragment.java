package android.unitedremote.com.unitedremotemobilechallenge.fragments;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.unitedremote.com.unitedremotemobilechallenge.R;
import android.unitedremote.com.unitedremotemobilechallenge.models.Owner;
import android.unitedremote.com.unitedremotemobilechallenge.models.Repo;
import android.unitedremote.com.unitedremotemobilechallenge.services.ApiService;
import android.unitedremote.com.unitedremotemobilechallenge.util.RepoAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class StarredReposFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private RepoAdapter mRepoAdapter;
    private static final String TAG ="StarredReposFragment";


    LinkedList<Repo> mRepo = new LinkedList<>();

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_starred_repos, container, false);

        //RecyclerView & NestedScroll
        mRecyclerView = view.findViewById(R.id.repo_list);
        mRecyclerView.setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRepoAdapter = new RepoAdapter(mRepo, getContext());
        mRecyclerView.setAdapter(mRepoAdapter);

        //Fill the list
        /*for(int i=0;i<5;i++){
            mRepo.add(new Repo(
                    "repo"+i,
                    "repo_desc"+i,
                    new Owner(
                            "owner"+i,
                            "avatar_url"+i
                    ),
                    i*300+1000
            ));
        }
        mRepoAdapter.notifyDataSetChanged();
        */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        // create an instance of the ApiService
        ApiService apiService = retrofit.create(ApiService.class);

        // make a request by calling the corresponding method
        Single<Repo> repos = apiService.getListRepos();
        repos.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Repo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Repo repos) {
                        mRepo.add(repos);
                        mRepoAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });

        return view;
    }
}