package android.unitedremote.com.unitedremotemobilechallenge.fragments;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.unitedremote.com.unitedremotemobilechallenge.R;
import android.unitedremote.com.unitedremotemobilechallenge.models.Owner;
import android.unitedremote.com.unitedremotemobilechallenge.models.Repo;
import android.unitedremote.com.unitedremotemobilechallenge.util.RepoAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;


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
        mRepoAdapter = new RepoAdapter(mRepo,getContext());
        mRecyclerView.setAdapter(mRepoAdapter);

        //Fill the list
        for(int i=0;i<5;i++){
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


        return view;
    }

}