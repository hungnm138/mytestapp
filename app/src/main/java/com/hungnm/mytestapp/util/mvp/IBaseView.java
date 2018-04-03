package com.hungnm.mytestapp.util.mvp;


import android.content.Context;

public interface IBaseView {

    void showToastMessage(String message);

    void setProgressBar(boolean show);

    Context getContext();
}
