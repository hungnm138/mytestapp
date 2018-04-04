package com.hungnm.mytestapp.data.remote;

import com.hungnm.mytestapp.BuildConfig;
import com.hungnm.mytestapp.data.DataSource;
import com.hungnm.mytestapp.util.threading.MainUiThread;
import com.hungnm.mytestapp.util.threading.ThreadExecutor;

import java.util.HashMap;
import java.util.Map;

/**
 * The class for fetching data from Github API on a background thread and returning data via
 * callbacks on the main UI thread
 */
public class RemoteDataSource extends DataSource {

    public static final String API_KEY = BuildConfig.MY_API_KEY;
    public static final String BASE_API_URL = "https://api.github.com/";

    public static final String QUERY_PARAM_USERNAME = "username";

    public static final String QUERY_PARAM_API_KEY = "API_KEY"; // just in case need authentication

    public static final String QUERY_PARAM_DIRECTION = "direction"; // asc, desc
    public static final String QUERY_PARAM_SORT = "sort"; // created, updated, pushed, full_name
    public static final String QUERY_PARAM_TYPE = "type"; // all, owner, member.

    // For paging - if needed
    public static final String QUERY_PARAM_PER_PAGE = "page";
    public static final String QUERY_PARAM_VALUE_PER_PAGE = "10";
    public static final String QUERY_PARAM_PAGE = "page";

    private static RemoteDataSource remoteDataSource;

    private ApiService apiService;

    private RemoteDataSource(MainUiThread mainUiThread,
                             ThreadExecutor threadExecutor, ApiService apiService) {
        super(mainUiThread, threadExecutor);
        this.apiService = apiService;
    }

    public static synchronized RemoteDataSource getInstance(
            MainUiThread mainUiThread,
            ThreadExecutor threadExecutor,
            ApiService apiService) {

        if (remoteDataSource == null) {
            remoteDataSource = new RemoteDataSource(mainUiThread, threadExecutor, apiService);
        }
        return remoteDataSource;
    }

    @Override
    public void getGithubRepos(int page, String username, final GetGithubReposCallback callback) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put(QUERY_PARAM_TYPE, "owner");
        queryMap.put(QUERY_PARAM_SORT, "updated");
        queryMap.put(QUERY_PARAM_DIRECTION, "desc");

        retrofit2.Call<com.hungnm.mytestapp.data.models.github.Response> call =
                apiService.getRepos(username, queryMap);

        call.enqueue(
                new retrofit2.Callback<com.hungnm.mytestapp.data.models.github.Response>() {
                    @Override
                    public void onResponse(
                            retrofit2.Call<com.hungnm.mytestapp.data.models.github.Response> call,
                            retrofit2.Response<com.hungnm.mytestapp.data.models.github.Response> response) {
                        if (response.isSuccessful()) {
                            com.hungnm.mytestapp.data.models.github.Response reposResponse = response.body();
                            callback.onSuccess(reposResponse.getrepos());
                        } else {
                            callback.onFailure(new Throwable());
                        }
                    }

                    @Override
                    public void onFailure(
                            retrofit2.Call<com.hungnm.mytestapp.data.models.github.Response> call,
                            Throwable t) {
                        callback.onFailure(t);
                    }
                });
    }
}
