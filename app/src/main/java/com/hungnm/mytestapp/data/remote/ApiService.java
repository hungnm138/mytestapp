package com.hungnm.mytestapp.data.remote;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * The interface defining methods to fetch data and their respective API
 * endpoints using Retrofit lib.
 */
public interface ApiService {

    @GET("users/{username}/repos")
    Call<com.hungnm.mytestapp.data.models.github.Response> getRepos(
            @Path(RemoteDataSource.QUERY_PARAM_USERNAME) String username,
            @QueryMap Map<String, String> queryMap);
}
