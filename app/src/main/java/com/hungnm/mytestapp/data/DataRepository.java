package com.hungnm.mytestapp.data;

import android.content.Context;

import com.hungnm.mytestapp.data.models.github.GithubRepos;
import com.hungnm.mytestapp.util.NetworkHelper;

import java.util.List;

/**
 * The primary class for the presenters that extend
 * {@link com.hungnm.mytestapp.util.mvp.BasePresenter} to interact with
 * for fetching and storing data.
 * It is the middleman in front of all data sources such as
 * {@link com.hungnm.mytestapp.data.remote.RemoteDataSource}
 * and {@link com.hungnm.mytestapp.data.local.LocalDataSource} and delegates the work to
 * them depending on conditions such as network availability, etc.
 */
public class DataRepository {

    private DataSource remoteDataSource;
    private DataSource localDataSource;
    private NetworkHelper networkHelper;

    private static DataRepository dataRepository;

    public DataRepository(DataSource remoteDataSource, DataSource localDataSource,
                          NetworkHelper networkHelper) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
        this.networkHelper = networkHelper;
    }

    public static synchronized DataRepository getInstance(DataSource remoteDataSource,
                                                          DataSource localDataSource,
                                                          NetworkHelper networkHelper) {
        if (dataRepository == null) {
            dataRepository = new DataRepository(remoteDataSource, localDataSource, networkHelper);
        }
        return dataRepository;
    }

    public void getGithubRepos(Context context, int page, String username, final DataSource.GetGithubReposCallback callback) {
        if (networkHelper.isNetworkAvailable(context)) {
            remoteDataSource.getGithubRepos(page, username, new DataSource.GetGithubReposCallback() {
                @Override
                public void onSuccess(List<GithubRepos> repos) {
                    callback.onSuccess(repos);
                    // TODO Save data to local
                    // Implement later here ...

                }

                @Override
                public void onFailure(Throwable throwable) {
                    callback.onFailure(throwable);
                }

                @Override
                public void onNetworkFailure() {
                    callback.onNetworkFailure();
                }
            });
        } else {
            // TODO get data from local storage
            // Implement later ...
        }
    }

    public void destroyInstance() {
        dataRepository = null;
    }
}
