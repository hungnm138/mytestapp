package com.hungnm.mytestapp.ui.github;

import android.content.Context;

import com.hungnm.mytestapp.data.models.github.GithubRepos;
import com.hungnm.mytestapp.util.mvp.IBasePresenter;
import com.hungnm.mytestapp.util.mvp.IBaseView;

import java.util.List;

interface GithubReposContract {
    interface View extends IBaseView {

        void showRepos(List<GithubRepos> repos);

        void showOwnerInfo();

        void shouldShowPlaceholderText();
    }

    interface Presenter extends IBasePresenter<View> {

        void getGithubRepos(Context context, int page, String username);
    }
}
