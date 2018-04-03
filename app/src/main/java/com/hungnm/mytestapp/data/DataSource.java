package com.hungnm.mytestapp.data;

import com.hungnm.mytestapp.data.models.github.GithubRepos;
import com.hungnm.mytestapp.util.threading.MainUiThread;
import com.hungnm.mytestapp.util.threading.ThreadExecutor;

import java.util.List;

/**
 * The interface that exposes fetching and storing data through helper methods.
 * This is to be implemented by all data sources such as
 * {@link com.hungnm.mytestapp.data.remote.RemoteDataSource} and
 * {@link com.hungnm.mytestapp.data.local.LocalDataSource}
 */
public abstract class DataSource {

    protected MainUiThread mainUiThread;
    protected ThreadExecutor threadExecutor;

    public DataSource(MainUiThread mainUiThread, ThreadExecutor threadExecutor) {
        this.mainUiThread = mainUiThread;
        this.threadExecutor = threadExecutor;
    }

    public interface GetGithubReposCallback {
        void onSuccess(List<GithubRepos> repos);

        void onFailure(Throwable throwable);

        void onNetworkFailure();
    }

    public abstract void getGithubRepos(int page, String username, GetGithubReposCallback callback);
}
