package com.hungnm.mytestapp.data.models.github;

import com.raizlabs.android.dbflow.structure.BaseModel;

public class GithubRepos extends BaseModel {

    private String name;
    private String desc;
    private long count;


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
