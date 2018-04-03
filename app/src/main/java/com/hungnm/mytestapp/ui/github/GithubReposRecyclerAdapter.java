package com.hungnm.mytestapp.ui.github;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hungnm.mytestapp.R;
import com.hungnm.mytestapp.data.models.github.GithubRepos;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * The {@link RecyclerView.Adapter} that renders and populates each repos
 * in the repos list.
 */
public class GithubReposRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private List<GithubRepos> lstRepos;
    private Fragment fragment;

    public GithubReposRecyclerAdapter(Fragment fragment, List<GithubRepos> repos) {
        this.fragment = fragment;
        this.lstRepos = repos;
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvOwnerName)
        TextView tvOwnerName;

        @BindView(R.id.ivPhoto)
        ImageView ivPhoto;

        public HeaderViewHolder(View headerView) {
            super(headerView);
            ButterKnife.bind(this, headerView);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvName)
        TextView tvName;

        @BindView(R.id.tvDesc)
        TextView tvDesc;

        @BindView(R.id.tvStarCount)
        TextView tvStarCount;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = null;
        if (viewType == TYPE_ITEM) {
            //inflate your layout and pass it to view holder
            view = inflater.inflate(R.layout.item_repos, parent, false);
        } else if (viewType == TYPE_HEADER) {
            //inflate your layout and pass it to view holder
            view = inflater.inflate(R.layout.item_header, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        if (viewHolder instanceof ViewHolder) {
            GithubRepos repos = getItem(position);
            //cast holder to item viewholder and set data
            ((ViewHolder) viewHolder).tvName.setText(repos.getName());
            ((ViewHolder) viewHolder).tvDesc.setText(repos.getDesc());
            ((ViewHolder) viewHolder).tvStarCount.setText(String.valueOf(repos.getCount()));
        } else if (viewHolder instanceof HeaderViewHolder) {
            // cast holder to header viewholder and set data for header.
            // TODO get photurl here
            String photoUrl = "";
            Glide.with(fragment).load(photoUrl).placeholder(R.drawable.drawable_placeholder).error(
                    R.drawable.drawable_placeholder).into(((HeaderViewHolder) viewHolder).ivPhoto);
        }
    }

    private GithubRepos getItem(int position) {
        if (position > 0) {
            return lstRepos.get(position - 1);
        } else {
            return lstRepos.get(0);
        }
    }

    @Override
    public int getItemCount() {
        return lstRepos.size() + 1;
    }

    public void clear() {
        int size = getItemCount();
        lstRepos.clear();
        notifyItemRangeRemoved(0, size);
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position)) {
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    public void addAll(List<GithubRepos> repos) {
        int prevSize = getItemCount();
        this.lstRepos.addAll(repos);
        notifyItemRangeInserted(prevSize, lstRepos.size());
    }
}
