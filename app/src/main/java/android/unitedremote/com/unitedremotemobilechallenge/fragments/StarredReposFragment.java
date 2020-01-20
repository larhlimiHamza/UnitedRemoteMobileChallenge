package android.unitedremote.com.unitedremotemobilechallenge.fragments;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.unitedremote.com.unitedremotemobilechallenge.R;
import android.unitedremote.com.unitedremotemobilechallenge.models.Repo;
import android.unitedremote.com.unitedremotemobilechallenge.models.Response;
import android.unitedremote.com.unitedremotemobilechallenge.services.ApiService;
import android.unitedremote.com.unitedremotemobilechallenge.util.RepoAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class StarredReposFragment extends Fragment {

    RecyclerView mRecyclerView;
    LinearLayoutManager mLinearLayoutManager;
    RepoAdapter mRepoAdapter;
    NestedScrollView mNestedScrollView;
    private static final String TAG ="StarredReposFragment";
    private Map<String, String> mOptions = new HashMap<>();
    Integer mPageValue = 1;
    LinkedList<Repo> mRepo = new LinkedList<>();
    Retrofit retrofit;
    ApiService apiService;
    ProgressBar mSpinner;
    ProgressBar mSpinnerLarge;


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_starred_repos, container, false);

        mSpinner = view.findViewById(R.id.progressBar);
        mSpinnerLarge = view.findViewById(R.id.progressBarLarge);
        //RecyclerView, NestedScroll & Adapter
        mNestedScrollView = view.findViewById(R.id.nested_scroll);
        nestedScrollListener();
        mRecyclerView = view.findViewById(R.id.repo_list);
        mRecyclerView.setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRepoAdapter = new RepoAdapter(mRepo,getContext());
        mRecyclerView.setAdapter(mRepoAdapter);

        //retrofit & ApiService
        retrofit= new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);

        // Getting Data
        getRepos();

        return view;
    }

    private void setOptions() {
        //TODO Find a scalable solution for date ( APIs bellow 26 )
        Log.d(TAG, java.time.LocalDate.now().minus(Period.ofDays(30)).toString());
        String qValue ="created:>";
        this.mOptions.put("q",qValue.concat(java.time.LocalDate.now().minus(Period.ofDays(30)).toString()));
        this.mOptions.put("sort","stars");
        this.mOptions.put("order","desc");
        this.mOptions.put("page",String.valueOf(mPageValue));
    }

    void getRepos(){
        // Setting options
        setOptions();

        // make a request by calling the corresponding method
        final Single<Response> responseSingle = apiService.getResponse(mOptions);
        responseSingle.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Response>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Response response) {
                        mRepo.addAll(response.getItems());
                        Log.d(TAG,"Size => "+mRepo.size()+"Data => "+mRepo);
                        mSpinnerLarge.setVisibility(View.GONE);
                        mSpinner.setVisibility(View.GONE);
                        mRepoAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,"Error  !");
                        e.printStackTrace();
                    }
                });
    }

    /**
     * Calls the getRepos function once the bottom is reached by the user
     */
    void nestedScrollListener(){
        mNestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView nestedScrollView, int x, int y, int oldX, int oldY) {
                if(y == nestedScrollView.getChildAt(0).getMeasuredHeight() - nestedScrollView.getMeasuredHeight()){
                    mPageValue++;
                    Log.d(TAG,"Bottom reached");
                    mSpinner.setVisibility(View.VISIBLE);
                    getRepos();
                }
            }
        });
    }
}