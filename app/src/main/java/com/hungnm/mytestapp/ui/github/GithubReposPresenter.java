package com.hungnm.mytestapp.ui.github;

import android.content.Context;

import com.hungnm.mytestapp.R;
import com.hungnm.mytestapp.data.DataRepository;
import com.hungnm.mytestapp.data.DataSource;
import com.hungnm.mytestapp.data.models.github.GithubRepos;
import com.hungnm.mytestapp.util.mvp.BasePresenter;
import com.hungnm.mytestapp.util.threading.MainUiThread;
import com.hungnm.mytestapp.util.threading.ThreadExecutor;

import java.util.List;

public class GithubReposPresenter extends BasePresenter<GithubReposContract.View>
        implements GithubReposContract.Presenter {

    private DataRepository dataRepository;
    private ThreadExecutor threadExecutor;
    private MainUiThread mainUiThread;

    public GithubReposPresenter(GithubReposContract.View view, DataRepository dataRepository,
                                ThreadExecutor threadExecutor, MainUiThread mainUiThread) {
        this.view = view;
        this.dataRepository = dataRepository;
        this.threadExecutor = threadExecutor;
        this.mainUiThread = mainUiThread;
    }

    @Override
    public void getGithubRepos(final Context context, int page, String username) {
        if (view == null) {
            return;
        }

        view.setProgressBar(true);

        dataRepository.getGithubRepos(context, page, username, new DataSource.GetGithubReposCallback() {
            @Override
            public void onSuccess(List<GithubRepos> repos) {
                if (view != null) {
                    view.showRepos(repos);
                    view.setProgressBar(false);
                    view.shouldShowPlaceholderText();
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                if (view != null) {
                    view.setProgressBar(false);
                    view.showToastMessage(context.getString(R.string.error_msg));
                    view.shouldShowPlaceholderText();
                }
            }

            @Override
            public void onNetworkFailure() {
                if (view != null) {
                    view.setProgressBar(false);
                    view.showToastMessage(context.getString(R.string.network_failure_msg));
                    view.shouldShowPlaceholderText();
                }
            }
        });
    }
}
