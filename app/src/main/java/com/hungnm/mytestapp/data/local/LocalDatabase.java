package com.hungnm.mytestapp.data.local;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * The class defining the local SQlite DB managed by DBFlow lib.
 */
@Database(name = LocalDatabase.NAME, version = LocalDatabase.VERSION)
public class LocalDatabase {

    public static final String NAME = "MyGithubReposAppDb";

    public static final int VERSION = 1;
}
