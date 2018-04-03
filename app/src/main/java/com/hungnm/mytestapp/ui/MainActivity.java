package com.hungnm.mytestapp.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.hungnm.mytestapp.R;
import com.hungnm.mytestapp.ui.github.GithubReposFragment;
import com.hungnm.mytestapp.util.BaseFragmentInteractionListener;
import com.hungnm.mytestapp.util.FoaBaseActivity;
import com.hungnm.mytestapp.util.NetworkHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.net.ConnectivityManager.CONNECTIVITY_ACTION;

/**
 * The container responsible for showing and destroying relevant {@link Fragment}, handling
 * back and up navigation using the Fragment back stack and maintaining global state
 * and event subscriptions.
 */
public class MainActivity extends FoaBaseActivity implements BaseFragmentInteractionListener {

    @BindView(R.id.fragmentPlaceHolder)
    FrameLayout fragmentPlaceholder;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tvOfflineMode)
    TextView tvOfflineMode;

    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;

    private IntentFilter connectivityIntentFilter;

    private BroadcastReceiver connectivityBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (!NetworkHelper.getInstance().isNetworkAvailable(context)) {
                tvOfflineMode.setVisibility(View.VISIBLE);
            } else {
                tvOfflineMode.setVisibility(View.GONE);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }

        Uri data = getIntent().getData();
        String username = "";
        if (data != null) {
            List<String> params = data.getPathSegments();
            username = params.get(0);
        }
        if (username == null || username.isEmpty()) {
            username = "jakewharton"; // testing
        }

        Bundle bundle = new Bundle();
        bundle.putString("username", username);
        showFragment(GithubReposFragment.class, bundle);

        connectivityIntentFilter = new IntentFilter(CONNECTIVITY_ACTION);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(connectivityBroadcastReceiver, connectivityIntentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(connectivityBroadcastReceiver);
        super.onPause();
    }

    @Override
    public void resetToolBarScroll() {
        appBarLayout.setExpanded(true, true);
    }
}
