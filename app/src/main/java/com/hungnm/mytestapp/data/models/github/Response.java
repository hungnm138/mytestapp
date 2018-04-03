
package com.hungnm.mytestapp.data.models.github;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.ColumnIgnore;

import java.util.List;

public class Response {

    @SerializedName("repos")
    @Expose
    @ColumnIgnore
    private List<GithubRepos> repos;

    public List<GithubRepos> getrepos() {
        return repos;
    }

    public void setRepos(List<GithubRepos> repos) {
        this.repos = repos;
    }

}
