package com.hungnm.mytestapp.data.local;


import com.hungnm.mytestapp.data.DataSource;
import com.hungnm.mytestapp.data.models.github.GithubRepos;
import com.hungnm.mytestapp.util.threading.MainUiThread;
import com.hungnm.mytestapp.util.threading.ThreadExecutor;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import java.util.List;

/**
 * The class for fetching from and storing data into a local SQLite DB on a background thread and
 * returning data via callbacks on the main UI thread
 */
public class LocalDataSource extends DataSource {

    private static LocalDataSource localDataSource;

    private DatabaseDefinition databaseDefinition;

    private LocalDataSource(MainUiThread mainUiThread, ThreadExecutor threadExecutor,
                            DatabaseDefinition databaseDefinition) {
        super(mainUiThread, threadExecutor);
        this.databaseDefinition = databaseDefinition;
    }

    public static synchronized LocalDataSource getInstance(MainUiThread mainUiThread,
                                                           ThreadExecutor threadExecutor, DatabaseDefinition databaseDefinition) {
        if (localDataSource == null) {
            localDataSource = new LocalDataSource(mainUiThread, threadExecutor, databaseDefinition);
        }
        return localDataSource;
    }

    @Override
    public void getGithubRepos(int page, String username, GetGithubReposCallback callback) {
        // TODO get from local storage ...
    }

    public void storeRepos(final List<GithubRepos> lstRepos) {
        Transaction transaction = databaseDefinition.beginTransactionAsync(
                new ITransaction() {
                    @Override
                    public void execute(DatabaseWrapper databaseWrapper) {
                        for (GithubRepos repos : lstRepos) {
                            repos.save();
                        }
                    }
                }).build();
        transaction.execute();
    }
}
