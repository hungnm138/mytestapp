package com.hungnm.mytestapp.ui.github;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hungnm.mytestapp.R;
import com.hungnm.mytestapp.data.DataRepository;
import com.hungnm.mytestapp.data.local.LocalDatabase;
import com.hungnm.mytestapp.data.models.github.GithubRepos;
import com.hungnm.mytestapp.di.Injection;
import com.hungnm.mytestapp.util.BaseFragmentInteractionListener;
import com.hungnm.mytestapp.util.EndlessRecyclerViewScrollListener;
import com.hungnm.mytestapp.util.ItemClickSupport;
import com.hungnm.mytestapp.util.mvp.BaseView;
import com.hungnm.mytestapp.util.threading.MainUiThread;
import com.hungnm.mytestapp.util.threading.ThreadExecutor;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GithubReposFragment extends BaseView implements GithubReposContract.View {

    public static final int STARTING_PAGE_INDEX = 1;

    @BindView(R.id.rvRepos)
    RecyclerView rvRepos;

    @BindView(R.id.tvPlaceholder)
    TextView tvPlaceholder;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private List<GithubRepos> lstGithubRepos;
    private GithubReposContract.Presenter presenter;
    private GithubReposRecyclerAdapter recyclerAdapter;

    private BaseFragmentInteractionListener fragmentInteractionListener;
    private EndlessRecyclerViewScrollListener endlessScrollListener;
    private boolean shouldRefreshRepos;

    private String username;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            username = bundle.getString("username", "jakewharton");
        } else {
            username = "jakewharton";
        }

        lstGithubRepos = new ArrayList<>();
        ThreadExecutor threadExecutor = ThreadExecutor.getInstance();
        MainUiThread mainUiThread = MainUiThread.getInstance();

        DatabaseDefinition databaseDefinition = FlowManager.getDatabase(LocalDatabase.class);
        DataRepository dataRepository = Injection.provideDataRepository(
                mainUiThread, threadExecutor, databaseDefinition);

        presenter = new GithubReposPresenter(this, dataRepository, threadExecutor, mainUiThread);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repos, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerAdapter = new GithubReposRecyclerAdapter(this, lstGithubRepos);
        rvRepos.setAdapter(recyclerAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvRepos.setLayoutManager(linearLayoutManager);

        endlessScrollListener = new EndlessRecyclerViewScrollListener(
                linearLayoutManager, STARTING_PAGE_INDEX) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                getRepos(page, username);
            }
        };

        rvRepos.addOnScrollListener(endlessScrollListener);

        ItemClickSupport.addTo(rvRepos).setOnItemClickListener(
                new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        showDetailReposFragment(position);
                    }
                });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshRepos();
            }
        });

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimaryDark, R.color.colorPrimary);

        getRepos(STARTING_PAGE_INDEX, username);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentInteractionListener = (BaseFragmentInteractionListener) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onViewActive(this);
        fragmentInteractionListener.resetToolBarScroll();
    }

    @Override
    public void onPause() {
        presenter.onViewInactive();
        super.onPause();
    }

    @Override
    public void showRepos(List<GithubRepos> repos) {
        if (shouldRefreshRepos) {
            recyclerAdapter.clear();
            endlessScrollListener.resetState();
            shouldRefreshRepos = false;
        }
        recyclerAdapter.addAll(lstGithubRepos);
    }

    @Override
    public void showOwnerInfo() {

    }

    @Override
    public void setProgressBar(boolean show) {
        swipeRefreshLayout.setRefreshing(show);
    }

    @Override
    public void shouldShowPlaceholderText() {
        if (lstGithubRepos.isEmpty()) {
            tvPlaceholder.setVisibility(View.VISIBLE);
        } else {
            tvPlaceholder.setVisibility(View.GONE);
        }
    }

    private void getRepos(int page, String username) {
        if (getContext() != null) {
            presenter.getGithubRepos(getContext().getApplicationContext(), page, username);
        }
    }

    private void refreshRepos() {
        shouldRefreshRepos = true;
        getRepos(STARTING_PAGE_INDEX, username);
    }

    private void showDetailReposFragment(int position) {
        // TODO Do something
    }
}
