package com.hungnm.mytestapp.di;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.hungnm.mytestapp.data.DataRepository;
import com.hungnm.mytestapp.data.local.LocalDataSource;
import com.hungnm.mytestapp.data.remote.ApiService;
import com.hungnm.mytestapp.data.remote.RemoteDataSource;
import com.hungnm.mytestapp.util.NetworkHelper;
import com.hungnm.mytestapp.util.threading.MainUiThread;
import com.hungnm.mytestapp.util.threading.ThreadExecutor;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.hungnm.mytestapp.data.remote.RemoteDataSource.API_KEY;
import static com.hungnm.mytestapp.data.remote.RemoteDataSource.BASE_API_URL;
import static com.hungnm.mytestapp.data.remote.RemoteDataSource.QUERY_PARAM_API_KEY;

public class Injection {

    public static DataRepository provideDataRepository(MainUiThread mainUiThread,
                                                       ThreadExecutor threadExecutor,
                                                       DatabaseDefinition databaseDefinition) {

        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                HttpUrl httpUrl = chain.request().url().newBuilder()
                        .addQueryParameter(QUERY_PARAM_API_KEY, API_KEY)
                        .build();
                Request newRequest = chain.request().newBuilder().url(httpUrl).build();
                return chain.proceed(newRequest);
            }
        };

        OkHttpClient okHttpClient =
                new OkHttpClient.Builder()
                        .addNetworkInterceptor(new StethoInterceptor())
                        .addInterceptor(interceptor)
                        .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        return DataRepository.getInstance(
                RemoteDataSource.getInstance(mainUiThread, threadExecutor, apiService),
                LocalDataSource.getInstance(mainUiThread, threadExecutor, databaseDefinition),
                NetworkHelper.getInstance());
    }
}
