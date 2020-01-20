package android.unitedremote.com.unitedremotemobilechallenge.util;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.unitedremote.com.unitedremotemobilechallenge.R;
import android.unitedremote.com.unitedremotemobilechallenge.models.Repo;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.LinkedList;


public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.MyViewAdapter> {

    private LinkedList<Repo> mRepos;
    private static final String TAG ="RepoAdapter";
    Context mContext;

    public RepoAdapter(LinkedList<Repo> mRepos, Context mContext) {
        this.mRepos = mRepos;
        this.mContext = mContext;
    }

    @Override
    public MyViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Create a new view ( item_repo file )
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_repo, parent, false);
        return new MyViewAdapter(v, mContext);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewAdapter holder, final int position) {
        //Replacing the contents of the view
        holder.repoName.setText(mRepos.get(position).getName());
        holder.repoDescription.setText(mRepos.get(position).getDescription());
        holder.ownerName.setText(mRepos.get(position).getOwner().getLogin());
        //TODO Load the avatar
        holder.repoStarsNumber.setText( StarsConverter.convert( mRepos.get(position).getStargazersCount() ) );

        //onClickListener
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent & Bundle
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.mRepos.size();
    }

    public static class MyViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView repoName;
        private TextView repoDescription;
        private TextView ownerName;
        private ImageView ownerAvatar;
        private TextView repoStarsNumber;

        private Context context;
        private RelativeLayout parentLayout;


        private MyViewAdapter(@NonNull View v, Context context) {
            super(v);
            this.context = context;

            //initialize views
            parentLayout = v.findViewById(R.id.parent_layout_repo);
            repoName = v.findViewById(R.id.repo_name);
            repoDescription = v.findViewById(R.id.repo_description);
            ownerName = v.findViewById(R.id.repo_owner_name);
            ownerAvatar = v.findViewById(R.id.repo_owner_avatar);
            repoStarsNumber = v.findViewById(R.id.repo_stars_number);

        }

        @Override
        public void onClick(View v) {

        }
    }
}